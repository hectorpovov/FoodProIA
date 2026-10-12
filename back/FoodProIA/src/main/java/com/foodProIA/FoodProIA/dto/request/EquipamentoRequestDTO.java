package com.foodProIA.FoodProIA.dto.request;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipamentoRequestDTO {

    @NotBlank
    private String numeroDeSerie;

    @NotBlank
    private String fabricante;

    @NotNull
    private LocalDateTime dataCompra;

    @NotNull
    private BigDecimal capacidade;

    @NotNull
    private Integer potencia;

    @NotNull
    private Double consumoEnergetico;

    @NotBlank
    private String velocidadeDeOperacao;

    @NotNull
    private Duration tempoEntreManutencoes;
}
