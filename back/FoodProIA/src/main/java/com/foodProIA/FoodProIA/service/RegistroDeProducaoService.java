package com.foodProIA.FoodProIA.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodProIA.FoodProIA.dto.request.RegistroDeProducaoRequestDTO;
import com.foodProIA.FoodProIA.dto.request.LoteMateriaPrimaRegistroDeProducaoRequestDTO;
import com.foodProIA.FoodProIA.dto.request.LoteInsumoRegistroDeProducaoRequestDTO;
import com.foodProIA.FoodProIA.dto.response.RegistroDeProducaoResponseDTO;
import com.foodProIA.FoodProIA.entity.LoteInsumoEntity;
import com.foodProIA.FoodProIA.entity.LoteInsumoRegistroDeProducaoEntity;
import com.foodProIA.FoodProIA.entity.LoteMateriaPrimaEntity;
import com.foodProIA.FoodProIA.entity.LoteMateriaPrimaRegistroDeProducaoEntity;
import com.foodProIA.FoodProIA.entity.ProdutoEntity;
import com.foodProIA.FoodProIA.entity.RegistroDeProducaoEntity;
import com.foodProIA.FoodProIA.repository.LoteInsumoRepository;
import com.foodProIA.FoodProIA.repository.LoteMateriaPrimaRepository;
import com.foodProIA.FoodProIA.repository.ProdutoRepository;
import com.foodProIA.FoodProIA.repository.RegistroDeProducaoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RegistroDeProducaoService {

    private final RegistroDeProducaoRepository registroRepository;
    private final ProdutoRepository produtoRepository;
    private final LoteMateriaPrimaRepository loteMateriaPrimaRepository;
    private final LoteInsumoRepository loteInsumoRepository;

    private final EntityManager entityManager;

    public RegistroDeProducaoResponseDTO criarRegistro(
            RegistroDeProducaoRequestDTO dto) {

        RegistroDeProducaoEntity registro =
                new RegistroDeProducaoEntity();

        preencherDados(registro, dto);

        registro = registroRepository.save(registro);

        substituirLotesMateriaPrima(registro, dto.getLotesMateriaPrima());
        substituirLotesInsumos(registro, dto.getLotesInsumos());

        entityManager.flush();

        return new RegistroDeProducaoResponseDTO(registro);
    }

    public RegistroDeProducaoResponseDTO alterarRegistro(
            Long id,
            RegistroDeProducaoRequestDTO dto) {

        RegistroDeProducaoEntity registro =
                registroRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Registro de produção não encontrado com o ID: "
                                        + id));

        preencherDados(registro, dto);

        substituirLotesMateriaPrima(registro, dto.getLotesMateriaPrima());
        substituirLotesInsumos(registro, dto.getLotesInsumos());

        registro = registroRepository.save(registro);

        entityManager.flush();

        return new RegistroDeProducaoResponseDTO(registro);
    }

    @Transactional(readOnly = true)
    public RegistroDeProducaoResponseDTO buscarRegistroPorId(Long id) {

        RegistroDeProducaoEntity registro =
                registroRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Registro de produção não encontrado com o ID: "
                                        + id));

        return new RegistroDeProducaoResponseDTO(registro);
    }

    @Transactional(readOnly = true)
    public List<RegistroDeProducaoResponseDTO> listarRegistros() {

        return registroRepository.findAll()
                .stream()
                .map(RegistroDeProducaoResponseDTO::new)
                .toList();
    }

    public void excluirRegistro(Long id) {

        RegistroDeProducaoEntity registro =
                registroRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Registro de produção não encontrado com o ID: "
                                        + id));

        if (!registro.getEtapas().isEmpty()) {
            throw new IllegalStateException(
                    "Não é possível excluir o registro, pois existem etapas associadas a ele.");
        }

        if (!registro.getPerdas().isEmpty()
                || !registro.getParadas().isEmpty()
                || !registro.getRetrabalhos().isEmpty()) {
            throw new IllegalStateException(
                    "Não é possível excluir o registro, pois existem perdas, paradas ou retrabalhos associados a ele.");
        }

        if (registro.getLoteProduto() != null) {
            throw new IllegalStateException(
                    "Não é possível excluir o registro, pois existe um lote de produto associado a ele.");
        }

        if (registro.getFuncionarios() != null
                && !registro.getFuncionarios().isEmpty()) {
            throw new IllegalStateException(
                    "Não é possível excluir o registro, pois existem funcionários associados a ele.");
        }

        substituirLotesMateriaPrima(registro, List.of());
        substituirLotesInsumos(registro, List.of());

        registroRepository.delete(registro);
    }

    private void preencherDados(
            RegistroDeProducaoEntity registro,
            RegistroDeProducaoRequestDTO dto) {

        registro.setDataInicio(dto.getDataInicio());
        registro.setDataFim(dto.getDataFim());
        registro.setQuantidadeProduzida(dto.getQuantidadeProduzida());
        registro.setTempoMaquina(dto.getTempoMaquina());
        registro.setQuantidadeGasta(dto.getQuantidadeGasta());
        registro.setAprovado(dto.getAprovado());
        registro.setQuantidadeRejeitada(dto.getQuantidadeRejeitada());
        registro.setObservacoes(dto.getObservacoes());

        ProdutoEntity produto = produtoRepository
                .findById(dto.getIdProduto())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Produto não encontrado com o ID: "
                                + dto.getIdProduto()));

        registro.setProduto(produto);
    }

    private void substituirLotesMateriaPrima(
            RegistroDeProducaoEntity registro,
            List<LoteMateriaPrimaRegistroDeProducaoRequestDTO> dtos) {

        // Remove os vínculos anteriores.
        for (LoteMateriaPrimaRegistroDeProducaoEntity vinculo
                : new ArrayList<>(registro.getLotesMateriaPrima())) {

            LoteMateriaPrimaEntity lote = vinculo.getLoteMateriaPrima();

            if (lote != null) {
                lote.getRegistrosDeProducao().remove(vinculo);
            }

            registro.getLotesMateriaPrima().remove(vinculo);
            entityManager.remove(vinculo);
        }

        // Cria os novos vínculos.
        if (dtos == null) {
            return;
        }

        for (LoteMateriaPrimaRegistroDeProducaoRequestDTO dto : dtos) {

            LoteMateriaPrimaEntity lote = loteMateriaPrimaRepository
                    .findById(dto.getIdLoteMateriaPrima())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Lote de matéria-prima não encontrado com o ID: "
                                    + dto.getIdLoteMateriaPrima()));

            LoteMateriaPrimaRegistroDeProducaoEntity vinculo =
                    new LoteMateriaPrimaRegistroDeProducaoEntity(
                            lote,
                            registro,
                            dto.getQuantidade());

            entityManager.persist(vinculo);
        }
    }

    private void substituirLotesInsumos(
            RegistroDeProducaoEntity registro,
            List<LoteInsumoRegistroDeProducaoRequestDTO> dtos) {

        // Remove os vínculos anteriores.
        for (LoteInsumoRegistroDeProducaoEntity vinculo
                : new ArrayList<>(registro.getLotesInsumos())) {

            LoteInsumoEntity lote = vinculo.getLoteInsumo();

            if (lote != null) {
                lote.getRegistrosDeProducao().remove(vinculo);
            }

            registro.getLotesInsumos().remove(vinculo);
            entityManager.remove(vinculo);
        }

        // Cria os novos vínculos.
        if (dtos == null) {
            return;
        }

        for (LoteInsumoRegistroDeProducaoRequestDTO dto : dtos) {

            LoteInsumoEntity lote = loteInsumoRepository
                    .findById(dto.getIdLoteInsumo())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Lote de insumo não encontrado com o ID: "
                                    + dto.getIdLoteInsumo()));

            LoteInsumoRegistroDeProducaoEntity vinculo =
                    new LoteInsumoRegistroDeProducaoEntity(
                            lote,
                            registro,
                            dto.getQuantidade());

            entityManager.persist(vinculo);
        }
    }
}