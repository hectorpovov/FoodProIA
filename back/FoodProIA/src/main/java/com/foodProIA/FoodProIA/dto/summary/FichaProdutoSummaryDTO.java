package com.foodProIA.FoodProIA.dto.summary;

import com.foodProIA.FoodProIA.entity.FichaProdutoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FichaProdutoSummaryDTO {

    private Long id;
    private String nomeTecnico;
    private Boolean status;

    public FichaProdutoSummaryDTO(FichaProdutoEntity fichaProduto) {
        this.id = fichaProduto.getId();
        this.nomeTecnico = fichaProduto.getNomeTecnico();
        this.status = fichaProduto.getStatus();
    }
}