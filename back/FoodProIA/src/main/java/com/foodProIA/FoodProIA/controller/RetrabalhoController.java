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

import com.foodProIA.FoodProIA.dto.request.RetrabalhoRequestDTO;
import com.foodProIA.FoodProIA.dto.response.RetrabalhoResponseDTO;
import com.foodProIA.FoodProIA.service.RetrabalhoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/retrabalhos")
@RequiredArgsConstructor
public class RetrabalhoController {

    private final RetrabalhoService retrabalhoService;

    @PostMapping
    public ResponseEntity<RetrabalhoResponseDTO> criar(
            @Valid @RequestBody RetrabalhoRequestDTO dto) {

        RetrabalhoResponseDTO retrabalho = retrabalhoService.criar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(retrabalho);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RetrabalhoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody RetrabalhoRequestDTO dto) {

        RetrabalhoResponseDTO retrabalho = retrabalhoService.atualizar(id, dto);

        return ResponseEntity.ok(retrabalho);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RetrabalhoResponseDTO> buscarPorId(
            @PathVariable Long id) {

        RetrabalhoResponseDTO retrabalho = retrabalhoService.buscarPorId(id);

        return ResponseEntity.ok(retrabalho);
    }

    @GetMapping
    public ResponseEntity<List<RetrabalhoResponseDTO>> listarTodos() {

        List<RetrabalhoResponseDTO> retrabalhos = retrabalhoService.listarTodos();

        return ResponseEntity.ok(retrabalhos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        retrabalhoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}