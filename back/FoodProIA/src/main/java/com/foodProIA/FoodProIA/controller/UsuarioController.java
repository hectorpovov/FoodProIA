package com.foodProIA.FoodProIA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodProIA.FoodProIA.dto.request.UsuarioRequestDTO;
import com.foodProIA.FoodProIA.dto.response.UsuarioResponseDTO;
import com.foodProIA.FoodProIA.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping
    public List<UsuarioResponseDTO> listarTodos(){
        return usuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO retornaUsuario(@PathVariable("id") Long id){
        return usuarioService.buscarPorId(id);
    }


    @PostMapping
    public UsuarioResponseDTO inserir(@Valid @RequestBody UsuarioRequestDTO usuario){

        System.out.println(usuario.getDataNascimento());
        System.out.println(usuario.getPapel());

        return usuarioService.inserir(usuario);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> alterar(@PathVariable("id") Long id, @Valid @RequestBody UsuarioRequestDTO usuario){
        return ResponseEntity.ok(usuarioService.alterar(id, usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id){
        usuarioService.excluir(id);
        return ResponseEntity.ok(null);
    }


}
