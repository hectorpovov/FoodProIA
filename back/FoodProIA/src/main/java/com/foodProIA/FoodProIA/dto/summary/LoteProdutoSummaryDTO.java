package com.foodProIA.FoodProIA.dto.summary;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.foodProIA.FoodProIA.entity.LoteProdutoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoteProdutoSummaryDTO {

    private Long id;
    private BigDecimal quantidade;
    private LocalDateTime dataFabricacao;
    private LocalDateTime dataValidade;

    public LoteProdutoSummaryDTO(LoteProdutoEntity loteProduto) {
        this.id = loteProduto.getId();
        this.quantidade = loteProduto.getQuantidade();
        this.dataFabricacao = loteProduto.getDataFabricacao();
        this.dataValidade = loteProduto.getDataValidade();
    }
}