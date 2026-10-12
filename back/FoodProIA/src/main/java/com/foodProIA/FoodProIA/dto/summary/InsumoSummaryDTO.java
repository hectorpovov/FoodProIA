package com.foodProIA.FoodProIA.dto.summary;

import com.foodProIA.FoodProIA.entity.InsumoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InsumoSummaryDTO {

    private Long id;
    private String nome;

    public InsumoSummaryDTO(InsumoEntity insumo){
        this.id = insumo.getId();
        this.nome = insumo.getNome();
    }

}
