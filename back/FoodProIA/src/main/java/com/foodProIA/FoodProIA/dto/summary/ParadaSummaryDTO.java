package com.foodProIA.FoodProIA.dto.summary;

import java.time.Duration;
import java.time.LocalDateTime;

import com.foodProIA.FoodProIA.entity.ParadaEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParadaSummaryDTO {

    private Long id;
    private LocalDateTime data;
    private Duration tempoGasto;
    private String comentario;

    public ParadaSummaryDTO(ParadaEntity parada) {
        this.id = parada.getId();
        this.data = parada.getData();
        this.tempoGasto = parada.getTempoGasto();
        this.comentario = parada.getComentario();
    }
}