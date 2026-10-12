package com.foodProIA.FoodProIA.dto.response;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import com.foodProIA.FoodProIA.dto.summary.LoteProdutoSummaryDTO;
import com.foodProIA.FoodProIA.entity.EstoqueEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EstoqueResponseDTO {

    private Long id;
    private String SKU;
    private String classificacaoDoItem;
    private LocalDateTime dataChegada;
    private Duration tempoParado;
    private BigDecimal quantidadeProduto;
    private Integer diasParado;
    private Integer mesesParado;
    private String destino;
    private BigDecimal valorDoProduto;
    private BigDecimal quantidadeSaida;
    private LoteProdutoSummaryDTO loteProduto;

    public EstoqueResponseDTO(EstoqueEntity estoque) {
        this.id = estoque.getId();
        this.SKU = estoque.getSKU();
        this.classificacaoDoItem = estoque.getClassificacaoDoItem();
        this.dataChegada = estoque.getDataChegada();
        this.tempoParado = estoque.getTempoParado();
        this.quantidadeProduto = estoque.getQuantidadeProduto();
        this.diasParado = estoque.getDiasParado();
        this.mesesParado = estoque.getMesesParado();
        this.destino = estoque.getDestino();
        this.valorDoProduto = estoque.getValorDoProduto();
        this.quantidadeSaida = estoque.getQuantidadeSaida();

        if (estoque.getLoteProduto() != null) {
            this.loteProduto = new LoteProdutoSummaryDTO(estoque.getLoteProduto());
        }
    }
}