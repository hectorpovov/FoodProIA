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

import com.foodProIA.FoodProIA.dto.request.RegistroDeProducaoRequestDTO;
import com.foodProIA.FoodProIA.dto.response.RegistroDeProducaoResponseDTO;
import com.foodProIA.FoodProIA.service.RegistroDeProducaoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/registros-de-producao")
@RequiredArgsConstructor
public class RegistroDeProducaoController {

    private final RegistroDeProducaoService registroDeProducaoService;

    @PostMapping
    public ResponseEntity<RegistroDeProducaoResponseDTO> criarRegistro(
            @Valid @RequestBody RegistroDeProducaoRequestDTO dto) {

        RegistroDeProducaoResponseDTO registro =
                registroDeProducaoService.criarRegistro(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroDeProducaoResponseDTO> alterarRegistro(
            @PathVariable Long id,
            @Valid @RequestBody RegistroDeProducaoRequestDTO dto) {

        RegistroDeProducaoResponseDTO registro =
                registroDeProducaoService.alterarRegistro(id, dto);

        return ResponseEntity.ok(registro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroDeProducaoResponseDTO> buscarRegistroPorId(
            @PathVariable Long id) {

        RegistroDeProducaoResponseDTO registro =
                registroDeProducaoService.buscarRegistroPorId(id);

        return ResponseEntity.ok(registro);
    }

    @GetMapping
    public ResponseEntity<List<RegistroDeProducaoResponseDTO>> listarRegistros() {

        List<RegistroDeProducaoResponseDTO> registros =
                registroDeProducaoService.listarRegistros();

        return ResponseEntity.ok(registros);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirRegistro(
            @PathVariable Long id) {

        registroDeProducaoService.excluirRegistro(id);

        return ResponseEntity.noContent().build();
    }
}