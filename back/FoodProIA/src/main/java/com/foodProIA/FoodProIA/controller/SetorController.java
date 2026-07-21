package com.foodProIA.FoodProIA.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodProIA.FoodProIA.dto.request.SetorCreateRequestDTO;
import com.foodProIA.FoodProIA.dto.request.SetorUpdateRequestDTO;
import com.foodProIA.FoodProIA.dto.response.FuncionarioResponseDTO;
import com.foodProIA.FoodProIA.dto.response.SetorResponseDTO;
import com.foodProIA.FoodProIA.service.SetorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/setores")
@RequiredArgsConstructor
public class SetorController {

    private final SetorService setorService;

    @GetMapping
    public List<SetorResponseDTO> listarTodos(){
        return setorService.listarTodos();
    }

    @GetMapping("/{id}/funcionarios")
    public List<FuncionarioResponseDTO> listarFuncionarios(@PathVariable("id")Long id){
        return setorService.listarFuncionarios(id);
    }
 
    @GetMapping("/{id}")
    public SetorResponseDTO retornaSetor(@PathVariable("id") Long id){
        return setorService.buscarPorId(id);
    }

    @PostMapping
    public SetorResponseDTO inserir(@Valid @RequestBody SetorCreateRequestDTO setor){
        return setorService.inserir(setor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SetorResponseDTO> alterar(@PathVariable("id") Long id,@Valid @RequestBody SetorUpdateRequestDTO setor){
        return ResponseEntity.ok(setorService.alterar(id, setor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id){
        setorService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
