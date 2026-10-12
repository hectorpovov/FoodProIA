package com.foodProIA.FoodProIA.dto.summary;

import com.foodProIA.FoodProIA.entity.RoteiroDeProducaoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RoteiroDeProducaoSummaryDTO {

    private Long id;
    private String nome;

    public RoteiroDeProducaoSummaryDTO(RoteiroDeProducaoEntity roteiro){
        this.id = roteiro.getId();
        this.nome = roteiro.getNome();
    }
}
