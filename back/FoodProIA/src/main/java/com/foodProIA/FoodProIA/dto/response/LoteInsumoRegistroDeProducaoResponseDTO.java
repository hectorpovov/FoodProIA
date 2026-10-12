package com.foodProIA.FoodProIA.dto.response;

import java.math.BigDecimal;

import com.foodProIA.FoodProIA.dto.summary.LoteInsumoSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.RegistroDeProducaoSummaryDTO;
import com.foodProIA.FoodProIA.entity.LoteInsumoRegistroDeProducaoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoteInsumoRegistroDeProducaoResponseDTO {

    private Long id;
    private BigDecimal quantidade;

    private LoteInsumoSummaryDTO loteInsumo;
    private RegistroDeProducaoSummaryDTO registroDeProducao;

    public LoteInsumoRegistroDeProducaoResponseDTO(
            LoteInsumoRegistroDeProducaoEntity entity) {

        this.id = entity.getId();
        this.quantidade = entity.getQuantidade();

        if (entity.getLoteInsumo() != null) {
            this.loteInsumo =
                    new LoteInsumoSummaryDTO(entity.getLoteInsumo());
        }

        if (entity.getRegistroDeProducao() != null) {
            this.registroDeProducao =
                    new RegistroDeProducaoSummaryDTO(
                            entity.getRegistroDeProducao());
        }
    }
}