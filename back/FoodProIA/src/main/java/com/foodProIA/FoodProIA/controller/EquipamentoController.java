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

import com.foodProIA.FoodProIA.dto.request.EquipamentoRequestDTO;
import com.foodProIA.FoodProIA.dto.response.EquipamentoResponseDTO;
import com.foodProIA.FoodProIA.service.EquipamentoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/equipamentos")
@RequiredArgsConstructor
public class EquipamentoController {

    private final EquipamentoService equipamentoService;

    @PostMapping
    public ResponseEntity<EquipamentoResponseDTO> criarEquipamento(
            @Valid @RequestBody EquipamentoRequestDTO dto) {

        EquipamentoResponseDTO equipamento =
                equipamentoService.criarEquipamento(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(equipamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipamentoResponseDTO> alterarEquipamento(
            @PathVariable Long id,
            @Valid @RequestBody EquipamentoRequestDTO dto) {

        EquipamentoResponseDTO equipamento =
                equipamentoService.alterarEquipamento(id, dto);

        return ResponseEntity.ok(equipamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipamentoResponseDTO> buscarEquipamentoPorId(
            @PathVariable Long id) {

        EquipamentoResponseDTO equipamento =
                equipamentoService.buscarEquipamentoPorId(id);

        return ResponseEntity.ok(equipamento);
    }

    @GetMapping
    public ResponseEntity<List<EquipamentoResponseDTO>> listarEquipamentos() {

        List<EquipamentoResponseDTO> equipamentos =
                equipamentoService.listarEquipamentos();

        return ResponseEntity.ok(equipamentos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEquipamento(
            @PathVariable Long id) {

        equipamentoService.excluirEquipamento(id);

        return ResponseEntity.noContent().build();
    }
}