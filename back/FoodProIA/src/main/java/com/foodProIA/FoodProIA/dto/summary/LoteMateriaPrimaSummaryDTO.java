package com.foodProIA.FoodProIA.dto.summary;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.foodProIA.FoodProIA.entity.LoteMateriaPrimaEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoteMateriaPrimaSummaryDTO {

    private Long id;
    private LocalDate data;
    private String marca;
    private BigDecimal quantidade;
    private String codigoNFe;
    private LocalDate dataValidade;

    public LoteMateriaPrimaSummaryDTO(
            LoteMateriaPrimaEntity loteMateriaPrima) {

        this.id = loteMateriaPrima.getId();
        this.data = loteMateriaPrima.getData();
        this.marca = loteMateriaPrima.getMarca();
        this.quantidade = loteMateriaPrima.getQuantidade();
        this.codigoNFe = loteMateriaPrima.getCodigoNFe();
        this.dataValidade = loteMateriaPrima.getDataValidade();
    }
}