package com.foodProIA.FoodProIA.dto.response;

import java.util.List;

import com.foodProIA.FoodProIA.entity.ModeloTesteEntity;
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
public class ModeloTesteResponseDTO {

    private Long id;

    private String nome;

    private List<TipoParametroResponseDTO> parametros;

    public ModeloTesteResponseDTO(ModeloTesteEntity entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();

        this.parametros = entity.getParametros()
                .stream()
                .map(this::criarTipoParametroResponse)
                .toList();
    }

    private TipoParametroResponseDTO criarTipoParametroResponse(
            TipoParametroEntity tipoParametro) {

        if (tipoParametro instanceof TipoParametroBooleanoEntity booleano) {
            return new TipoParametroBooleanoResponseDTO(booleano);

        } else if (tipoParametro instanceof TipoParametroNumericoEntity numerico) {
            return new TipoParametroNumericoResponseDTO(numerico);

        } else if (tipoParametro instanceof TipoParametroTextualEntity textual) {
            return new TipoParametroTextualResponseDTO(textual);
        }

        throw new IllegalArgumentException(
                "Tipo de parâmetro não suportado: "
                + tipoParametro.getClass().getSimpleName()
        );
    }
}