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
public class InsumoRoteiroRequestDTO {

    @NotNull
    private Long insumoId;

    @NotNull
    private Double quantidade;

    @NotBlank
    private String unidadeDeMedida;
}