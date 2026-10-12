package com.foodProIA.FoodProIA.dto.response;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import com.foodProIA.FoodProIA.entity.EquipamentoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EquipamentoResponseDTO {

    private Long id;
    private String numeroDeSerie;
    private String fabricante;
    private LocalDateTime dataCompra;
    private BigDecimal capacidade;
    private Integer potencia;
    private Double consumoEnergetico;
    private String velocidadeDeOperacao;
    private Duration tempoEntreManutencoes;

    public EquipamentoResponseDTO(EquipamentoEntity equipamento) {
        this.id = equipamento.getId();
        this.numeroDeSerie = equipamento.getNumeroDeSerie();
        this.fabricante = equipamento.getFabricante();
        this.dataCompra = equipamento.getDataCompra();
        this.capacidade = equipamento.getCapacidade();
        this.potencia = equipamento.getPotencia();
        this.consumoEnergetico = equipamento.getConsumoEnergetico();
        this.velocidadeDeOperacao = equipamento.getVelocidadeDeOperacao();
        this.tempoEntreManutencoes = equipamento.getTempoEntreManutencoes();
    }
}