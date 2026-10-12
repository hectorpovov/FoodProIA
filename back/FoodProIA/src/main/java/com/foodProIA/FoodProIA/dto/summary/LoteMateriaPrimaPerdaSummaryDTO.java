package com.foodProIA.FoodProIA.dto.summary;

import java.math.BigDecimal;

import com.foodProIA.FoodProIA.entity.LoteMateriaPrimaPerdaEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoteMateriaPrimaPerdaSummaryDTO {

    private Long id;
    private BigDecimal quantidade;

    public LoteMateriaPrimaPerdaSummaryDTO(LoteMateriaPrimaPerdaEntity loteMateriaPrimaPerda){
        this.id = loteMateriaPrimaPerda.getId();
        this.quantidade = loteMateriaPrimaPerda.getQuantidade();
    }
}
