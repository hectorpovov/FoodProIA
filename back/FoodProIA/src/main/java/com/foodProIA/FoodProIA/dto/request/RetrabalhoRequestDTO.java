package com.foodProIA.FoodProIA.dto.request;

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
public class RetrabalhoRequestDTO {

    @NotNull
    private LocalDateTime data;

    @NotNull
    private Duration tempoGasto;

    private String comentario;

    @NotNull
    private Long idRegistroDeProducao;

    private Long idEtapa;

    @Valid
    private List<PerdaRequestDTO> perdas;
}