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

import com.foodProIA.FoodProIA.dto.request.RegistroManutencaoRequestDTO;
import com.foodProIA.FoodProIA.dto.response.RegistroManutencaoResponseDTO;
import com.foodProIA.FoodProIA.service.RegistroManutencaoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/registros-manutencao")
@RequiredArgsConstructor
public class RegistroManutencaoController {

    private final RegistroManutencaoService registroManutencaoService;

    @PostMapping
    public ResponseEntity<RegistroManutencaoResponseDTO> criar(
            @Valid @RequestBody RegistroManutencaoRequestDTO dto) {

        RegistroManutencaoResponseDTO registro =
                registroManutencaoService.criar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroManutencaoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody RegistroManutencaoRequestDTO dto) {

        RegistroManutencaoResponseDTO registro =
                registroManutencaoService.atualizar(id, dto);

        return ResponseEntity.ok(registro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroManutencaoResponseDTO> buscarPorId(
            @PathVariable Long id) {

        RegistroManutencaoResponseDTO registro =
                registroManutencaoService.buscarPorId(id);

        return ResponseEntity.ok(registro);
    }

    @GetMapping
    public ResponseEntity<List<RegistroManutencaoResponseDTO>> listarTodos() {

        List<RegistroManutencaoResponseDTO> registros =
                registroManutencaoService.listarTodos();

        return ResponseEntity.ok(registros);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        registroManutencaoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}