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
public class ParadaRequestDTO {

    @NotNull
    private LocalDateTime data;

    @NotNull
    private Duration tempoGasto;

    private String comentario;

    @NotNull
    private Long idRegistroDeProducao;

    @NotNull
    private Long idEtapa;
}