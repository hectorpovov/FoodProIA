package com.foodProIA.FoodProIA.dto.summary;

import com.foodProIA.FoodProIA.entity.ModeloTesteEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModeloTesteSummaryDTO {

    private Long id;
    private String nome;

    public ModeloTesteSummaryDTO(ModeloTesteEntity modelo){
        this.id = modelo.getId();
        this.nome = modelo.getNome();
    }
}
