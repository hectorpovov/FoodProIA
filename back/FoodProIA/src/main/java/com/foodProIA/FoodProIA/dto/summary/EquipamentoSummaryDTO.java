package com.foodProIA.FoodProIA.dto.summary;

import com.foodProIA.FoodProIA.entity.EquipamentoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EquipamentoSummaryDTO {

    private Long id;
    private String numeroDeSerie;
    private String fabricante;

    public EquipamentoSummaryDTO(EquipamentoEntity equipamento) {
        this.id = equipamento.getId();
        this.numeroDeSerie = equipamento.getNumeroDeSerie();
        this.fabricante = equipamento.getFabricante();
    }
}