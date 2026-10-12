package com.foodProIA.FoodProIA.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoteMateriaPrimaRequestDTO {

    @NotNull
    private LocalDate data;

    private String marca;

    @NotNull
    private BigDecimal quantidade;

    private String codigoNFe;

    @NotNull
    private BigDecimal valorCompra;

    @NotNull
    private LocalDate dataValidade;

    @NotNull
    private Long idMateriaPrima;

    private Long idFornecedor;

    private Long idRecebedor;
}