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

import com.foodProIA.FoodProIA.dto.request.ModeloTesteRequestDTO;
import com.foodProIA.FoodProIA.dto.request.TipoParametroRequestDTO;
import com.foodProIA.FoodProIA.dto.response.ModeloTesteResponseDTO;
import com.foodProIA.FoodProIA.service.ModeloTesteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/modelo-teste")
@RequiredArgsConstructor
public class ModeloTesteController {

    private final ModeloTesteService modeloTesteService;


    // =========================================================
    // INSERIR
    // =========================================================

    @PostMapping
    public ResponseEntity<ModeloTesteResponseDTO> inserir(
            @Valid @RequestBody ModeloTesteRequestDTO dto) {

        ModeloTesteResponseDTO resposta =
                modeloTesteService.inserir(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resposta);
    }


    // =========================================================
    // ALTERAR
    // =========================================================

    @PutMapping("/{id}")
    public ResponseEntity<ModeloTesteResponseDTO> alterar(
            @PathVariable Long id,
            @Valid @RequestBody ModeloTesteRequestDTO dto) {

        ModeloTesteResponseDTO resposta =
                modeloTesteService.alterar(id, dto);

        return ResponseEntity.ok(resposta);
    }


    // =========================================================
    // BUSCAR POR ID
    // =========================================================

    @GetMapping("/{id}")
    public ResponseEntity<ModeloTesteResponseDTO> buscarPorId(
            @PathVariable Long id) {

        ModeloTesteResponseDTO resposta =
                modeloTesteService.buscarPorId(id);

        return ResponseEntity.ok(resposta);
    }


    // =========================================================
    // EXCLUIR
    // =========================================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        modeloTesteService.excluir(id);

        return ResponseEntity.noContent().build();
    }


    // =========================================================
    // ADICIONAR PARÂMETRO
    // =========================================================

    @PostMapping("/{id}/parametros")
    public ResponseEntity<ModeloTesteResponseDTO> adicionarParametro(
            @PathVariable Long id,
            @Valid @RequestBody TipoParametroRequestDTO parametroDTO) {

        ModeloTesteResponseDTO resposta =
                modeloTesteService.adicionarParametro(
                        id,
                        parametroDTO
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resposta);
    }


    // =========================================================
    // REMOVER PARÂMETRO
    // =========================================================

    @DeleteMapping("/{modeloTesteId}/parametros/{parametroId}")
    public ResponseEntity<ModeloTesteResponseDTO> removerParametro(
            @PathVariable Long modeloTesteId,
            @PathVariable Long parametroId) {

        ModeloTesteResponseDTO resposta =
                modeloTesteService.removerParametro(
                        modeloTesteId,
                        parametroId
                );

        return ResponseEntity.ok(resposta);
    }
}

