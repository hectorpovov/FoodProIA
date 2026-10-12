package com.foodProIA.FoodProIA.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class LoteProdutoRequestDTO {

    @NotNull
    private BigDecimal quantidade;

    @NotNull
    private LocalDateTime dataFabricacao;

    @NotNull
    private Long idProduto;

    @NotNull
    private Long idRegistroDeProducao;

    @Valid
    @NotNull
    private EstoqueRequestDTO estoque;
}