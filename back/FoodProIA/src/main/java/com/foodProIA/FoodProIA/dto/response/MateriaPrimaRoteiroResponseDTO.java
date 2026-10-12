package com.foodProIA.FoodProIA.dto.response;

import java.math.BigDecimal;

import com.foodProIA.FoodProIA.dto.summary.MateriaPrimaSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.RoteiroDeProducaoSummaryDTO;
import com.foodProIA.FoodProIA.entity.MateriaPrimaRoteiroEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MateriaPrimaRoteiroResponseDTO {

    private Long id;
    private BigDecimal quantidade;

    private MateriaPrimaSummaryDTO materiaPrima;
    private RoteiroDeProducaoSummaryDTO roteiro;

    public MateriaPrimaRoteiroResponseDTO(
            MateriaPrimaRoteiroEntity entity) {

        this.id = entity.getId();
        this.quantidade = entity.getQuantidade();

        if (entity.getMateriaPrima() != null) {
            this.materiaPrima =
                    new MateriaPrimaSummaryDTO(entity.getMateriaPrima());
        }

        if (entity.getRoteiro() != null) {
            this.roteiro =
                    new RoteiroDeProducaoSummaryDTO(entity.getRoteiro());
        }
    }
}