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
public class LoteInsumoRegistroDeProducaoRequestDTO {

    @NotNull
    private BigDecimal quantidade;

    @NotNull
    private Long idLoteInsumo;
}