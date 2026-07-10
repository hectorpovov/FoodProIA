package com.foodProIA.FoodProIA.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SetorRequestDTO {

    @NotBlank
    private String nome;

    private String Descricao;
    
    @NotNull
    private Long idEmpresa;

}
