package com.foodProIA.FoodProIA.service;

import com.foodProIA.FoodProIA.dto.request.FichaProdutoRequestDTO;
import com.foodProIA.FoodProIA.dto.request.InformacaoNutricionalProdutoRequestDTO;
import com.foodProIA.FoodProIA.dto.request.ProdutoRequestDTO;
import com.foodProIA.FoodProIA.dto.request.TipoParametroBooleanoRequestDTO;
import com.foodProIA.FoodProIA.dto.request.TipoParametroNumericoRequestDTO;
import com.foodProIA.FoodProIA.dto.request.TipoParametroRequestDTO;
import com.foodProIA.FoodProIA.dto.request.TipoParametroTextualRequestDTO;
import com.foodProIA.FoodProIA.dto.response.ProdutoResponseDTO;
import com.foodProIA.FoodProIA.entity.FichaProdutoEntity;
import com.foodProIA.FoodProIA.entity.InformacaoNutricionalEntity;
import com.foodProIA.FoodProIA.entity.InformacaoNutricionalProdutoEntity;
import com.foodProIA.FoodProIA.entity.MateriaPrimaEntity;
import com.foodProIA.FoodProIA.entity.ProdutoEntity;
import com.foodProIA.FoodProIA.entity.TipoParametroBooleanoEntity;
import com.foodProIA.FoodProIA.entity.TipoParametroEntity;
import com.foodProIA.FoodProIA.entity.TipoParametroNumericoEntity;
import com.foodProIA.FoodProIA.entity.TipoParametroTextualEntity;
import com.foodProIA.FoodProIA.repository.FichaProdutoRepository;
import com.foodProIA.FoodProIA.repository.InformacaoNutricionalRepository;
import com.foodProIA.FoodProIA.repository.MateriaPrimaRepository;
import com.foodProIA.FoodProIA.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final FichaProdutoRepository fichaProdutoRepository;

    private final MateriaPrimaRepository materiaPrimaRepository;

    private final InformacaoNutricionalRepository
            informacaoNutricionalRepository;


    // =========================
    // CRIAR
    // =========================

    @Transactional
    public ProdutoResponseDTO criar(ProdutoRequestDTO dto) {

        ProdutoEntity produto = new ProdutoEntity();

        produto.setNomeComercial(dto.getNomeComercial());
        produto.setDescricao(dto.getDescricao());
        produto.setCategoria(dto.getCategoria());
        produto.setUnidadeDeMedida(dto.getUnidadeDeMedida());
        produto.setAnosValidade(dto.getAnosValidade());
        produto.setMesesValidade(dto.getMesesValidade());
        produto.setDiasValidade(dto.getDiasValidade());

        produtoRepository.save(produto);

        criarFicha(produto, dto.getFichaProduto());

        criarParametros(produto, dto.getParametros());

        criarRelacoesNutricionais(
                produto,
                dto.getInformacoesNutricionais()
        );

        return new ProdutoResponseDTO(produto);
    }


    // =========================
    // ALTERAR
    // =========================

    @Transactional
    public ProdutoResponseDTO alterar(
            Long id,
            ProdutoRequestDTO dto) {

        ProdutoEntity produto =
                produtoRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Produto não encontrado"
                                )
                        );

        produto.setNomeComercial(dto.getNomeComercial());
        produto.setDescricao(dto.getDescricao());
        produto.setCategoria(dto.getCategoria());
        produto.setUnidadeDeMedida(dto.getUnidadeDeMedida());
        produto.setAnosValidade(dto.getAnosValidade());
        produto.setMesesValidade(dto.getMesesValidade());
        produto.setDiasValidade(dto.getDiasValidade());

        alterarFicha(produto, dto.getFichaProduto());

        atualizarParametros(produto, dto.getParametros());

        atualizarRelacoesNutricionais(
                produto,
                dto.getInformacoesNutricionais()
        );

        return new ProdutoResponseDTO(produto);
    }


    // =========================
    // EXCLUIR
    // =========================

    @Transactional
    public void excluir(Long id) {

        ProdutoEntity produto =
                produtoRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Produto não encontrado"
                                )
                        );

        produtoRepository.delete(produto);
    }


    // =========================
    // BUSCAR POR ID
    // =========================

    @Transactional(readOnly = true)
    public ProdutoResponseDTO buscarPorId(Long id) {

        ProdutoEntity produto =
                produtoRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Produto não encontrado"
                                )
                        );

        return new ProdutoResponseDTO(produto);
    }


    // =========================
    // LISTAR TODOS
    // =========================

    @Transactional(readOnly = true)
    public List<ProdutoResponseDTO> listarTodos() {

        return produtoRepository.findAll()
                .stream()
                .map(ProdutoResponseDTO::new)
                .toList();
    }


    // =========================
    // FICHA
    // =========================

    private void criarFicha(
            ProdutoEntity produto,
            FichaProdutoRequestDTO dto) {

        FichaProdutoEntity ficha = new FichaProdutoEntity();

        ficha.setNomeTecnico(dto.getNomeTecnico());
        ficha.setDataCriacao(dto.getDataCriacao());
        ficha.setInstrucaoDeManipulacao(
                dto.getInstrucaoDeManipulacao()
        );
        ficha.setTipoDeEmbalagem(dto.getTipoDeEmbalagem());
        ficha.setStatus(dto.getStatus());

        ficha.setProduto(produto);
        produto.setFichaProduto(ficha);

        adicionarMateriasPrimas(ficha, dto.getIdsMateriasPrimas());

        fichaProdutoRepository.save(ficha);
    }


    private void alterarFicha(
            ProdutoEntity produto,
            FichaProdutoRequestDTO dto) {

        FichaProdutoEntity ficha = produto.getFichaProduto();

        if (ficha == null) {
            criarFicha(produto, dto);
            return;
        }

        ficha.setNomeTecnico(dto.getNomeTecnico());
        ficha.setDataCriacao(dto.getDataCriacao());
        ficha.setInstrucaoDeManipulacao(
                dto.getInstrucaoDeManipulacao()
        );
        ficha.setTipoDeEmbalagem(dto.getTipoDeEmbalagem());
        ficha.setStatus(dto.getStatus());

        ficha.getMateriasPrimas().clear();

        adicionarMateriasPrimas(ficha, dto.getIdsMateriasPrimas());
    }


    private void adicionarMateriasPrimas(
            FichaProdutoEntity ficha,
            List<Long> idsMateriasPrimas) {

        if (idsMateriasPrimas == null) {
            return;
        }

        for (Long id : idsMateriasPrimas) {

            MateriaPrimaEntity materiaPrima =
                    materiaPrimaRepository.findById(id)
                            .orElseThrow(() ->
                                    new EntityNotFoundException(
                                            "Matéria-prima não encontrada"
                                    )
                            );

            ficha.getMateriasPrimas().add(materiaPrima);
        }
    }


    // =========================
    // PARÂMETROS
    // =========================

    private void criarParametros(
            ProdutoEntity produto,
            List<TipoParametroRequestDTO> dtos) {

        if (dtos == null) {
            return;
        }

        for (TipoParametroRequestDTO dto : dtos) {

            TipoParametroEntity parametro = criarParametro(dto);

            produto.getParametros().add(parametro);
        }
    }


    private TipoParametroEntity criarParametro(
            TipoParametroRequestDTO dto) {

        if (dto instanceof TipoParametroBooleanoRequestDTO booleano) {

            TipoParametroBooleanoEntity parametro =
                    new TipoParametroBooleanoEntity();

            parametro.setNome(dto.getNome());
            parametro.setValorEsperado(booleano.isValorEsperado());

            return parametro;
        }

        if (dto instanceof TipoParametroNumericoRequestDTO numerico) {

            TipoParametroNumericoEntity parametro =
                    new TipoParametroNumericoEntity();

            parametro.setNome(dto.getNome());
            parametro.setValorMinimo(numerico.getValorMinimo());
            parametro.setValorMaximo(numerico.getValorMaximo());
            parametro.setUnidadeMedida(numerico.getUnidadeMedida());

            return parametro;
        }

        if (dto instanceof TipoParametroTextualRequestDTO textual) {

            TipoParametroTextualEntity parametro =
                    new TipoParametroTextualEntity();

            parametro.setNome(dto.getNome());
            parametro.setEsperado(textual.getEsperado());
            parametro.setPossibilidades(textual.getPossibilidades());

            return parametro;
        }

        throw new IllegalArgumentException(
                "Tipo de parâmetro não suportado"
        );
    }


    private void atualizarParametros(
            ProdutoEntity produto,
            List<TipoParametroRequestDTO> dtos) {

        produto.getParametros().clear();

        criarParametros(produto, dtos);
    }


    // =========================
    // INFORMAÇÕES NUTRICIONAIS
    // =========================

    private void criarRelacoesNutricionais(
            ProdutoEntity produto,
            List<InformacaoNutricionalProdutoRequestDTO> dtos) {

        if (dtos == null) {
            return;
        }

        for (InformacaoNutricionalProdutoRequestDTO dto : dtos) {

            InformacaoNutricionalEntity informacaoNutricional =
                    informacaoNutricionalRepository.findById(
                            dto.getIdInformacaoNutricional()
                    ).orElseThrow(() ->
                            new EntityNotFoundException(
                                    "Informação nutricional não encontrada"
                            )
                    );

            new InformacaoNutricionalProdutoEntity(
                    produto,
                    informacaoNutricional,
                    dto.getValor()
            );
        }
    }


    private void atualizarRelacoesNutricionais(
            ProdutoEntity produto,
            List<InformacaoNutricionalProdutoRequestDTO> dtos) {

        produto.getInformacoesNutricionais().clear();

        criarRelacoesNutricionais(produto, dtos);
    }

}