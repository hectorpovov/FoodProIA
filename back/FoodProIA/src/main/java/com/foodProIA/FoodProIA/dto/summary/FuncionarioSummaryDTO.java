package com.foodProIA.FoodProIA.dto.summary;

import com.foodProIA.FoodProIA.entity.FuncionarioEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FuncionarioSummaryDTO {

    private Long id;
    private String nome;
    
    public FuncionarioSummaryDTO(FuncionarioEntity funcionario){
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
    }
}
