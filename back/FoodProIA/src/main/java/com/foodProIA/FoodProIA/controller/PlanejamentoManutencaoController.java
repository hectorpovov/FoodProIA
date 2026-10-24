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

import com.foodProIA.FoodProIA.dto.request.PlanejamentoManutencaoRequestDTO;
import com.foodProIA.FoodProIA.dto.response.PlanejamentoManutencaoResponseDTO;
import com.foodProIA.FoodProIA.service.PlanejamentoManutencaoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/planejamentos-manutencao")
@RequiredArgsConstructor
public class PlanejamentoManutencaoController {

    private final PlanejamentoManutencaoService planejamentoService;

    @PostMapping
    public ResponseEntity<PlanejamentoManutencaoResponseDTO> criarPlanejamento(
            @Valid @RequestBody PlanejamentoManutencaoRequestDTO dto) {

        PlanejamentoManutencaoResponseDTO planejamento =
                planejamentoService.criarPlanejamento(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(planejamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanejamentoManutencaoResponseDTO> alterarPlanejamento(
            @PathVariable Long id,
            @Valid @RequestBody PlanejamentoManutencaoRequestDTO dto) {

        PlanejamentoManutencaoResponseDTO planejamento =
                planejamentoService.alterarPlanejamento(id, dto);

        return ResponseEntity.ok(planejamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanejamentoManutencaoResponseDTO> buscarPlanejamentoPorId(
            @PathVariable Long id) {

        PlanejamentoManutencaoResponseDTO planejamento =
                planejamentoService.buscarPlanejamentoPorId(id);

        return ResponseEntity.ok(planejamento);
    }

    @GetMapping
    public ResponseEntity<List<PlanejamentoManutencaoResponseDTO>> listarPlanejamentos() {

        List<PlanejamentoManutencaoResponseDTO> planejamentos =
                planejamentoService.listarPlanejamentos();

        return ResponseEntity.ok(planejamentos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPlanejamento(
            @PathVariable Long id) {

        planejamentoService.excluirPlanejamento(id);

        return ResponseEntity.noContent().build();
    }
}