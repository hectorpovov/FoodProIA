package com.foodProIA.FoodProIA.dto.summary;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.foodProIA.FoodProIA.entity.LoteInsumoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoteInsumoSummaryDTO {

    private Long id;
    private String marca;
    private BigDecimal quantidade;
    private String codigoNFe;
    private LocalDateTime dataDeValidade;

    public LoteInsumoSummaryDTO(LoteInsumoEntity loteInsumo) {
        this.id = loteInsumo.getId();
        this.marca = loteInsumo.getMarca();
        this.quantidade = loteInsumo.getQuantidade();
        this.codigoNFe = loteInsumo.getCodigoNFe();
        this.dataDeValidade = loteInsumo.getDataDeValidade();
    }
}