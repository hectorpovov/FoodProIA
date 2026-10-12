package com.foodProIA.FoodProIA.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoteInsumoRequestDTO {

    @NotNull
    private LocalDateTime data;

    private String marca;

    @NotNull
    private BigDecimal quantidade;

    @NotNull
    private LocalDateTime dataDeValidade;

    private String codigoNFe;

    @NotNull
    private BigDecimal valorCompra;

    @NotNull
    private Long idFornecedor;

    @NotNull
    private Long idFuncionario;

    @NotNull
    private Long idInsumo;
}
