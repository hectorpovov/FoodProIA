package com.foodProIA.FoodProIA.dto.response;


import com.foodProIA.FoodProIA.dto.summary.ModeloTesteSummaryDTO;
import com.foodProIA.FoodProIA.entity.TipoParametroEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class TipoParametroResponseDTO {

    private Long id;

    private String nome;

    private ModeloTesteSummaryDTO modeloDeTeste;

    public TipoParametroResponseDTO(TipoParametroEntity tipoParametro){
        this.id = tipoParametro.getId();
        this.modeloDeTeste = new ModeloTesteSummaryDTO(tipoParametro.getModeloTeste());
        this.nome = tipoParametro.getNome();
    }
}
