package com.foodProIA.FoodProIA.dto.response;

import java.time.LocalDate;

import com.foodProIA.FoodProIA.entity.UsuarioEntity;
import com.foodProIA.FoodProIA.enums.UserRole;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UsuarioResponseDTO {

    private Long id;

    private String nome;

    private String cpf;

    private String telefone;

    private String email;

    private LocalDate dataNascimento;

    private boolean ativo;

    private UserRole role;


    public UsuarioResponseDTO(UsuarioEntity usuario){
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.telefone = usuario.getTelefone();
        this.email = usuario.getEmail();
        this.dataNascimento = usuario.getDataNascimento();
        this.ativo = usuario.isAtivo();
        this.role = usuario.getRole();
    }

}
