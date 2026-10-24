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

import com.foodProIA.FoodProIA.dto.request.InformacaoNutricionalRequestDTO;
import com.foodProIA.FoodProIA.dto.response.InformacaoNutricionalResponseDTO;
import com.foodProIA.FoodProIA.service.InformacaoNutricionalService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/informacoes-nutricionais")
@RequiredArgsConstructor
public class InformacaoNutricionalController {

    private final InformacaoNutricionalService informacaoNutricionalService;

    @PostMapping
    public ResponseEntity<InformacaoNutricionalResponseDTO> criar(
            @Valid @RequestBody InformacaoNutricionalRequestDTO dto) {

        InformacaoNutricionalResponseDTO informacao =
                informacaoNutricionalService.criar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(informacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InformacaoNutricionalResponseDTO> alterar(
            @PathVariable Long id,
            @Valid @RequestBody InformacaoNutricionalRequestDTO dto) {

        InformacaoNutricionalResponseDTO informacao =
                informacaoNutricionalService.alterar(id, dto);

        return ResponseEntity.ok(informacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InformacaoNutricionalResponseDTO> buscarPorId(
            @PathVariable Long id) {

        InformacaoNutricionalResponseDTO informacao =
                informacaoNutricionalService.buscarPorId(id);

        return ResponseEntity.ok(informacao);
    }

    @GetMapping
    public ResponseEntity<List<InformacaoNutricionalResponseDTO>> listarTodos() {

        List<InformacaoNutricionalResponseDTO> informacoes =
                informacaoNutricionalService.listarTodos();

        return ResponseEntity.ok(informacoes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        informacaoNutricionalService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}