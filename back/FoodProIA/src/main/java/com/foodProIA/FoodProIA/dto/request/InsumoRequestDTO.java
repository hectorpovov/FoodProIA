package com.foodProIA.FoodProIA.dto.request;

import com.foodProIA.FoodProIA.enums.TipoInsumo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InsumoRequestDTO {

    @NotBlank
    private String nome;

    @NotNull
    private TipoInsumo tipoInsumo;
}