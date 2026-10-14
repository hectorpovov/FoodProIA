package com.foodProIA.FoodProIA.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodProIA.FoodProIA.dto.request.ParadaRequestDTO;
import com.foodProIA.FoodProIA.dto.response.ParadaResponseDTO;
import com.foodProIA.FoodProIA.entity.EtapaEntity;
import com.foodProIA.FoodProIA.entity.ParadaEntity;
import com.foodProIA.FoodProIA.entity.RegistroDeProducaoEntity;
import com.foodProIA.FoodProIA.repository.EtapaRepository;
import com.foodProIA.FoodProIA.repository.ParadaRepository;
import com.foodProIA.FoodProIA.repository.RegistroDeProducaoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ParadaService {

    private final ParadaRepository paradaRepository;
    private final RegistroDeProducaoRepository registroDeProducaoRepository;
    private final EtapaRepository etapaRepository;

    public ParadaResponseDTO criar(ParadaRequestDTO dto) {

        ParadaEntity parada = new ParadaEntity();

        preencherDados(parada, dto);

        parada = paradaRepository.save(parada);

        return new ParadaResponseDTO(parada);
    }

    @Transactional(readOnly = true)
    public ParadaResponseDTO buscarPorId(Long id) {

        ParadaEntity parada = buscarEntidadePorId(id);

        return new ParadaResponseDTO(parada);
    }

    @Transactional(readOnly = true)
    public List<ParadaResponseDTO> listarTodos() {

        return paradaRepository.findAll()
                .stream()
                .map(ParadaResponseDTO::new)
                .toList();
    }

    public ParadaResponseDTO atualizar(
            Long id,
            ParadaRequestDTO dto) {

        ParadaEntity parada = buscarEntidadePorId(id);

        preencherDados(parada, dto);

        parada = paradaRepository.save(parada);

        return new ParadaResponseDTO(parada);
    }

    public void excluir(Long id) {

        ParadaEntity parada = buscarEntidadePorId(id);

        paradaRepository.delete(parada);
    }

    private ParadaEntity buscarEntidadePorId(Long id) {

        return paradaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Parada não encontrada com ID: " + id));
    }

    private void preencherDados(
            ParadaEntity parada,
            ParadaRequestDTO dto) {

        parada.setData(dto.getData());
        parada.setTempoGasto(dto.getTempoGasto());
        parada.setComentario(dto.getComentario());

        parada.setRegistroDeProducao(
                buscarRegistroDeProducao(dto.getIdRegistroDeProducao()));

        parada.setEtapa(
                buscarEtapa(dto.getIdEtapa()));
    }

    private RegistroDeProducaoEntity buscarRegistroDeProducao(Long id) {

        return registroDeProducaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Registro de produção não encontrado com ID: " + id));
    }

    private EtapaEntity buscarEtapa(Long id) {

        return etapaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Etapa não encontrada com ID: " + id));
    }
}