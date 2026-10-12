package com.foodProIA.FoodProIA.dto.summary;

import java.time.LocalDateTime;

import com.foodProIA.FoodProIA.entity.PlanejamentoManutencaoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlanejamentoManutencaoSummaryDTO {

    private Long id;
    private LocalDateTime data;
    private String numeroDeSerieEquipamento;

    public PlanejamentoManutencaoSummaryDTO(
            PlanejamentoManutencaoEntity planejamento) {

        this.id = planejamento.getId();
        this.data = planejamento.getData();
        this.numeroDeSerieEquipamento =
                planejamento.getNumeroDeSerieEquipamento();
    }
}