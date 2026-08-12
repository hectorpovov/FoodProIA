package com.foodProIA.FoodProIA.dto.response;

import com.foodProIA.FoodProIA.entity.TipoParametroBooleanoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TipoParametroBooleanoResponseDTO extends TipoParametroResponseDTO {

    private boolean valorEsperado;

    public TipoParametroBooleanoResponseDTO(TipoParametroBooleanoEntity tipoParametro) {
        super(tipoParametro);
        this.valorEsperado = tipoParametro.isValorEsperado();
    }
}