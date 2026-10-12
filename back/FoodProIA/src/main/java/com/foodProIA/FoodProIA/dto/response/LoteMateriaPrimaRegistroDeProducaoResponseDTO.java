package com.foodProIA.FoodProIA.dto.response;

import java.math.BigDecimal;

import com.foodProIA.FoodProIA.dto.summary.LoteMateriaPrimaSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.RegistroDeProducaoSummaryDTO;
import com.foodProIA.FoodProIA.entity.LoteMateriaPrimaRegistroDeProducaoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoteMateriaPrimaRegistroDeProducaoResponseDTO {

    private Long id;
    private BigDecimal quantidade;

    private LoteMateriaPrimaSummaryDTO loteMateriaPrima;
    private RegistroDeProducaoSummaryDTO registroDeProducao;

    public LoteMateriaPrimaRegistroDeProducaoResponseDTO(
            LoteMateriaPrimaRegistroDeProducaoEntity entity) {

        this.id = entity.getId();
        this.quantidade = entity.getQuantidade();

        if (entity.getLoteMateriaPrima() != null) {
            this.loteMateriaPrima =
                    new LoteMateriaPrimaSummaryDTO(
                            entity.getLoteMateriaPrima());
        }

        if (entity.getRegistroDeProducao() != null) {
            this.registroDeProducao =
                    new RegistroDeProducaoSummaryDTO(
                            entity.getRegistroDeProducao());
        }
    }
}