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

import com.foodProIA.FoodProIA.dto.request.EmpresaRequestDTO;
import com.foodProIA.FoodProIA.dto.response.EmpresaResponseDTO;
import com.foodProIA.FoodProIA.dto.response.FuncionarioResponseDTO;
import com.foodProIA.FoodProIA.dto.response.SetorResponseDTO;
import com.foodProIA.FoodProIA.service.EmpresaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/empresas")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService empresaService;

    @GetMapping
    public List<EmpresaResponseDTO> listarTodos(){
        return empresaService.listarTodos();
    }

    @GetMapping("/{id}/setores")
    public List<SetorResponseDTO> listarSetores(@PathVariable("id") Long id){
        return empresaService.listarSetores(id);
    }

    @GetMapping("/{id}/funcionarios")
    public List<FuncionarioResponseDTO> listarFuncionarios(@PathVariable("id") Long id){
        return empresaService.listarFuncionarios(id);
    }

    @GetMapping("/{id}")
    public EmpresaResponseDTO retornaEmpresa(@PathVariable("id") Long id){
        return empresaService.buscarPorId(id);
    }

    @PostMapping
    public EmpresaResponseDTO inserir(@Valid @RequestBody EmpresaRequestDTO empresa){

        return empresaService.inserir(empresa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> alterar(@PathVariable("id") Long id, @Valid @RequestBody EmpresaRequestDTO empresa){
        return ResponseEntity.ok(empresaService.alterar(id, empresa));    
    } 

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id){
        empresaService.excluir(id);
        return ResponseEntity.noContent().build();
    }



}
