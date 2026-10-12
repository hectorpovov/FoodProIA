package com.foodProIA.FoodProIA.dto.request;

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
public class PerdaRequestDTO {

    @NotNull
    private LocalDateTime data;

    private String comentario;

    @NotNull
    private Long idRegistroDeProducao;

    private Long idEtapa;

    @Valid
    private List<LoteMateriaPrimaPerdaRequestDTO> lotesMateriaPrima;

    @Valid
    private List<LoteInsumoPerdaRequestDTO> lotesInsumo;
}