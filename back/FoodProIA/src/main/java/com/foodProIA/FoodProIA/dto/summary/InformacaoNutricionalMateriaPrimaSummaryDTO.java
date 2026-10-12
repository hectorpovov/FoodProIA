package com.foodProIA.FoodProIA.dto.summary;

import java.math.BigDecimal;

import com.foodProIA.FoodProIA.entity.InformacaoNutricionalMateriaPrimaEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InformacaoNutricionalMateriaPrimaSummaryDTO {

    private Long id;
    private BigDecimal valor;

    public InformacaoNutricionalMateriaPrimaSummaryDTO(
            InformacaoNutricionalMateriaPrimaEntity informacao) {

        this.id = informacao.getId();
        this.valor = informacao.getValor();
    }
}