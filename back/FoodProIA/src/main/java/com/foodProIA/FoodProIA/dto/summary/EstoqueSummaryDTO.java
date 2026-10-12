package com.foodProIA.FoodProIA.dto.summary;

import java.math.BigDecimal;

import com.foodProIA.FoodProIA.entity.EstoqueEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EstoqueSummaryDTO {

    private Long id;
    private String SKU;
    private BigDecimal quantidadeProduto;
    private String destino;

    public EstoqueSummaryDTO(EstoqueEntity estoque) {
        this.id = estoque.getId();
        this.SKU = estoque.getSKU();
        this.quantidadeProduto = estoque.getQuantidadeProduto();
        this.destino = estoque.getDestino();
    }
}