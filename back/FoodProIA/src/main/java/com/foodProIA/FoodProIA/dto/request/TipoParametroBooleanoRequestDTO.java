package com.foodProIA.FoodProIA.dto.request;

import com.foodProIA.FoodProIA.entity.TipoParametroBooleanoEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoParametroBooleanoRequestDTO extends TipoParametroRequestDTO {

    private boolean valorEsperado;

    public TipoParametroBooleanoRequestDTO(TipoParametroBooleanoEntity entity) {
        super();
        setNome(entity.getNome());
        setValorEsperado(entity.isValorEsperado());

    }
}