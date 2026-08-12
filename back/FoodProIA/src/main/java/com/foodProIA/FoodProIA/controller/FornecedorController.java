
package com.foodProIA.FoodProIA.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.foodProIA.FoodProIA.dto.request.FornecedorRequestDTO;
import com.foodProIA.FoodProIA.dto.request.FraudeRequestDTO;
import com.foodProIA.FoodProIA.dto.response.FornecedorResponseDTO;
import com.foodProIA.FoodProIA.service.FornecedorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fornecedor")
@RequiredArgsConstructor
public class FornecedorController {

    private final FornecedorService fornecedorService;


    // =========================================================
    // INSERIR
    // =========================================================

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FornecedorResponseDTO> inserir(

            @RequestPart("dados")
            @Valid
            FornecedorRequestDTO dto,

            @RequestPart(
                    value = "certidaoDeNegativas",
                    required = false
            )
            MultipartFile certidaoDeNegativas,

            @RequestPart(
                    value = "licensasDeFuncionamento",
                    required = false
            )
            MultipartFile licensasDeFuncionamento,

            @RequestPart(
                    value = "certificacoesDeQualidade",
                    required = false
            )
            java.util.List<MultipartFile> certificacoesDeQualidade) {

        dto.setCertidaoDeNegativas(
                certidaoDeNegativas
        );

        dto.setLicensasDeFuncionamento(
                licensasDeFuncionamento
        );

        dto.setCertificacoesDeQualidade(
                certificacoesDeQualidade
        );

        FornecedorResponseDTO response =
                fornecedorService.inserir(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


        
    // =========================================================
    // ALTERAR
    // =========================================================

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<FornecedorResponseDTO> alterar(

            @PathVariable Long id,

            @RequestPart("dados")
            @Valid
            FornecedorRequestDTO dto,

            @RequestPart(
                    value = "certidaoDeNegativas",
                    required = false
            )
            MultipartFile certidaoDeNegativas,

            @RequestPart(
                    value = "licensasDeFuncionamento",
                    required = false
            )
            MultipartFile licensasDeFuncionamento,

            @RequestPart(
                    value = "certificacoesDeQualidade",
                    required = false
            )
            List<MultipartFile> certificacoesDeQualidade) {

        dto.setCertidaoDeNegativas(
                certidaoDeNegativas
        );

        dto.setLicensasDeFuncionamento(
                licensasDeFuncionamento
        );

        dto.setCertificacoesDeQualidade(
                certificacoesDeQualidade
        );

        FornecedorResponseDTO response =
                fornecedorService.alterar(
                        id,
                        dto
                );

        return ResponseEntity.ok(response);
    }



    // =========================================================
    // EXCLUIR
    // =========================================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        fornecedorService.excluir(id);

        return ResponseEntity
                .noContent()
                .build();
    }


    // =========================================================
    // BUSCAR POR ID
    // =========================================================

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorResponseDTO> buscarPorId(
            @PathVariable Long id) {

        FornecedorResponseDTO response =
                fornecedorService.buscarPorId(id);

        return ResponseEntity.ok(response);
    }


    // =========================================================
    // ADICIONAR FRAUDE
    // =========================================================

    @PostMapping("/{fornecedorId}/fraude")
    public ResponseEntity<FornecedorResponseDTO> adicionarFraude(

            @PathVariable Long fornecedorId,

            @RequestPart("dados")
            @Valid
            FraudeRequestDTO dto) {

        FornecedorResponseDTO response =
                fornecedorService.adicionarFraude(
                        fornecedorId,
                        dto
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    // =========================================================
    // REMOVER FRAUDE
    // =========================================================

    @DeleteMapping(
            "/{fornecedorId}/fraude/{fraudeId}"
    )
    public ResponseEntity<FornecedorResponseDTO> removerFraude(

            @PathVariable Long fornecedorId,

            @PathVariable Long fraudeId) {

        FornecedorResponseDTO response =
                fornecedorService.removerFraude(
                        fornecedorId,
                        fraudeId
                );

        return ResponseEntity.ok(response);
    }
}

