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

import com.foodProIA.FoodProIA.dto.request.ParadaRequestDTO;
import com.foodProIA.FoodProIA.dto.response.ParadaResponseDTO;
import com.foodProIA.FoodProIA.service.ParadaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/paradas")
@RequiredArgsConstructor
public class ParadaController {

    private final ParadaService paradaService;

    @PostMapping
    public ResponseEntity<ParadaResponseDTO> criar(
            @Valid @RequestBody ParadaRequestDTO dto) {

        ParadaResponseDTO parada = paradaService.criar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(parada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParadaResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ParadaRequestDTO dto) {

        ParadaResponseDTO parada = paradaService.atualizar(id, dto);

        return ResponseEntity.ok(parada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParadaResponseDTO> buscarPorId(
            @PathVariable Long id) {

        ParadaResponseDTO parada = paradaService.buscarPorId(id);

        return ResponseEntity.ok(parada);
    }

    @GetMapping
    public ResponseEntity<List<ParadaResponseDTO>> listarTodos() {

        List<ParadaResponseDTO> paradas = paradaService.listarTodos();

        return ResponseEntity.ok(paradas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        paradaService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}