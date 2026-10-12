package com.foodProIA.FoodProIA.dto.summary;

import java.math.BigDecimal;

import com.foodProIA.FoodProIA.entity.InformacaoNutricionalProdutoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InformacaoNutricionalProdutoSummaryDTO {

    private Long id;
    private BigDecimal valor;

    public InformacaoNutricionalProdutoSummaryDTO(
            InformacaoNutricionalProdutoEntity informacao) {

        this.id = informacao.getId();
        this.valor = informacao.getValor();
    }
}