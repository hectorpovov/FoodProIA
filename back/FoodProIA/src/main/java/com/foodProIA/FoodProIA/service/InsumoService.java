package com.foodProIA.FoodProIA.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodProIA.FoodProIA.dto.request.InsumoRequestDTO;
import com.foodProIA.FoodProIA.dto.response.InsumoResponseDTO;
import com.foodProIA.FoodProIA.entity.InsumoEntity;
import com.foodProIA.FoodProIA.repository.InsumoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InsumoService {

    private final InsumoRepository insumoRepository;


    // =========================================================
    // INSERIR
    // =========================================================

    @Transactional
    public InsumoResponseDTO inserir(InsumoRequestDTO dto) {

        InsumoEntity insumo = new InsumoEntity();

        insumo.setNome(dto.getNome());
        insumo.setTipoInsumo(dto.getTipoInsumo());

        insumo = insumoRepository.save(insumo);

        return new InsumoResponseDTO(insumo);
    }


    // =========================================================
    // ALTERAR
    // =========================================================

    @Transactional
    public InsumoResponseDTO alterar(
            Long id,
            InsumoRequestDTO dto) {

        InsumoEntity insumo =
                insumoRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Insumo não encontrado: " + id
                                )
                        );

        insumo.setNome(dto.getNome());
        insumo.setTipoInsumo(dto.getTipoInsumo());

        insumo = insumoRepository.save(insumo);

        return new InsumoResponseDTO(insumo);
    }


    // =========================================================
    // EXCLUIR
    // =========================================================

    @Transactional
    public void excluir(Long id) {

        InsumoEntity insumo =
                insumoRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Insumo não encontrado: " + id
                                )
                        );

        /*
         * As relações InsumoRoteiroEntity serão excluídas
         * pelo cascade + orphanRemoval configurados na entidade.
         */
        insumoRepository.delete(insumo);
    }


    // =========================================================
    // BUSCAR POR ID
    // =========================================================

    @Transactional(readOnly = true)
    public InsumoResponseDTO buscarPorId(Long id) {

        InsumoEntity insumo =
                insumoRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Insumo não encontrado: " + id
                                )
                        );

        return new InsumoResponseDTO(insumo);
    }
}
