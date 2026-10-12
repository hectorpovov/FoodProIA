package com.foodProIA.FoodProIA.dto.response;

import java.util.List;

import com.foodProIA.FoodProIA.dto.summary.FichaProdutoSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.LoteProdutoSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.PlanejamentoDeProducaoSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.TipoParametroSummaryDTO;
import com.foodProIA.FoodProIA.entity.ProdutoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProdutoResponseDTO {

    private Long id;
    private String nomeComercial;
    private String descricao;
    private String categoria;
    private String unidadeDeMedida;

    private List<TipoParametroSummaryDTO> parametros;

    private Integer anosValidade;
    private Integer mesesValidade;
    private Integer diasValidade;

    private FichaProdutoSummaryDTO fichaProduto;

    private List<InformacaoNutricionalProdutoResponseDTO> informacoesNutricionais;

    private List<PlanejamentoDeProducaoSummaryDTO> planejamentos;

    private List<LoteProdutoSummaryDTO> lotes;

    public ProdutoResponseDTO(ProdutoEntity produto) {

        this.id = produto.getId();
        this.nomeComercial = produto.getNomeComercial();
        this.descricao = produto.getDescricao();
        this.categoria = produto.getCategoria();
        this.unidadeDeMedida = produto.getUnidadeDeMedida();

        this.parametros = produto.getParametros()
                .stream()
                .map(TipoParametroSummaryDTO::new)
                .toList();

        this.anosValidade = produto.getAnosValidade();
        this.mesesValidade = produto.getMesesValidade();
        this.diasValidade = produto.getDiasValidade();

        if (produto.getFichaProduto() != null) {
            this.fichaProduto =
                    new FichaProdutoSummaryDTO(produto.getFichaProduto());
        }

        this.informacoesNutricionais =
                produto.getInformacoesNutricionais()
                        .stream()
                        .map(InformacaoNutricionalProdutoResponseDTO::new)
                        .toList();

        this.planejamentos = produto.getPlanejamentos()
                .stream()
                .map(PlanejamentoDeProducaoSummaryDTO::new)
                .toList();

        this.lotes = produto.getLotes()
                .stream()
                .map(LoteProdutoSummaryDTO::new)
                .toList();
    }
}