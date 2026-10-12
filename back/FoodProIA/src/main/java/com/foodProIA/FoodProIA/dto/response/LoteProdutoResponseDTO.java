package com.foodProIA.FoodProIA.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.foodProIA.FoodProIA.dto.summary.EstoqueSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.ProdutoSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.RegistroDeProducaoSummaryDTO;
import com.foodProIA.FoodProIA.entity.LoteProdutoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoteProdutoResponseDTO {

    private Long id;
    private BigDecimal quantidade;
    private LocalDateTime dataFabricacao;
    private LocalDateTime dataValidade;

    private ProdutoSummaryDTO produto;
    private RegistroDeProducaoSummaryDTO registroDeProducao;
    private EstoqueSummaryDTO estoque;

    public LoteProdutoResponseDTO(LoteProdutoEntity loteProduto) {

        this.id = loteProduto.getId();
        this.quantidade = loteProduto.getQuantidade();
        this.dataFabricacao = loteProduto.getDataFabricacao();
        this.dataValidade = loteProduto.getDataValidade();

        if (loteProduto.getProduto() != null) {
            this.produto =
                    new ProdutoSummaryDTO(loteProduto.getProduto());
        }

        if (loteProduto.getRegistroDeProducao() != null) {
            this.registroDeProducao =
                    new RegistroDeProducaoSummaryDTO(
                            loteProduto.getRegistroDeProducao());
        }

        if (loteProduto.getEstoque() != null) {
            this.estoque =
                    new EstoqueSummaryDTO(loteProduto.getEstoque());
        }
    }
}