package com.foodProIA.FoodProIA.controller;

import java.util.List;

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

import com.foodProIA.FoodProIA.dto.request.MateriaPrimaRequestDTO;
import com.foodProIA.FoodProIA.dto.response.MateriaPrimaResponseDTO;
import com.foodProIA.FoodProIA.service.MateriaPrimaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/materias-primas")
@RequiredArgsConstructor
public class MateriaPrimaController {

    private final MateriaPrimaService materiaPrimaService;

    @PostMapping
    public ResponseEntity<MateriaPrimaResponseDTO> criar(
            @Valid @RequestBody MateriaPrimaRequestDTO dto) {

        MateriaPrimaResponseDTO materiaPrima =
                materiaPrimaService.criar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(materiaPrima);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MateriaPrimaResponseDTO> alterar(
            @PathVariable Long id,
            @Valid @RequestBody MateriaPrimaRequestDTO dto) {

        MateriaPrimaResponseDTO materiaPrima =
                materiaPrimaService.alterar(id, dto);

        return ResponseEntity.ok(materiaPrima);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaPrimaResponseDTO> buscarPorId(
            @PathVariable Long id) {

        MateriaPrimaResponseDTO materiaPrima =
                materiaPrimaService.buscarPorId(id);

        return ResponseEntity.ok(materiaPrima);
    }

    @GetMapping
    public ResponseEntity<List<MateriaPrimaResponseDTO>> listarTodos() {

        List<MateriaPrimaResponseDTO> materiasPrimas =
                materiaPrimaService.listarTodos();

        return ResponseEntity.ok(materiasPrimas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        materiaPrimaService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}