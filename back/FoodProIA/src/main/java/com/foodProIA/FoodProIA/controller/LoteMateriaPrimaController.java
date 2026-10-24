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

import com.foodProIA.FoodProIA.dto.request.LoteMateriaPrimaRequestDTO;
import com.foodProIA.FoodProIA.dto.response.LoteMateriaPrimaResponseDTO;
import com.foodProIA.FoodProIA.service.LoteMateriaPrimaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/lotes-materias-primas")
@RequiredArgsConstructor
public class LoteMateriaPrimaController {

    private final LoteMateriaPrimaService loteMateriaPrimaService;

    @PostMapping
    public ResponseEntity<LoteMateriaPrimaResponseDTO> criar(
            @Valid @RequestBody LoteMateriaPrimaRequestDTO dto) {

        LoteMateriaPrimaResponseDTO lote = loteMateriaPrimaService.criar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(lote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoteMateriaPrimaResponseDTO> alterar(
            @PathVariable Long id,
            @Valid @RequestBody LoteMateriaPrimaRequestDTO dto) {

        LoteMateriaPrimaResponseDTO lote =
                loteMateriaPrimaService.alterar(id, dto);

        return ResponseEntity.ok(lote);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoteMateriaPrimaResponseDTO> buscarPorId(
            @PathVariable Long id) {

        LoteMateriaPrimaResponseDTO lote =
                loteMateriaPrimaService.buscarPorId(id);

        return ResponseEntity.ok(lote);
    }

    @GetMapping
    public ResponseEntity<List<LoteMateriaPrimaResponseDTO>> listarTodos() {

        List<LoteMateriaPrimaResponseDTO> lotes =
                loteMateriaPrimaService.listarTodos();

        return ResponseEntity.ok(lotes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        loteMateriaPrimaService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}