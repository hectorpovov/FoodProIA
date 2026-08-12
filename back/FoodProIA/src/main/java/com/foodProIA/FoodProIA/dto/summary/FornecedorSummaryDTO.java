package com.foodProIA.FoodProIA.dto.summary;

import com.foodProIA.FoodProIA.entity.FornecedorEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FornecedorSummaryDTO {

    private Long id;
    private String nome;
    private String registroEmpresa;

    public FornecedorSummaryDTO(FornecedorEntity fornecedor){
        this.id = fornecedor.getId();
        this.nome = fornecedor.getNome();
        this.registroEmpresa = fornecedor.getRegistroEmpresa();
    }
}
