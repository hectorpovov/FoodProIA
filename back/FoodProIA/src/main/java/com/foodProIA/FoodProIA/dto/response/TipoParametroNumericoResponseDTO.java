package com.foodProIA.FoodProIA.dto.response;

import com.foodProIA.FoodProIA.entity.TipoParametroNumericoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TipoParametroNumericoResponseDTO extends TipoParametroResponseDTO {

    private float valorMinimo;

    private float valorMaximo;

    private String unidadeMedida;

    public TipoParametroNumericoResponseDTO(TipoParametroNumericoEntity tipoParametro) {
        super(tipoParametro);
        this.valorMinimo = tipoParametro.getValorMinimo();
        this.valorMaximo = tipoParametro.getValorMaximo();
        this.unidadeMedida = tipoParametro.getUnidadeMedida();
    }
}