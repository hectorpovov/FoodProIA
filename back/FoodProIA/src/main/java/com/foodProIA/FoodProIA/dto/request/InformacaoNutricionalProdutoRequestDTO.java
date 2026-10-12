package com.foodProIA.FoodProIA.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InformacaoNutricionalProdutoRequestDTO {

    @NotNull
    private Long idInformacaoNutricional;

    @NotNull
    private BigDecimal valor;
}