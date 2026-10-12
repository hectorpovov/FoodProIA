package com.foodProIA.FoodProIA.dto.response;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import com.foodProIA.FoodProIA.dto.summary.EtapaSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.PerdaSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.RegistroDeProducaoSummaryDTO;
import com.foodProIA.FoodProIA.entity.RetrabalhoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RetrabalhoResponseDTO {

    private Long id;

    private LocalDateTime data;

    private Duration tempoGasto;

    private String comentario;

    private RegistroDeProducaoSummaryDTO registroDeProducao;

    private EtapaSummaryDTO etapa;

    private List<PerdaSummaryDTO> perdas;

    public RetrabalhoResponseDTO(RetrabalhoEntity retrabalho) {
        this.id = retrabalho.getId();
        this.data = retrabalho.getData();
        this.tempoGasto = retrabalho.getTempoGasto();
        this.comentario = retrabalho.getComentario();

        if (retrabalho.getRegistroDeProducao() != null) {
            this.registroDeProducao =
                    new RegistroDeProducaoSummaryDTO(
                            retrabalho.getRegistroDeProducao());
        }

        if (retrabalho.getEtapa() != null) {
            this.etapa =
                    new EtapaSummaryDTO(retrabalho.getEtapa());
        }

        this.perdas = retrabalho.getPerdas()
                .stream()
                .map(PerdaSummaryDTO::new)
                .toList();
    }
}