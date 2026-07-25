package com.foodProIA.FoodProIA.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodProIA.FoodProIA.dto.request.UsuarioAuthenticationRequestDTO;
import com.foodProIA.FoodProIA.dto.response.LoginResponseDTO;
import com.foodProIA.FoodProIA.entity.UsuarioEntity;
import com.foodProIA.FoodProIA.service.TokenService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid UsuarioAuthenticationRequestDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UsuarioEntity)auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }


}
