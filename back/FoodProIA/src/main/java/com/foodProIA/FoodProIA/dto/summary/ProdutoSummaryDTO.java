package com.foodProIA.FoodProIA.dto.summary;

import com.foodProIA.FoodProIA.entity.ProdutoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProdutoSummaryDTO {

    private Long id;
    private String nomeComercial;
    private String categoria;

    public ProdutoSummaryDTO(ProdutoEntity produto) {
        this.id = produto.getId();
        this.nomeComercial = produto.getNomeComercial();
        this.categoria = produto.getCategoria();
    }
}