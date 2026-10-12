package com.foodProIA.FoodProIA.dto.response;

import java.math.BigDecimal;

import com.foodProIA.FoodProIA.dto.summary.InformacaoNutricionalSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.ProdutoSummaryDTO;
import com.foodProIA.FoodProIA.entity.InformacaoNutricionalProdutoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InformacaoNutricionalProdutoResponseDTO {

    private Long id;
    private BigDecimal valor;
    private ProdutoSummaryDTO produto;
    private InformacaoNutricionalSummaryDTO informacaoNutricional;

    public InformacaoNutricionalProdutoResponseDTO(
            InformacaoNutricionalProdutoEntity informacao) {

        this.id = informacao.getId();
        this.valor = informacao.getValor();

        if (informacao.getProduto() != null) {
            this.produto =
                    new ProdutoSummaryDTO(informacao.getProduto());
        }

        if (informacao.getInformacaoNutricional() != null) {
            this.informacaoNutricional =
                    new InformacaoNutricionalSummaryDTO(
                            informacao.getInformacaoNutricional());
        }
    }
}