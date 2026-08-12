package com.foodProIA.FoodProIA.dto.request;

import java.util.List;

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
public class RoteiroDeProducaoRequestDTO {

    @NotBlank
    private String nome;

    @NotNull
    private boolean status;

    private List<InsumoRoteiroRequestDTO> insumos;
}