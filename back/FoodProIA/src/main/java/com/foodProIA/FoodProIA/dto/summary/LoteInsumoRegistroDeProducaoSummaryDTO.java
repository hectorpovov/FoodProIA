package com.foodProIA.FoodProIA.dto.summary;

import java.math.BigDecimal;

import com.foodProIA.FoodProIA.entity.LoteInsumoRegistroDeProducaoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoteInsumoRegistroDeProducaoSummaryDTO {

    private Long id;
    private BigDecimal quantidade;

    public LoteInsumoRegistroDeProducaoSummaryDTO(
            LoteInsumoRegistroDeProducaoEntity entity) {

        this.id = entity.getId();
        this.quantidade = entity.getQuantidade();
    }
}