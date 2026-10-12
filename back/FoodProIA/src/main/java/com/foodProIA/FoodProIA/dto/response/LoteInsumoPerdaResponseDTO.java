package com.foodProIA.FoodProIA.dto.response;

import java.math.BigDecimal;

import com.foodProIA.FoodProIA.dto.summary.LoteInsumoSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.PerdaSummaryDTO;
import com.foodProIA.FoodProIA.entity.LoteInsumoPerdaEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoteInsumoPerdaResponseDTO {

    private Long id;

    private BigDecimal quantidade;

    private LoteInsumoSummaryDTO loteInsumo;

    private PerdaSummaryDTO perda;

    public LoteInsumoPerdaResponseDTO(
            LoteInsumoPerdaEntity loteInsumoPerda) {

        this.id = loteInsumoPerda.getId();
        this.quantidade = loteInsumoPerda.getQuantidade();

        if (loteInsumoPerda.getLoteInsumo() != null) {
            this.loteInsumo =
                    new LoteInsumoSummaryDTO(
                            loteInsumoPerda.getLoteInsumo());
        }

        if (loteInsumoPerda.getPerda() != null) {
            this.perda =
                    new PerdaSummaryDTO(loteInsumoPerda.getPerda());
        }
    }
}