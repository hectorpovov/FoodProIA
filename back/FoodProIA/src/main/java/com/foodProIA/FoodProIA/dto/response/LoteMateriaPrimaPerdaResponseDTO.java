package com.foodProIA.FoodProIA.dto.response;

import java.math.BigDecimal;

import com.foodProIA.FoodProIA.dto.summary.LoteMateriaPrimaSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.PerdaSummaryDTO;
import com.foodProIA.FoodProIA.entity.LoteMateriaPrimaPerdaEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoteMateriaPrimaPerdaResponseDTO {

    private Long id;

    private BigDecimal quantidade;

    private LoteMateriaPrimaSummaryDTO loteMateriaPrima;

    private PerdaSummaryDTO perda;

    public LoteMateriaPrimaPerdaResponseDTO(
            LoteMateriaPrimaPerdaEntity loteMateriaPrimaPerda) {

        this.id = loteMateriaPrimaPerda.getId();
        this.quantidade = loteMateriaPrimaPerda.getQuantidade();

        if (loteMateriaPrimaPerda.getLoteMateriaPrima() != null) {
            this.loteMateriaPrima =
                    new LoteMateriaPrimaSummaryDTO(
                            loteMateriaPrimaPerda.getLoteMateriaPrima());
        }

        if (loteMateriaPrimaPerda.getPerda() != null) {
            this.perda =
                    new PerdaSummaryDTO(loteMateriaPrimaPerda.getPerda());
        }
    }
}