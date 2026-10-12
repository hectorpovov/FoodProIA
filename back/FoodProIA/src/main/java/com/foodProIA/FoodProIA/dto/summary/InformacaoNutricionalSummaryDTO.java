package com.foodProIA.FoodProIA.dto.summary;

import com.foodProIA.FoodProIA.entity.InformacaoNutricionalEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InformacaoNutricionalSummaryDTO {

    private Long id;
    private String nome;
    private String unidadeDeMedida;

    public InformacaoNutricionalSummaryDTO(
            InformacaoNutricionalEntity informacao) {

        this.id = informacao.getId();
        this.nome = informacao.getNome();
        this.unidadeDeMedida = informacao.getUnidadeDeMedida();
    }
}