package com.foodProIA.FoodProIA.dto.response;

import java.time.Duration;
import java.time.LocalDateTime;

import com.foodProIA.FoodProIA.dto.summary.EtapaSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.RegistroDeProducaoSummaryDTO;
import com.foodProIA.FoodProIA.entity.ParadaEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParadaResponseDTO {

    private Long id;
    private LocalDateTime data;
    private Duration tempoGasto;
    private String comentario;

    private RegistroDeProducaoSummaryDTO registroDeProducao;
    private EtapaSummaryDTO etapa;

    public ParadaResponseDTO(ParadaEntity parada) {

        this.id = parada.getId();
        this.data = parada.getData();
        this.tempoGasto = parada.getTempoGasto();
        this.comentario = parada.getComentario();

        if (parada.getRegistroDeProducao() != null) {
            this.registroDeProducao =
                    new RegistroDeProducaoSummaryDTO(
                            parada.getRegistroDeProducao());
        }

        if (parada.getEtapa() != null) {
            this.etapa =
                    new EtapaSummaryDTO(parada.getEtapa());
        }
    }
}