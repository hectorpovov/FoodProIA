
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

import com.foodProIA.FoodProIA.dto.request.FraudeRequestDTO;
import com.foodProIA.FoodProIA.dto.request.TesteDeQualidadeRequestDTO;
import com.foodProIA.FoodProIA.dto.response.TesteDeQualidadeResponseDTO;
import com.foodProIA.FoodProIA.service.TesteDeQualidadeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/teste-de-qualidade")
@RequiredArgsConstructor
public class TesteDeQualidadeController {

    private final TesteDeQualidadeService testeDeQualidadeService;


    // =========================================================
    // INSERIR
    // =========================================================

    @PostMapping
    public ResponseEntity<TesteDeQualidadeResponseDTO> inserir(
            @Valid @RequestBody TesteDeQualidadeRequestDTO dto) {

        TesteDeQualidadeResponseDTO resposta =
                testeDeQualidadeService.inserir(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resposta);
    }


    // =========================================================
    // ALTERAR
    // =========================================================

    @PutMapping("/{id}")
    public ResponseEntity<TesteDeQualidadeResponseDTO> alterar(
            @PathVariable Long id,
            @Valid @RequestBody TesteDeQualidadeRequestDTO dto) {

        TesteDeQualidadeResponseDTO resposta =
                testeDeQualidadeService.alterar(id, dto);

        return ResponseEntity.ok(resposta);
    }


    // =========================================================
    // BUSCAR POR ID
    // =========================================================

    @GetMapping("/{id}")
    public ResponseEntity<TesteDeQualidadeResponseDTO> buscarPorId(
            @PathVariable Long id) {

        TesteDeQualidadeResponseDTO resposta =
                testeDeQualidadeService.buscarPorId(id);

        return ResponseEntity.ok(resposta);
    }


    // =========================================================
    // EXCLUIR
    // =========================================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        testeDeQualidadeService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    // =========================================================
    // ADICIONAR FRAUDE
    // =========================================================

    @PostMapping("/{id}/fraudes")
    public ResponseEntity<TesteDeQualidadeResponseDTO> adicionarFraude(
            @PathVariable Long id,
            @Valid @RequestBody FraudeRequestDTO dto) {

        TesteDeQualidadeResponseDTO resposta =
                testeDeQualidadeService.adicionarFraude(
                        id,
                        dto
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resposta);
    }


    // =========================================================
    // REMOVER FRAUDE
    // =========================================================

    @DeleteMapping("/{testeId}/fraudes/{fraudeId}")
    public ResponseEntity<TesteDeQualidadeResponseDTO> removerFraude(
            @PathVariable Long testeId,
            @PathVariable Long fraudeId) {

        TesteDeQualidadeResponseDTO resposta =
                testeDeQualidadeService.removerFraude(
                        testeId,
                        fraudeId
                );

        return ResponseEntity.ok(resposta);
    }
}
