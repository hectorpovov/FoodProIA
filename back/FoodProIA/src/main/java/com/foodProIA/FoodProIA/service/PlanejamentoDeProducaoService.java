package com.foodProIA.FoodProIA.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodProIA.FoodProIA.dto.request.PlanejamentoDeProducaoRequestDTO;
import com.foodProIA.FoodProIA.dto.response.PlanejamentoDeProducaoResponseDTO;
import com.foodProIA.FoodProIA.entity.EquipamentoEntity;
import com.foodProIA.FoodProIA.entity.PlanejamentoDeProducaoEntity;
import com.foodProIA.FoodProIA.entity.ProdutoEntity;
import com.foodProIA.FoodProIA.entity.RoteiroDeProducaoEntity;
import com.foodProIA.FoodProIA.entity.TurnoEntity;
import com.foodProIA.FoodProIA.repository.EquipamentoRepository;
import com.foodProIA.FoodProIA.repository.PlanejamentoDeProducaoRepository;
import com.foodProIA.FoodProIA.repository.ProdutoRepository;
import com.foodProIA.FoodProIA.repository.RoteiroDeProducaoRepository;
import com.foodProIA.FoodProIA.repository.TurnoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanejamentoDeProducaoService {

    private final PlanejamentoDeProducaoRepository planejamentoRepository;
    private final TurnoRepository turnoRepository;
    private final ProdutoRepository produtoRepository;
    private final RoteiroDeProducaoRepository roteiroRepository;
    private final EquipamentoRepository equipamentoRepository;

    public PlanejamentoDeProducaoResponseDTO criarPlanejamento(
            PlanejamentoDeProducaoRequestDTO dto) {

        PlanejamentoDeProducaoEntity planejamento =
                new PlanejamentoDeProducaoEntity();

        preencherDados(planejamento, dto);

        planejamento = planejamentoRepository.save(planejamento);

        return new PlanejamentoDeProducaoResponseDTO(planejamento);
    }

    public PlanejamentoDeProducaoResponseDTO alterarPlanejamento(
            Long id,
            PlanejamentoDeProducaoRequestDTO dto) {

        PlanejamentoDeProducaoEntity planejamento =
                planejamentoRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Planejamento de produção não encontrado com o ID: "
                                        + id));

        preencherDados(planejamento, dto);

        planejamento = planejamentoRepository.save(planejamento);

        return new PlanejamentoDeProducaoResponseDTO(planejamento);
    }

    @Transactional(readOnly = true)
    public PlanejamentoDeProducaoResponseDTO buscarPlanejamentoPorId(
            Long id) {

        PlanejamentoDeProducaoEntity planejamento =
                planejamentoRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Planejamento de produção não encontrado com o ID: "
                                        + id));

        return new PlanejamentoDeProducaoResponseDTO(planejamento);
    }

    @Transactional(readOnly = true)
    public List<PlanejamentoDeProducaoResponseDTO> listarPlanejamentos() {

        return planejamentoRepository.findAll()
                .stream()
                .map(PlanejamentoDeProducaoResponseDTO::new)
                .toList();
    }

    public void excluirPlanejamento(Long id) {

        PlanejamentoDeProducaoEntity planejamento =
                planejamentoRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Planejamento de produção não encontrado com o ID: "
                                        + id));

        planejamentoRepository.delete(planejamento);
    }

    private void preencherDados(
            PlanejamentoDeProducaoEntity planejamento,
            PlanejamentoDeProducaoRequestDTO dto) {

        planejamento.setQuantidadePlanejada(dto.getQuantidadePlanejada());
        planejamento.setStatus(dto.getStatus());
        planejamento.setDataInicio(dto.getDataInicio());
        planejamento.setPrazo(dto.getPrazo());

        // Turno: opcional
        if (dto.getIdTurno() != null) {
            TurnoEntity turno = turnoRepository
                    .findById(dto.getIdTurno())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Turno não encontrado com o ID: "
                                    + dto.getIdTurno()));

            planejamento.setTurno(turno);
        } else {
            planejamento.setTurno(null);
        }

        // Produto: obrigatório
        ProdutoEntity produto = produtoRepository
                .findById(dto.getIdProduto())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Produto não encontrado com o ID: "
                                + dto.getIdProduto()));

        planejamento.setProduto(produto);

        // Roteiro: opcional
        if (dto.getIdRoteiro() != null) {
            RoteiroDeProducaoEntity roteiro = roteiroRepository
                    .findById(dto.getIdRoteiro())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Roteiro de produção não encontrado com o ID: "
                                    + dto.getIdRoteiro()));

            planejamento.setRoteiro(roteiro);
        } else {
            planejamento.setRoteiro(null);
        }

        // Equipamentos: associação com equipamentos existentes
        List<EquipamentoEntity> equipamentos = new ArrayList<>();

        if (dto.getIdsEquipamentos() != null) {
            for (Long idEquipamento : dto.getIdsEquipamentos()) {
                EquipamentoEntity equipamento = equipamentoRepository
                        .findById(idEquipamento)
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Equipamento não encontrado com o ID: "
                                        + idEquipamento));

                if (equipamentos.contains(equipamento)) {
                    throw new IllegalArgumentException(
                            "O equipamento com o ID " + idEquipamento
                                    + " foi informado mais de uma vez.");
                }

                equipamentos.add(equipamento);
            }
        }

        planejamento.setEquipamentos(equipamentos);
    }
}