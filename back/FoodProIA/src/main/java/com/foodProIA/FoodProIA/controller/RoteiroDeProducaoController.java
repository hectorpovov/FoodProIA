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

import com.foodProIA.FoodProIA.dto.request.InsumoRoteiroRequestDTO;
import com.foodProIA.FoodProIA.dto.request.RoteiroDeProducaoRequestDTO;
import com.foodProIA.FoodProIA.dto.response.RoteiroDeProducaoResponseDTO;
import com.foodProIA.FoodProIA.service.RoteiroDeProducaoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/roteiro-de-producao")
@RequiredArgsConstructor
public class RoteiroDeProducaoController {

    private final RoteiroDeProducaoService roteiroService;


    // =========================================================
    // INSERIR ROTEIRO
    // =========================================================

    @PostMapping
    public ResponseEntity<RoteiroDeProducaoResponseDTO> inserir(
            @Valid @RequestBody RoteiroDeProducaoRequestDTO dto) {

        RoteiroDeProducaoResponseDTO response =
                roteiroService.inserir(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    // =========================================================
    // ALTERAR ROTEIRO
    // =========================================================

    @PutMapping("/{id}")
    public ResponseEntity<RoteiroDeProducaoResponseDTO> alterar(
            @PathVariable Long id,
            @Valid @RequestBody RoteiroDeProducaoRequestDTO dto) {

        RoteiroDeProducaoResponseDTO response =
                roteiroService.alterar(id, dto);

        return ResponseEntity.ok(response);
    }


    // =========================================================
    // EXCLUIR ROTEIRO
    // =========================================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        roteiroService.excluir(id);

        return ResponseEntity
                .noContent()
                .build();
    }


    // =========================================================
    // BUSCAR POR ID
    // =========================================================

    @GetMapping("/{id}")
    public ResponseEntity<RoteiroDeProducaoResponseDTO> buscarPorId(
            @PathVariable Long id) {

        RoteiroDeProducaoResponseDTO response =
                roteiroService.buscarPorId(id);

        return ResponseEntity.ok(response);
    }


    // =========================================================
    // ADICIONAR INSUMO AO ROTEIRO
    // =========================================================

    @PostMapping("/{roteiroId}/insumos")
    public ResponseEntity<RoteiroDeProducaoResponseDTO> adicionarInsumo(
            @PathVariable Long roteiroId,
            @Valid @RequestBody InsumoRoteiroRequestDTO dto) {

        RoteiroDeProducaoResponseDTO response =
                roteiroService.adicionarInsumo(
                        roteiroId,
                        dto
                );

        return ResponseEntity.ok(response);
    }


    // =========================================================
    // REMOVER INSUMO DO ROTEIRO
    // =========================================================

    @DeleteMapping("/{roteiroId}/insumos/{insumoId}")
    public ResponseEntity<RoteiroDeProducaoResponseDTO> removerInsumo(
            @PathVariable Long roteiroId,
            @PathVariable Long insumoId) {

        RoteiroDeProducaoResponseDTO response =
                roteiroService.removerInsumo(
                        roteiroId,
                        insumoId
                );

        return ResponseEntity.ok(response);
    }


    // =========================================================
    // ALTERAR INSUMO DO ROTEIRO
    // =========================================================

    @PutMapping("/{roteiroId}/insumos/{insumoId}")
    public ResponseEntity<RoteiroDeProducaoResponseDTO> alterarInsumo(
            @PathVariable Long roteiroId,
            @PathVariable Long insumoId,
            @Valid @RequestBody InsumoRoteiroRequestDTO dto) {

        RoteiroDeProducaoResponseDTO response =
                roteiroService.alterarInsumo(
                        roteiroId,
                        insumoId,
                        dto
                );

        return ResponseEntity.ok(response);
    }
}
