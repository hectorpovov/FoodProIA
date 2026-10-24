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

import com.foodProIA.FoodProIA.dto.request.TurnoRequestDTO;
import com.foodProIA.FoodProIA.dto.response.TurnoResponseDTO;
import com.foodProIA.FoodProIA.service.TurnoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/turnos")
@RequiredArgsConstructor
public class TurnoController {

    private final TurnoService turnoService;

    @PostMapping
    public ResponseEntity<TurnoResponseDTO> criarTurno(
            @Valid @RequestBody TurnoRequestDTO dto) {

        TurnoResponseDTO turno = turnoService.criarTurno(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(turno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurnoResponseDTO> alterarTurno(
            @PathVariable Long id,
            @Valid @RequestBody TurnoRequestDTO dto) {

        TurnoResponseDTO turno = turnoService.alterarTurno(id, dto);

        return ResponseEntity.ok(turno);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoResponseDTO> buscarTurnoPorId(
            @PathVariable Long id) {

        TurnoResponseDTO turno = turnoService.buscarTurnoPorId(id);

        return ResponseEntity.ok(turno);
    }

    @GetMapping
    public ResponseEntity<List<TurnoResponseDTO>> listarTurnos() {

        List<TurnoResponseDTO> turnos = turnoService.listarTurnos();

        return ResponseEntity.ok(turnos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTurno(@PathVariable Long id) {

        turnoService.excluirTurno(id);

        return ResponseEntity.noContent().build();
    }
}