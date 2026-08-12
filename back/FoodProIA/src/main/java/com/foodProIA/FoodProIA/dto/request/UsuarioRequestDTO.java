package com.foodProIA.FoodProIA.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import com.foodProIA.FoodProIA.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {

    @NotBlank
    private String nome;
    @NotBlank
    private String cpf;
    @NotBlank
    private String telefone;
    @NotBlank
    @Email
    private String email;
    @NotNull
    private LocalDate dataNascimento;
    @NotNull
    private UserRole role;

    
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
        message = "A senha deve conter pelo menos uma letra maiúscula, uma minúscula e um número."
    )
    
    private String password;
}
