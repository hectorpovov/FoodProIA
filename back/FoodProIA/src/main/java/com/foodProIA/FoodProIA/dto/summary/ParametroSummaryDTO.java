package com.foodProIA.FoodProIA.dto.summary;

import com.foodProIA.FoodProIA.dto.response.TipoParametroBooleanoResponseDTO;
import com.foodProIA.FoodProIA.dto.response.TipoParametroNumericoResponseDTO;
import com.foodProIA.FoodProIA.dto.response.TipoParametroResponseDTO;
import com.foodProIA.FoodProIA.dto.response.TipoParametroTextualResponseDTO;
import com.foodProIA.FoodProIA.entity.ParametroEntity;
import com.foodProIA.FoodProIA.entity.TipoParametroBooleanoEntity;
import com.foodProIA.FoodProIA.entity.TipoParametroEntity;
import com.foodProIA.FoodProIA.entity.TipoParametroNumericoEntity;
import com.foodProIA.FoodProIA.entity.TipoParametroTextualEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParametroSummaryDTO {
    private Long id;
    private String valor;
    private TipoParametroResponseDTO tipo;

    public ParametroSummaryDTO(ParametroEntity parametro) {

    this.id = parametro.getId();
    this.valor = parametro.getValor();

    TipoParametroEntity tipo = parametro.getTipo();

    if (tipo instanceof TipoParametroBooleanoEntity booleano) {

        this.tipo = new TipoParametroBooleanoResponseDTO(booleano);

    } else if (tipo instanceof TipoParametroNumericoEntity numerico) {

        this.tipo = new TipoParametroNumericoResponseDTO(numerico);

    } else if (tipo instanceof TipoParametroTextualEntity textual) {

        this.tipo = new TipoParametroTextualResponseDTO(textual);
    }
}
}
