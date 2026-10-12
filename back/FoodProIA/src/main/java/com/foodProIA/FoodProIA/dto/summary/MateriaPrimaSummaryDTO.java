package com.foodProIA.FoodProIA.dto.summary;

import com.foodProIA.FoodProIA.entity.MateriaPrimaEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MateriaPrimaSummaryDTO {

    private Long id;
    private String nome;
    private String categoria;
    private String unidadeDeMedida;

    public MateriaPrimaSummaryDTO(MateriaPrimaEntity materiaPrima) {

        this.id = materiaPrima.getId();
        this.nome = materiaPrima.getNome();
        this.categoria = materiaPrima.getCategoria();
        this.unidadeDeMedida = materiaPrima.getUnidadeDeMedida();
    }
}