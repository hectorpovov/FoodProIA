package com.foodProIA.FoodProIA.dto.summary;

import java.math.BigDecimal;

import com.foodProIA.FoodProIA.entity.LoteInsumoPerdaEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoteInsumoPerdaSummaryDTO {

    private Long id;
    private BigDecimal quantidade;

    public LoteInsumoPerdaSummaryDTO(
            LoteInsumoPerdaEntity entity) {

        this.id = entity.getId();
        this.quantidade = entity.getQuantidade();
    }
}