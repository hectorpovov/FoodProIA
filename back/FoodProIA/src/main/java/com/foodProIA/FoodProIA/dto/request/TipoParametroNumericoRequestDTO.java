package com.foodProIA.FoodProIA.dto.request;

import com.foodProIA.FoodProIA.entity.TipoParametroNumericoEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoParametroNumericoRequestDTO extends TipoParametroRequestDTO {

    private float valorMinimo;

    private float valorMaximo;

    private String unidadeMedida;

    public TipoParametroNumericoRequestDTO(TipoParametroNumericoEntity entity) {
        super();
        setNome(entity.getNome());
        setValorMinimo(entity.getValorMinimo());
        setValorMaximo(entity.getValorMaximo());
        setUnidadeMedida(entity.getUnidadeMedida());

    }
}