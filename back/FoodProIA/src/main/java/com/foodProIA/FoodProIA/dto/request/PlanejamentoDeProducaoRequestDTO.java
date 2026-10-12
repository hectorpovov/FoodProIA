package com.foodProIA.FoodProIA.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanejamentoDeProducaoRequestDTO {

    @NotNull
    private BigDecimal quantidadePlanejada;

    @NotNull
    private Boolean status;

    @NotNull
    private LocalDateTime dataInicio;

    @NotNull
    private LocalDateTime prazo;

    private Long idTurno;

    @NotNull
    private Long idProduto;

    private Long idRoteiro;

    private List<Long> idsEquipamentos;
}