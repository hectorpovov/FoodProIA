package com.foodProIA.FoodProIA.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.foodProIA.FoodProIA.dto.summary.EtapaSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.RegistroDeProducaoSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.RetrabalhoSummaryDTO;
import com.foodProIA.FoodProIA.entity.PerdaEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PerdaResponseDTO {

    private Long id;

    private LocalDateTime data;

    private String comentario;

    private RetrabalhoSummaryDTO retrabalho;

    private RegistroDeProducaoSummaryDTO registroDeProducao;

    private EtapaSummaryDTO etapa;

    private List<LoteMateriaPrimaPerdaResponseDTO> lotesMateriaPrima;

    private List<LoteInsumoPerdaResponseDTO> lotesInsumo;

    public PerdaResponseDTO(PerdaEntity perda) {
        this.id = perda.getId();
        this.data = perda.getData();
        this.comentario = perda.getComentario();

        if (perda.getRetrabalho() != null) {
            this.retrabalho = new RetrabalhoSummaryDTO(perda.getRetrabalho());
        }

        if (perda.getRegistroDeProducao() != null) {
            this.registroDeProducao =
                    new RegistroDeProducaoSummaryDTO(perda.getRegistroDeProducao());
        }

        if (perda.getEtapa() != null) {
            this.etapa = new EtapaSummaryDTO(perda.getEtapa());
        }

        this.lotesMateriaPrima = perda.getLotesMateriaPrima()
                .stream()
                .map(LoteMateriaPrimaPerdaResponseDTO::new)
                .toList();

        this.lotesInsumo = perda.getLotesInsumo()
                .stream()
                .map(LoteInsumoPerdaResponseDTO::new)
                .toList();
    }
}