package com.foodProIA.FoodProIA.service;

import com.foodProIA.FoodProIA.dto.request.InformacaoNutricionalRequestDTO;
import com.foodProIA.FoodProIA.dto.response.InformacaoNutricionalResponseDTO;
import com.foodProIA.FoodProIA.entity.InformacaoNutricionalEntity;
import com.foodProIA.FoodProIA.repository.InformacaoNutricionalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InformacaoNutricionalService {

    private final InformacaoNutricionalRepository
            informacaoNutricionalRepository;


    // =========================
    // CRIAR
    // =========================

    @Transactional
    public InformacaoNutricionalResponseDTO criar(
            InformacaoNutricionalRequestDTO dto) {

        InformacaoNutricionalEntity informacao =
                new InformacaoNutricionalEntity();

        informacao.setNome(dto.getNome());
        informacao.setUnidadeDeMedida(dto.getUnidadeDeMedida());

        informacaoNutricionalRepository.save(informacao);

        return new InformacaoNutricionalResponseDTO(informacao);
    }


    // =========================
    // ALTERAR
    // =========================

    @Transactional
    public InformacaoNutricionalResponseDTO alterar(
            Long id,
            InformacaoNutricionalRequestDTO dto) {

        InformacaoNutricionalEntity informacao =
                informacaoNutricionalRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Informação nutricional não encontrada"
                                )
                        );

        informacao.setNome(dto.getNome());
        informacao.setUnidadeDeMedida(dto.getUnidadeDeMedida());

        return new InformacaoNutricionalResponseDTO(informacao);
    }


    // =========================
    // EXCLUIR
    // =========================

    @Transactional
    public void excluir(Long id) {

        InformacaoNutricionalEntity informacao =
                informacaoNutricionalRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Informação nutricional não encontrada"
                                )
                        );

        informacaoNutricionalRepository.delete(informacao);
    }


    // =========================
    // BUSCAR POR ID
    // =========================

    @Transactional(readOnly = true)
    public InformacaoNutricionalResponseDTO buscarPorId(Long id) {

        InformacaoNutricionalEntity informacao =
                informacaoNutricionalRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Informação nutricional não encontrada"
                                )
                        );

        return new InformacaoNutricionalResponseDTO(informacao);
    }


    // =========================
    // LISTAR TODOS
    // =========================

    @Transactional(readOnly = true)
    public List<InformacaoNutricionalResponseDTO> listarTodos() {

        return informacaoNutricionalRepository.findAll()
                .stream()
                .map(InformacaoNutricionalResponseDTO::new)
                .toList();
    }

}