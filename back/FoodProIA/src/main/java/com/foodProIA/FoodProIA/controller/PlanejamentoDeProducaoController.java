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

import com.foodProIA.FoodProIA.dto.request.PlanejamentoDeProducaoRequestDTO;
import com.foodProIA.FoodProIA.dto.response.PlanejamentoDeProducaoResponseDTO;
import com.foodProIA.FoodProIA.service.PlanejamentoDeProducaoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/planejamentos-de-producao")
@RequiredArgsConstructor
public class PlanejamentoDeProducaoController {

    private final PlanejamentoDeProducaoService planejamentoService;

    @PostMapping
    public ResponseEntity<PlanejamentoDeProducaoResponseDTO> criarPlanejamento(
            @Valid @RequestBody PlanejamentoDeProducaoRequestDTO dto) {

        PlanejamentoDeProducaoResponseDTO planejamento =
                planejamentoService.criarPlanejamento(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(planejamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanejamentoDeProducaoResponseDTO> alterarPlanejamento(
            @PathVariable Long id,
            @Valid @RequestBody PlanejamentoDeProducaoRequestDTO dto) {

        PlanejamentoDeProducaoResponseDTO planejamento =
                planejamentoService.alterarPlanejamento(id, dto);

        return ResponseEntity.ok(planejamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanejamentoDeProducaoResponseDTO> buscarPlanejamentoPorId(
            @PathVariable Long id) {

        PlanejamentoDeProducaoResponseDTO planejamento =
                planejamentoService.buscarPlanejamentoPorId(id);

        return ResponseEntity.ok(planejamento);
    }

    @GetMapping
    public ResponseEntity<List<PlanejamentoDeProducaoResponseDTO>> listarPlanejamentos() {

        List<PlanejamentoDeProducaoResponseDTO> planejamentos =
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