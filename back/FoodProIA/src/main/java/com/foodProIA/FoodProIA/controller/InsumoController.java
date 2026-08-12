package com.foodProIA.FoodProIA.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodProIA.FoodProIA.dto.request.InsumoRequestDTO;
import com.foodProIA.FoodProIA.dto.response.InsumoResponseDTO;
import com.foodProIA.FoodProIA.service.InsumoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/insumo")
@RequiredArgsConstructor
public class InsumoController {

    private final InsumoService insumoService;


    // =========================================================
    // INSERIR
    // =========================================================

    @PostMapping
    public ResponseEntity<InsumoResponseDTO> inserir(
            @Valid @RequestBody InsumoRequestDTO dto) {

        InsumoResponseDTO response =
                insumoService.inserir(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    // =========================================================
    // ALTERAR
    // =========================================================

    @PutMapping("/{id}")
    public ResponseEntity<InsumoResponseDTO> alterar(
            @PathVariable Long id,
            @Valid @RequestBody InsumoRequestDTO dto) {

        InsumoResponseDTO response =
                insumoService.alterar(id, dto);

        return ResponseEntity.ok(response);
    }


    // =========================================================
    // EXCLUIR
    // =========================================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        insumoService.excluir(id);

        return ResponseEntity.noContent().build();
    }


    // =========================================================
    // BUSCAR POR ID
    // =========================================================

    @GetMapping("/{id}")
    public ResponseEntity<InsumoResponseDTO> buscarPorId(
            @PathVariable Long id) {

        InsumoResponseDTO response =
                insumoService.buscarPorId(id);

        return ResponseEntity.ok(response);
    }
}