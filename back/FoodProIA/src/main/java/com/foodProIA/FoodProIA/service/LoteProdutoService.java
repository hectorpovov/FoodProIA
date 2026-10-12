package com.foodProIA.FoodProIA.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodProIA.FoodProIA.dto.request.EstoqueRequestDTO;
import com.foodProIA.FoodProIA.dto.request.LoteProdutoRequestDTO;
import com.foodProIA.FoodProIA.dto.response.EstoqueResponseDTO;
import com.foodProIA.FoodProIA.dto.response.LoteProdutoResponseDTO;
import com.foodProIA.FoodProIA.entity.EstoqueEntity;
import com.foodProIA.FoodProIA.entity.LoteProdutoEntity;
import com.foodProIA.FoodProIA.entity.ProdutoEntity;
import com.foodProIA.FoodProIA.entity.RegistroDeProducaoEntity;
import com.foodProIA.FoodProIA.repository.EstoqueRepository;
import com.foodProIA.FoodProIA.repository.LoteProdutoRepository;
import com.foodProIA.FoodProIA.repository.ProdutoRepository;
import com.foodProIA.FoodProIA.repository.RegistroDeProducaoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoteProdutoService {

    private final LoteProdutoRepository loteProdutoRepository;
    private final EstoqueRepository estoqueRepository;
    private final ProdutoRepository produtoRepository;
    private final RegistroDeProducaoRepository registroDeProducaoRepository;

    // =========================================================
    // CRIAR LOTE DE PRODUTO
    // =========================================================

    @Transactional
    public LoteProdutoResponseDTO criar(LoteProdutoRequestDTO dto) {

        LoteProdutoEntity lote = new LoteProdutoEntity();

        lote.setQuantidade(dto.getQuantidade());
        lote.setDataFabricacao(dto.getDataFabricacao());

        adicionarProduto(lote, dto.getIdProduto());

        adicionarRegistroDeProducao(
                lote,
                dto.getIdRegistroDeProducao()
        );

        // Calcula a validade a partir da fabricação e do produto.
        calcularDataValidade(lote);

        lote = loteProdutoRepository.save(lote);

        // O estoque é criado junto com o lote.
        criarEstoque(lote, dto.getEstoque());

        return new LoteProdutoResponseDTO(lote);
    }

    // =========================================================
    // ALTERAR LOTE DE PRODUTO
    // =========================================================

    @Transactional
    public LoteProdutoResponseDTO alterar(
            Long id,
            LoteProdutoRequestDTO dto
    ) {

        LoteProdutoEntity lote = loteProdutoRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Lote de produto não encontrado"
                        )
                );

        lote.setQuantidade(dto.getQuantidade());
        lote.setDataFabricacao(dto.getDataFabricacao());

        adicionarProduto(lote, dto.getIdProduto());

        adicionarRegistroDeProducao(
                lote,
                dto.getIdRegistroDeProducao()
        );

        // Recalcula a validade caso a fabricação ou o produto mudem.
        calcularDataValidade(lote);

        // O lote pode não possuir estoque associado.
        if (lote.getEstoque() == null) {

            criarEstoque(lote, dto.getEstoque());

        } else {

            EstoqueEntity estoque = lote.getEstoque();

            atualizarDadosEstoque(
                    estoque,
                    dto.getEstoque()
            );

            recalcularTempoParado(estoque);

            estoqueRepository.save(estoque);
        }

        return new LoteProdutoResponseDTO(lote);
    }

    // =========================================================
    // EXCLUIR LOTE DE PRODUTO
    // =========================================================

    @Transactional
    public void excluir(Long id) {

        LoteProdutoEntity lote = loteProdutoRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Lote de produto não encontrado"
                        )
                );

        /*
         * O estoque possui uma chave estrangeira obrigatória
         * para o lote. Por isso, deve ser removido primeiro.
         *
         * O flush garante que a exclusão do estoque seja enviada
         * ao banco antes da exclusão do lote.
         */
        if (lote.getEstoque() != null) {

            EstoqueEntity estoque = lote.getEstoque();

            lote.setEstoque(null);

            estoqueRepository.delete(estoque);
            estoqueRepository.flush();
        }

        /*
         * O produto e o registro de produção não são excluídos.
         * Apenas o lote é removido.
         */
        loteProdutoRepository.delete(lote);
    }

    // =========================================================
    // BUSCAR LOTE POR ID
    // =========================================================

    @Transactional
    public LoteProdutoResponseDTO buscarPorId(Long id) {

        LoteProdutoEntity lote = loteProdutoRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Lote de produto não encontrado"
                        )
                );

        if (lote.getEstoque() != null) {

            recalcularTempoParado(lote.getEstoque());

            estoqueRepository.save(lote.getEstoque());
        }

        return new LoteProdutoResponseDTO(lote);
    }

    // =========================================================
    // LISTAR TODOS OS LOTES
    // =========================================================

    @Transactional
    public List<LoteProdutoResponseDTO> listarTodos() {

        List<LoteProdutoEntity> lotes =
                loteProdutoRepository.findAll();

        for (LoteProdutoEntity lote : lotes) {

            if (lote.getEstoque() != null) {

                recalcularTempoParado(lote.getEstoque());

                estoqueRepository.save(lote.getEstoque());
            }
        }

        return lotes.stream()
                .map(LoteProdutoResponseDTO::new)
                .toList();
    }

    // =========================================================
    // CRIAR ESTOQUE
    // =========================================================

    @Transactional
    public EstoqueResponseDTO criarEstoque(
            Long idLoteProduto,
            EstoqueRequestDTO dto
    ) {

        LoteProdutoEntity lote =
                loteProdutoRepository.findById(idLoteProduto)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Lote de produto não encontrado"
                                )
                        );

        if (lote.getEstoque() != null) {

            throw new IllegalStateException(
                    "Este lote já possui um estoque associado"
            );
        }

        EstoqueEntity estoque = new EstoqueEntity();

        atualizarDadosEstoque(estoque, dto);

        estoque.setLoteProduto(lote);
        lote.setEstoque(estoque);

        recalcularTempoParado(estoque);

        estoque = estoqueRepository.save(estoque);

        return new EstoqueResponseDTO(estoque);
    }

    // =========================================================
    // ALTERAR ESTOQUE
    // =========================================================

    @Transactional
    public EstoqueResponseDTO alterarEstoque(
            Long id,
            EstoqueRequestDTO dto
    ) {

        EstoqueEntity estoque = estoqueRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Estoque não encontrado"
                        )
                );

        atualizarDadosEstoque(estoque, dto);

        recalcularTempoParado(estoque);

        estoque = estoqueRepository.save(estoque);

        return new EstoqueResponseDTO(estoque);
    }

    // =========================================================
    // EXCLUIR ESTOQUE
    // =========================================================

    @Transactional
    public void excluirEstoque(Long id) {

        EstoqueEntity estoque = estoqueRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Estoque não encontrado"
                        )
                );

        LoteProdutoEntity lote = estoque.getLoteProduto();

        if (lote != null) {
            lote.setEstoque(null);
        }

        estoqueRepository.delete(estoque);
    }

    // =========================================================
    // BUSCAR ESTOQUE POR ID
    // =========================================================

    @Transactional
    public EstoqueResponseDTO buscarEstoquePorId(Long id) {

        EstoqueEntity estoque = estoqueRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Estoque não encontrado"
                        )
                );

        recalcularTempoParado(estoque);

        estoque = estoqueRepository.save(estoque);

        return new EstoqueResponseDTO(estoque);
    }

    // =========================================================
    // LISTAR TODOS OS ESTOQUES
    // =========================================================

    @Transactional
    public List<EstoqueResponseDTO> listarTodosEstoques() {

        List<EstoqueEntity> estoques =
                estoqueRepository.findAll();

        for (EstoqueEntity estoque : estoques) {

            recalcularTempoParado(estoque);

            estoqueRepository.save(estoque);
        }

        return estoques.stream()
                .map(EstoqueResponseDTO::new)
                .toList();
    }

    // =========================================================
    // ADICIONAR PRODUTO EXISTENTE AO LOTE
    // =========================================================

    private void adicionarProduto(
            LoteProdutoEntity lote,
            Long idProduto
    ) {

        ProdutoEntity produto = produtoRepository.findById(idProduto)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Produto não encontrado"
                        )
                );

        lote.setProduto(produto);
    }

    // =========================================================
    // ADICIONAR REGISTRO DE PRODUÇÃO EXISTENTE AO LOTE
    // =========================================================

    private void adicionarRegistroDeProducao(
            LoteProdutoEntity lote,
            Long idRegistroDeProducao
    ) {

        RegistroDeProducaoEntity registro =
                registroDeProducaoRepository
                        .findById(idRegistroDeProducao)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Registro de produção não encontrado"
                                )
                        );

        lote.setRegistroDeProducao(registro);
    }

    // =========================================================
    // CALCULAR DATA DE VALIDADE
    // =========================================================

    private void calcularDataValidade(LoteProdutoEntity lote) {

        ProdutoEntity produto = lote.getProduto();

        LocalDateTime dataFabricacao = lote.getDataFabricacao();

        if (produto == null || dataFabricacao == null) {

            lote.setDataValidade(null);

            return;
        }

        Integer anos = produto.getAnosValidade();
        Integer meses = produto.getMesesValidade();
        Integer dias = produto.getDiasValidade();

        /*
         * Se o produto não possuir nenhuma informação de validade,
         * não é possível calcular uma data de validade.
         */
        if (anos == null && meses == null && dias == null) {

            lote.setDataValidade(null);

            return;
        }

        LocalDateTime dataValidade = dataFabricacao;

        if (anos != null) {
            dataValidade = dataValidade.plusYears(anos);
        }

        if (meses != null) {
            dataValidade = dataValidade.plusMonths(meses);
        }

        if (dias != null) {
            dataValidade = dataValidade.plusDays(dias);
        }

        lote.setDataValidade(dataValidade);
    }

    // =========================================================
    // CRIAR ESTOQUE ASSOCIADO AO LOTE
    // =========================================================

    private EstoqueEntity criarEstoque(
            LoteProdutoEntity lote,
            EstoqueRequestDTO dto
    ) {

        EstoqueEntity estoque = new EstoqueEntity();

        atualizarDadosEstoque(estoque, dto);

        estoque.setLoteProduto(lote);
        lote.setEstoque(estoque);

        recalcularTempoParado(estoque);

        return estoqueRepository.save(estoque);
    }

    // =========================================================
    // ATUALIZAR DADOS DO ESTOQUE
    // =========================================================

    private void atualizarDadosEstoque(
            EstoqueEntity estoque,
            EstoqueRequestDTO dto
    ) {

        estoque.setSKU(dto.getSKU());

        estoque.setClassificacaoDoItem(
                dto.getClassificacaoDoItem()
        );

        estoque.setDataChegada(dto.getDataChegada());

        estoque.setQuantidadeProduto(
                dto.getQuantidadeProduto()
        );

        estoque.setDestino(dto.getDestino());

        estoque.setValorDoProduto(
                dto.getValorDoProduto()
        );

        estoque.setQuantidadeSaida(
                dto.getQuantidadeSaida()
        );
    }

    // =========================================================
    // RECALCULAR TEMPO DE PERMANÊNCIA NO ESTOQUE
    // =========================================================

    private void recalcularTempoParado(EstoqueEntity estoque) {

        LocalDateTime dataChegada = estoque.getDataChegada();

        if (dataChegada == null) {

            estoque.setTempoParado(null);
            estoque.setDiasParado(null);
            estoque.setMesesParado(null);

            return;
        }

        LocalDateTime agora = LocalDateTime.now();

        /*
         * Evita valores negativos caso a data de chegada
         * esteja no futuro.
         */
        if (dataChegada.isAfter(agora)) {

            estoque.setTempoParado(Duration.ZERO);
            estoque.setDiasParado(0);
            estoque.setMesesParado(0);

            return;
        }

        Duration tempoParado =
                Duration.between(dataChegada, agora);

        long dias = ChronoUnit.DAYS.between(
                dataChegada,
                agora
        );

        long meses = ChronoUnit.MONTHS.between(
                dataChegada,
                agora
        );

        estoque.setTempoParado(tempoParado);

        estoque.setDiasParado(Math.toIntExact(dias));

        estoque.setMesesParado(Math.toIntExact(meses));
    }
}