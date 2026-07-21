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

import com.foodProIA.FoodProIA.dto.request.FuncionarioRequestDTO;
import com.foodProIA.FoodProIA.dto.response.FuncionarioResponseDTO;
import com.foodProIA.FoodProIA.service.FuncionarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @GetMapping
    public List<FuncionarioResponseDTO> listarTodos(){
        return funcionarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public FuncionarioResponseDTO retornaFuncionario(@PathVariable("id") Long id){
        return funcionarioService.buscarPorId(id);
    }

    @PostMapping
    public FuncionarioResponseDTO inserir(@Valid @RequestBody FuncionarioRequestDTO funcionario){
        return funcionarioService.inserir(funcionario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> alterar(@PathVariable("id") Long id, @Valid @RequestBody FuncionarioRequestDTO funcionario){
        return ResponseEntity.ok(funcionarioService.alterar(id, funcionario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id){
        funcionarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }


}
