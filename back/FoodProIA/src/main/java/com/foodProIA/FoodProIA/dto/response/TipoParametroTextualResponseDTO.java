package com.foodProIA.FoodProIA.dto.response;

import java.util.List;

import com.foodProIA.FoodProIA.entity.TipoParametroTextualEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TipoParametroTextualResponseDTO extends TipoParametroResponseDTO {

    private String esperado;

    private List<String> possibilidades;

    public TipoParametroTextualResponseDTO(TipoParametroTextualEntity tipoParametro) {
        super(tipoParametro);
        this.esperado = tipoParametro.getEsperado();
        this.possibilidades = tipoParametro.getPossibilidades();
    }
}
