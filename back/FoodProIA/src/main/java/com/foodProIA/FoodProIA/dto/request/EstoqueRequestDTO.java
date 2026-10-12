package com.foodProIA.FoodProIA.dto.request;

import java.math.BigDecimal;
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
public class EstoqueRequestDTO {

    @NotBlank
    private String SKU;

    @NotBlank
    private String classificacaoDoItem;

    @NotNull 
    private LocalDateTime dataChegada;

    @NotNull
    private BigDecimal quantidadeProduto;

    
    private String destino;

    @NotNull
    private BigDecimal valorDoProduto;

    
    private BigDecimal quantidadeSaida;

}