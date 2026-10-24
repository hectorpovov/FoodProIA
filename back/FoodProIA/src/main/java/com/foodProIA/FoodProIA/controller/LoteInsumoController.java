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

import com.foodProIA.FoodProIA.dto.request.LoteInsumoRequestDTO;
import com.foodProIA.FoodProIA.dto.response.LoteInsumoResponseDTO;
import com.foodProIA.FoodProIA.service.LoteInsumoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/lotes-insumos")
@RequiredArgsConstructor
public class LoteInsumoController {

    private final LoteInsumoService loteInsumoService;

    @PostMapping
    public ResponseEntity<LoteInsumoResponseDTO> criar(
            @Valid @RequestBody LoteInsumoRequestDTO dto) {

        LoteInsumoResponseDTO lote = loteInsumoService.criar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(lote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoteInsumoResponseDTO> alterar(
            @PathVariable Long id,
            @Valid @RequestBody LoteInsumoRequestDTO dto) {

        LoteInsumoResponseDTO lote = loteInsumoService.alterar(id, dto);

        return ResponseEntity.ok(lote);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoteInsumoResponseDTO> buscarPorId(
            @PathVariable Long id) {

        LoteInsumoResponseDTO lote = loteInsumoService.buscarPorId(id);

        return ResponseEntity.ok(lote);
    }

    @GetMapping
    public ResponseEntity<List<LoteInsumoResponseDTO>> listarTodos() {

        List<LoteInsumoResponseDTO> lotes = loteInsumoService.listarTodos();

        return ResponseEntity.ok(lotes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        loteInsumoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}