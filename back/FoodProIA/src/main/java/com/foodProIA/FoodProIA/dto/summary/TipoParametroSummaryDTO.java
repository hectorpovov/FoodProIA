package com.foodProIA.FoodProIA.dto.summary;

import com.foodProIA.FoodProIA.entity.TipoParametroEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TipoParametroSummaryDTO {

    private Long id;
    private String nome;

    public TipoParametroSummaryDTO(TipoParametroEntity tipoParametro){
        this.id = tipoParametro.getId();
        this.nome = tipoParametro.getNome();
    }
}
