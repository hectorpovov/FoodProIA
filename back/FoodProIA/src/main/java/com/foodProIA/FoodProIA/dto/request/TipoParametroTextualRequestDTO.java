package com.foodProIA.FoodProIA.dto.request;

import java.util.List;

import com.foodProIA.FoodProIA.entity.TipoParametroTextualEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoParametroTextualRequestDTO extends TipoParametroRequestDTO {

    private String esperado;

    private List<String> possibilidades;

    public TipoParametroTextualRequestDTO(TipoParametroTextualEntity entity) {
        super();
        setNome(entity.getNome());
        setEsperado(entity.getEsperado());
        setPossibilidades(entity.getPossibilidades());

    }
}