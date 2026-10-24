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

import com.foodProIA.FoodProIA.dto.request.EtapaRequestDTO;
import com.foodProIA.FoodProIA.dto.request.TipoEtapaRequestDTO;
import com.foodProIA.FoodProIA.dto.response.EtapaResponseDTO;
import com.foodProIA.FoodProIA.dto.response.TipoEtapaResponseDTO;
import com.foodProIA.FoodProIA.service.EtapaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class EtapaController {

    private final EtapaService etapaService;

    // =========================================================
    // TIPO DE ETAPA
    // =========================================================

    @PostMapping("/tipos-etapa")
    public ResponseEntity<TipoEtapaResponseDTO> criarTipoEtapa(
            @Valid @RequestBody TipoEtapaRequestDTO dto) {

        TipoEtapaResponseDTO tipoEtapa =
                etapaService.criarTipoEtapa(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(tipoEtapa);
    }

    @PutMapping("/tipos-etapa/{id}")
    public ResponseEntity<TipoEtapaResponseDTO> alterarTipoEtapa(
            @PathVariable Long id,
            @Valid @RequestBody TipoEtapaRequestDTO dto) {

        TipoEtapaResponseDTO tipoEtapa =
                etapaService.alterarTipoEtapa(id, dto);

        return ResponseEntity.ok(tipoEtapa);
    }

    @GetMapping("/tipos-etapa/{id}")
    public ResponseEntity<TipoEtapaResponseDTO> buscarTipoEtapaPorId(
            @PathVariable Long id) {

        TipoEtapaResponseDTO tipoEtapa =
                etapaService.buscarTipoEtapaPorId(id);

        return ResponseEntity.ok(tipoEtapa);
    }

    @GetMapping("/tipos-etapa")
    public ResponseEntity<List<TipoEtapaResponseDTO>> listarTiposEtapa() {

        List<TipoEtapaResponseDTO> tiposEtapa =
                etapaService.listarTiposEtapa();

        return ResponseEntity.ok(tiposEtapa);
    }

    @DeleteMapping("/tipos-etapa/{id}")
    public ResponseEntity<Void> excluirTipoEtapa(
            @PathVariable Long id) {

        etapaService.excluirTipoEtapa(id);

        return ResponseEntity.noContent().build();
    }

    // =========================================================
    // ETAPA CONCRETA
    // =========================================================

    @PostMapping("/etapas")
    public ResponseEntity<EtapaResponseDTO> criarEtapa(
            @Valid @RequestBody EtapaRequestDTO dto) {

        EtapaResponseDTO etapa =
                etapaService.criarEtapa(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(etapa);
    }

    @PutMapping("/etapas/{id}")
    public ResponseEntity<EtapaResponseDTO> alterarEtapa(
            @PathVariable Long id,
            @Valid @RequestBody EtapaRequestDTO dto) {

        EtapaResponseDTO etapa =
                etapaService.alterarEtapa(id, dto);

        return ResponseEntity.ok(etapa);
    }

    @GetMapping("/etapas/{id}")
    public ResponseEntity<EtapaResponseDTO> buscarEtapaPorId(
            @PathVariable Long id) {

        EtapaResponseDTO etapa =
                etapaService.buscarEtapaPorId(id);

        return ResponseEntity.ok(etapa);
    }

    @GetMapping("/etapas")
    public ResponseEntity<List<EtapaResponseDTO>> listarEtapas() {

        List<EtapaResponseDTO> etapas =
                etapaService.listarEtapas();

        return ResponseEntity.ok(etapas);
    }

    @DeleteMapping("/etapas/{id}")
    public ResponseEntity<Void> excluirEtapa(
            @PathVariable Long id) {

        etapaService.excluirEtapa(id);

        return ResponseEntity.noContent().build();
    }
}