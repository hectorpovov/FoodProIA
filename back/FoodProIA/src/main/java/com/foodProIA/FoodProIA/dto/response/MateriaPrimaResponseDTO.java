package com.foodProIA.FoodProIA.dto.response;

import java.util.List;

import com.foodProIA.FoodProIA.dto.summary.FichaProdutoSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.LoteMateriaPrimaSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.MateriaPrimaRoteiroSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.TipoEtapaSummaryDTO;
import com.foodProIA.FoodProIA.entity.MateriaPrimaEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MateriaPrimaResponseDTO {

    private Long id;

    private String nome;

    private String categoria;

    private String unidadeDeMedida;

    private String descricao;

    private Integer anosValidade;

    private Integer mesesValidade;

    private Integer diasValidade;

    private List<LoteMateriaPrimaSummaryDTO> lotes;

    private List<InformacaoNutricionalMateriaPrimaResponseDTO>
            informacoesNutricionais;

    private List<MateriaPrimaRoteiroSummaryDTO> roteiros;

    private List<TipoEtapaSummaryDTO> tiposEtapa;

    private List<FichaProdutoSummaryDTO> fichasProduto;

    public MateriaPrimaResponseDTO(MateriaPrimaEntity materiaPrima) {

        this.id = materiaPrima.getId();
        this.nome = materiaPrima.getNome();
        this.categoria = materiaPrima.getCategoria();
        this.unidadeDeMedida = materiaPrima.getUnidadeDeMedida();
        this.descricao = materiaPrima.getDescricao();

        this.anosValidade = materiaPrima.getAnosValidade();
        this.mesesValidade = materiaPrima.getMesesValidade();
        this.diasValidade = materiaPrima.getDiasValidade();

        this.lotes = materiaPrima.getLotes()
                .stream()
                .map(LoteMateriaPrimaSummaryDTO::new)
                .toList();

        this.informacoesNutricionais =
                materiaPrima.getInformacoesNutricionais()
                        .stream()
                        .map(InformacaoNutricionalMateriaPrimaResponseDTO::new)
                        .toList();

        this.roteiros = materiaPrima.getRoteiros()
                .stream()
                .map(MateriaPrimaRoteiroSummaryDTO::new)
                .toList();

        this.tiposEtapa = materiaPrima.getTiposEtapa()
                .stream()
                .map(TipoEtapaSummaryDTO::new)
                .toList();

        this.fichasProduto = materiaPrima.getFichasProduto()
                .stream()
                .map(FichaProdutoSummaryDTO::new)
                .toList();
    }
}