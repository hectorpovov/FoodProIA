package com.foodProIA.FoodProIA.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SetorUpdateRequestDTO {

    @NotBlank
    private String nome;

    private String Descricao;
}
