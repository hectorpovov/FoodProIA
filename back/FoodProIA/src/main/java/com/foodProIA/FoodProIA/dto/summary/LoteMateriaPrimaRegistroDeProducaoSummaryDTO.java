package com.foodProIA.FoodProIA.dto.summary;

import java.math.BigDecimal;

import com.foodProIA.FoodProIA.entity.LoteMateriaPrimaRegistroDeProducaoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoteMateriaPrimaRegistroDeProducaoSummaryDTO {

    private Long id;
    private BigDecimal quantidade;

    public LoteMateriaPrimaRegistroDeProducaoSummaryDTO(
            LoteMateriaPrimaRegistroDeProducaoEntity entity) {

        this.id = entity.getId();
        this.quantidade = entity.getQuantidade();
    }
}