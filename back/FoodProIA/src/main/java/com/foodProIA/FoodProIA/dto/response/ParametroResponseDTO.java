package com.foodProIA.FoodProIA.dto.response;

import java.util.List;

import com.foodProIA.FoodProIA.dto.summary.TesteDeQualidadeSummaryDTO;
import com.foodProIA.FoodProIA.entity.ParametroEntity;
import com.foodProIA.FoodProIA.entity.TipoParametroBooleanoEntity;
import com.foodProIA.FoodProIA.entity.TipoParametroEntity;
import com.foodProIA.FoodProIA.entity.TipoParametroNumericoEntity;
import com.foodProIA.FoodProIA.entity.TipoParametroTextualEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParametroResponseDTO {

    private Long id;

    private String valor;

    private TipoParametroResponseDTO tipo;

    private List<TesteDeQualidadeSummaryDTO> testesDeQualidade;

    public ParametroResponseDTO(ParametroEntity parametro){
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
        
        this.testesDeQualidade = parametro.getTestesDeQualidade()
                                        .stream()
                                        .map(TesteDeQualidadeSummaryDTO::new)
                                        .toList();
    }
}
