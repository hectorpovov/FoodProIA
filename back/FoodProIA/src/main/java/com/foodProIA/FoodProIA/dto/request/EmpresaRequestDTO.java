package com.foodProIA.FoodProIA.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaRequestDTO {


    @NotBlank
    private String cnpj;

    @NotBlank
    private String razaoSocial;

    @NotBlank
    private String nomeFantasia;

    @Email
    private String email;

    @NotBlank
    private String telefone;

    @NotBlank
    private String observacoes;


}
