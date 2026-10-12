package com.foodProIA.FoodProIA.dto.request;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDeProducaoRequestDTO {

    @NotNull
    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    private BigDecimal quantidadeProduzida;

    private Duration tempoMaquina;

    private BigDecimal quantidadeGasta;

    private Boolean aprovado;

    private BigDecimal quantidadeRejeitada;

    private String observacoes;

    @NotNull
    private Long idProduto;

    @Valid
    private List<LoteMateriaPrimaRegistroDeProducaoRequestDTO>
            lotesMateriaPrima;

    @Valid
    private List<LoteInsumoRegistroDeProducaoRequestDTO>
            lotesInsumos;
}