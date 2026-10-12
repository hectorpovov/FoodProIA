package com.foodProIA.FoodProIA.dto.request;

import java.time.Duration;
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
public class PlanejamentoManutencaoRequestDTO {

    @NotNull
    private LocalDateTime data;

    @NotNull
    private String numeroDeSerieEquipamento;

    @NotNull
    private Duration tempoParadoEstipulado;

    @NotNull
    private Integer diasParadoEstipulado;

    @NotNull
    private Integer mesesParadoEstipulado;

    private String comentario;
}