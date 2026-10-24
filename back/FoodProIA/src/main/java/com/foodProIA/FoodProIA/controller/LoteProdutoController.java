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

import com.foodProIA.FoodProIA.dto.request.EstoqueRequestDTO;
import com.foodProIA.FoodProIA.dto.request.LoteProdutoRequestDTO;
import com.foodProIA.FoodProIA.dto.response.EstoqueResponseDTO;
import com.foodProIA.FoodProIA.dto.response.LoteProdutoResponseDTO;
import com.foodProIA.FoodProIA.service.LoteProdutoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class LoteProdutoController {

    private final LoteProdutoService loteProdutoService;

    // =========================
    // LOTES DE PRODUTO
    // =========================

    @PostMapping("/lotes-produtos")
    public ResponseEntity<LoteProdutoResponseDTO> criar(
            @Valid @RequestBody LoteProdutoRequestDTO dto) {

        LoteProdutoResponseDTO lote = loteProdutoService.criar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(lote);
    }

    @PutMapping("/lotes-produtos/{id}")
    public ResponseEntity<LoteProdutoResponseDTO> alterar(
            @PathVariable Long id,
            @Valid @RequestBody LoteProdutoRequestDTO dto) {

        LoteProdutoResponseDTO lote = loteProdutoService.alterar(id, dto);

        return ResponseEntity.ok(lote);
    }

    @GetMapping("/lotes-produtos/{id}")
    public ResponseEntity<LoteProdutoResponseDTO> buscarPorId(
            @PathVariable Long id) {

        LoteProdutoResponseDTO lote = loteProdutoService.buscarPorId(id);

        return ResponseEntity.ok(lote);
    }

    @GetMapping("/lotes-produtos")
    public ResponseEntity<List<LoteProdutoResponseDTO>> listarTodos() {

        List<LoteProdutoResponseDTO> lotes = loteProdutoService.listarTodos();

        return ResponseEntity.ok(lotes);
    }

    @DeleteMapping("/lotes-produtos/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        loteProdutoService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    // =========================
    // ESTOQUES
    // =========================

    @PostMapping("/lotes-produtos/{idLoteProduto}/estoque")
    public ResponseEntity<EstoqueResponseDTO> criarEstoque(
            @PathVariable Long idLoteProduto,
            @Valid @RequestBody EstoqueRequestDTO dto) {

        EstoqueResponseDTO estoque =
                loteProdutoService.criarEstoque(idLoteProduto, dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(estoque);
    }

    @PutMapping("/estoques/{id}")
    public ResponseEntity<EstoqueResponseDTO> alterarEstoque(
            @PathVariable Long id,
            @Valid @RequestBody EstoqueRequestDTO dto) {

        EstoqueResponseDTO estoque =
                loteProdutoService.alterarEstoque(id, dto);

        return ResponseEntity.ok(estoque);
    }

    @GetMapping("/estoques/{id}")
    public ResponseEntity<EstoqueResponseDTO> buscarEstoquePorId(
            @PathVariable Long id) {

        EstoqueResponseDTO estoque =
                loteProdutoService.buscarEstoquePorId(id);

        return ResponseEntity.ok(estoque);
    }

    @GetMapping("/estoques")
    public ResponseEntity<List<EstoqueResponseDTO>> listarTodosEstoques() {

        List<EstoqueResponseDTO> estoques =
                loteProdutoService.listarTodosEstoques();

        return ResponseEntity.ok(estoques);
    }

    @DeleteMapping("/estoques/{id}")
    public ResponseEntity<Void> excluirEstoque(@PathVariable Long id) {

        loteProdutoService.excluirEstoque(id);

        return ResponseEntity.noContent().build();
    }
}   