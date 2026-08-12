package com.foodProIA.FoodProIA.dto.summary;

import com.foodProIA.FoodProIA.entity.RotaEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RotaSummaryDTO {

    private Long id;
    private String nome;
    private String codigo;

    public RotaSummaryDTO(RotaEntity rota){
        this.id = rota.getId();
        this.nome = rota.getNome();
        this.codigo = rota.getCodigo();
    }
}
