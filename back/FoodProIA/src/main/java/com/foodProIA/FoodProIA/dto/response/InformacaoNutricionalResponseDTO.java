package com.foodProIA.FoodProIA.dto.response;

import java.util.List;

import com.foodProIA.FoodProIA.dto.summary.InformacaoNutricionalMateriaPrimaSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.InformacaoNutricionalProdutoSummaryDTO;
import com.foodProIA.FoodProIA.entity.InformacaoNutricionalEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InformacaoNutricionalResponseDTO {

    private Long id;
    private String nome;
    private String unidadeDeMedida;

    private List<InformacaoNutricionalMateriaPrimaSummaryDTO> materiasPrimas;

    private List<InformacaoNutricionalProdutoSummaryDTO> produtos;

    public InformacaoNutricionalResponseDTO(
            InformacaoNutricionalEntity informacao) {

        this.id = informacao.getId();
        this.nome = informacao.getNome();
        this.unidadeDeMedida = informacao.getUnidadeDeMedida();

        this.materiasPrimas = informacao.getMateriasPrimas()
                .stream()
                .map(InformacaoNutricionalMateriaPrimaSummaryDTO::new)
                .toList();

        this.produtos = informacao.getProdutos()
                .stream()
                .map(InformacaoNutricionalProdutoSummaryDTO::new)
                .toList();
    }
}