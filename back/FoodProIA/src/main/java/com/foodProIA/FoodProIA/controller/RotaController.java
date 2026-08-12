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

import com.foodProIA.FoodProIA.dto.request.ProgramacaoRotaRequestDTO;
import com.foodProIA.FoodProIA.dto.request.RotaRequestDTO;
import com.foodProIA.FoodProIA.dto.response.ProgramacaoRotaResponseDTO;
import com.foodProIA.FoodProIA.dto.response.RotaResponseDTO;
import com.foodProIA.FoodProIA.service.RotaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rota")
@RequiredArgsConstructor
public class RotaController {

    private final RotaService rotaService;


    // =========================================================
    // INSERIR ROTA
    // =========================================================

    @PostMapping
    public ResponseEntity<RotaResponseDTO> inserir(
            @RequestBody @Valid RotaRequestDTO dto) {

        RotaResponseDTO response =
                rotaService.inserir(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    // =========================================================
    // ALTERAR ROTA
    // =========================================================

    @PutMapping("/{id}")
    public ResponseEntity<RotaResponseDTO> alterar(
            @PathVariable Long id,
            @RequestBody @Valid RotaRequestDTO dto) {

        RotaResponseDTO response =
                rotaService.alterar(id, dto);

        return ResponseEntity.ok(response);
    }


    // =========================================================
    // EXCLUIR ROTA
    // =========================================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        rotaService.excluir(id);

        return ResponseEntity
                .noContent()
                .build();
    }


    // =========================================================
    // BUSCAR ROTA POR ID
    // =========================================================

    @GetMapping("/{id}")
    public ResponseEntity<RotaResponseDTO> buscarPorId(
            @PathVariable Long id) {

        RotaResponseDTO response =
                rotaService.buscarPorId(id);

        return ResponseEntity.ok(response);
    }


    // =========================================================
    // PROGRAMAR ROTA
    // =========================================================

    @PostMapping("/{rotaId}/programacao")
    public ResponseEntity<ProgramacaoRotaResponseDTO> programarRota(
            @PathVariable Long rotaId,
            @RequestBody @Valid ProgramacaoRotaRequestDTO dto) {

        /*
         * O id da rota vem na URL, então garantimos que o DTO
         * também está apontando para a mesma rota.
         */
        if (!rotaId.equals(dto.getIdRota())) {
            throw new IllegalArgumentException(
                    "O id da rota informado na URL não corresponde "
                    + "ao id da rota informado no corpo da requisição."
            );
        }

        ProgramacaoRotaResponseDTO response =
                rotaService.programarRota(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    // =========================================================
    // REMOVER PROGRAMAÇÃO
    // =========================================================

    @DeleteMapping("/{rotaId}/programacao/{programacaoId}")
    public ResponseEntity<Void> removerProgramacao(
            @PathVariable Long rotaId,
            @PathVariable Long programacaoId) {

        rotaService.removerProgramacao(
                rotaId,
                programacaoId
        );

        return ResponseEntity
                .noContent()
                .build();
    }


    // =========================================================
    // ALTERAR PROGRAMAÇÃO
    // =========================================================

    @PutMapping("/{rotaId}/programacao/{programacaoId}")
    public ResponseEntity<ProgramacaoRotaResponseDTO> alterarProgramacao(
            @PathVariable Long rotaId,
            @PathVariable Long programacaoId,
            @RequestBody @Valid ProgramacaoRotaRequestDTO dto) {

        ProgramacaoRotaResponseDTO response =
                rotaService.alterarProgramacao(
                        rotaId,
                        programacaoId,
                        dto
                );

        return ResponseEntity.ok(response);
    }
}

