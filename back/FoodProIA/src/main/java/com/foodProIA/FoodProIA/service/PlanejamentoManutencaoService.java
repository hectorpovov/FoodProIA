package com.foodProIA.FoodProIA.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodProIA.FoodProIA.dto.request.PlanejamentoManutencaoRequestDTO;
import com.foodProIA.FoodProIA.dto.response.PlanejamentoManutencaoResponseDTO;
import com.foodProIA.FoodProIA.entity.PlanejamentoManutencaoEntity;
import com.foodProIA.FoodProIA.repository.PlanejamentoManutencaoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanejamentoManutencaoService {

    private final PlanejamentoManutencaoRepository planejamentoRepository;

    public PlanejamentoManutencaoResponseDTO criarPlanejamento(
            PlanejamentoManutencaoRequestDTO dto) {

        PlanejamentoManutencaoEntity planejamento =
                new PlanejamentoManutencaoEntity();

        preencherDados(planejamento, dto);

        planejamento = planejamentoRepository.save(planejamento);

        return new PlanejamentoManutencaoResponseDTO(planejamento);
    }

    public PlanejamentoManutencaoResponseDTO alterarPlanejamento(
            Long id,
            PlanejamentoManutencaoRequestDTO dto) {

        PlanejamentoManutencaoEntity planejamento =
                planejamentoRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Planejamento de manutenção não encontrado com o ID: "
                                        + id));

        preencherDados(planejamento, dto);

        planejamento = planejamentoRepository.save(planejamento);

        return new PlanejamentoManutencaoResponseDTO(planejamento);
    }

    @Transactional(readOnly = true)
    public PlanejamentoManutencaoResponseDTO buscarPlanejamentoPorId(
            Long id) {

        PlanejamentoManutencaoEntity planejamento =
                planejamentoRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Planejamento de manutenção não encontrado com o ID: "
                                        + id));

        return new PlanejamentoManutencaoResponseDTO(planejamento);
    }

    @Transactional(readOnly = true)
    public List<PlanejamentoManutencaoResponseDTO> listarPlanejamentos() {

        return planejamentoRepository.findAll()
                .stream()
                .map(PlanejamentoManutencaoResponseDTO::new)
                .toList();
    }

    public void excluirPlanejamento(Long id) {

        PlanejamentoManutencaoEntity planejamento =
                planejamentoRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Planejamento de manutenção não encontrado com o ID: "
                                        + id));

        planejamentoRepository.delete(planejamento);
    }

    private void preencherDados(
            PlanejamentoManutencaoEntity planejamento,
            PlanejamentoManutencaoRequestDTO dto) {

        planejamento.setData(dto.getData());

        planejamento.setNumeroDeSerieEquipamento(
                dto.getNumeroDeSerieEquipamento());

        planejamento.setTempoParadoEstipulado(
                dto.getTempoParadoEstipulado());

        planejamento.setDiasParadoEstipulado(
                dto.getDiasParadoEstipulado());

        planejamento.setMesesParadoEstipulado(
                dto.getMesesParadoEstipulado());

        planejamento.setComentario(dto.getComentario());
    }
}