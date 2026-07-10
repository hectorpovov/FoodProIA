package com.foodProIA.FoodProIA.dto.summary;

import com.foodProIA.FoodProIA.entity.SetorEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SetorSummaryDTO {

    private Long id;
    private String nome;

    public SetorSummaryDTO(SetorEntity setor){
        this.id = setor.getId();
        this.nome = setor.getNome();
    }
}
