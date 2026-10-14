package com.foodProIA.FoodProIA.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodProIA.FoodProIA.dto.request.EquipamentoRequestDTO;
import com.foodProIA.FoodProIA.dto.response.EquipamentoResponseDTO;
import com.foodProIA.FoodProIA.entity.EquipamentoEntity;
import com.foodProIA.FoodProIA.repository.EquipamentoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;

    public EquipamentoResponseDTO criarEquipamento(
            EquipamentoRequestDTO dto) {

        EquipamentoEntity equipamento = new EquipamentoEntity();

        equipamento.setNumeroDeSerie(dto.getNumeroDeSerie());
        equipamento.setFabricante(dto.getFabricante());
        equipamento.setDataCompra(dto.getDataCompra());
        equipamento.setCapacidade(dto.getCapacidade());
        equipamento.setPotencia(dto.getPotencia());
        equipamento.setConsumoEnergetico(dto.getConsumoEnergetico());
        equipamento.setVelocidadeDeOperacao(dto.getVelocidadeDeOperacao());
        equipamento.setTempoEntreManutencoes(dto.getTempoEntreManutencoes());

        equipamento = equipamentoRepository.save(equipamento);

        return new EquipamentoResponseDTO(equipamento);
    }

    public EquipamentoResponseDTO alterarEquipamento(
            Long id,
            EquipamentoRequestDTO dto) {

        EquipamentoEntity equipamento = equipamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Equipamento não encontrado com o ID: " + id));

        equipamento.setNumeroDeSerie(dto.getNumeroDeSerie());
        equipamento.setFabricante(dto.getFabricante());
        equipamento.setDataCompra(dto.getDataCompra());
        equipamento.setCapacidade(dto.getCapacidade());
        equipamento.setPotencia(dto.getPotencia());
        equipamento.setConsumoEnergetico(dto.getConsumoEnergetico());
        equipamento.setVelocidadeDeOperacao(dto.getVelocidadeDeOperacao());
        equipamento.setTempoEntreManutencoes(dto.getTempoEntreManutencoes());

        equipamento = equipamentoRepository.save(equipamento);

        return new EquipamentoResponseDTO(equipamento);
    }

    @Transactional(readOnly = true)
    public EquipamentoResponseDTO buscarEquipamentoPorId(Long id) {

        EquipamentoEntity equipamento = equipamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Equipamento não encontrado com o ID: " + id));

        return new EquipamentoResponseDTO(equipamento);
    }

    @Transactional(readOnly = true)
    public List<EquipamentoResponseDTO> listarEquipamentos() {

        return equipamentoRepository.findAll()
                .stream()
                .map(EquipamentoResponseDTO::new)
                .toList();
    }

    public void excluirEquipamento(Long id) {

        EquipamentoEntity equipamento = equipamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Equipamento não encontrado com o ID: " + id));

        if (!equipamento.getTiposEtapas().isEmpty()) {
            throw new IllegalStateException(
                    "Não é possível excluir o equipamento, pois ele está associado a tipos de etapa.");
        }

        if (!equipamento.getPlanejamentosDeProducao().isEmpty()) {
            throw new IllegalStateException(
                    "Não é possível excluir o equipamento, pois ele está associado a planejamentos de produção.");
        }

        equipamentoRepository.delete(equipamento);
    }
}