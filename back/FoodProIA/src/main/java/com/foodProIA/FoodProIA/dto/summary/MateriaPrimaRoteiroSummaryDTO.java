package com.foodProIA.FoodProIA.dto.summary;

import java.math.BigDecimal;

import com.foodProIA.FoodProIA.entity.MateriaPrimaRoteiroEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MateriaPrimaRoteiroSummaryDTO {

    private Long id;
    private BigDecimal quantidade;

    public MateriaPrimaRoteiroSummaryDTO(
            MateriaPrimaRoteiroEntity materiaPrimaRoteiro) {

        this.id = materiaPrimaRoteiro.getId();
        this.quantidade = materiaPrimaRoteiro.getQuantidade();
    }
}