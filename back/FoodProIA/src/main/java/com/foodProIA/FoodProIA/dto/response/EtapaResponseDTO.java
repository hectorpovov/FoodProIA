package com.foodProIA.FoodProIA.dto.response;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import com.foodProIA.FoodProIA.dto.summary.ParadaSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.PerdaSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.RegistroDeProducaoSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.RetrabalhoSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.TipoEtapaSummaryDTO;
import com.foodProIA.FoodProIA.entity.EtapaEntity;
import com.foodProIA.FoodProIA.enums.EstadoEtapa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EtapaResponseDTO {

    private Long id;

    private List<ParametroResponseDTO> parametros;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    private String observacoes;

    private Duration tempoGasto;

    private BigDecimal consumoAgua;

    private EstadoEtapa estado;

    private TipoEtapaSummaryDTO tipoEtapa;

    private RegistroDeProducaoSummaryDTO registroDeProducao;

    private List<PerdaSummaryDTO> perdas;

    private List<ParadaSummaryDTO> paradas;

    private List<RetrabalhoSummaryDTO> retrabalhos;

    public EtapaResponseDTO(EtapaEntity etapa) {
        this.id = etapa.getId();

        this.parametros = etapa.getParametros()
                .stream()
                .map(ParametroResponseDTO::new)
                .toList();

        this.dataInicio = etapa.getDataInicio();
        this.dataFim = etapa.getDataFim();
        this.observacoes = etapa.getObservacoes();
        this.tempoGasto = etapa.getTempoGasto();
        this.consumoAgua = etapa.getConsumoAgua();
        this.estado = etapa.getEstado();

        if (etapa.getTipoEtapa() != null) {
            this.tipoEtapa = new TipoEtapaSummaryDTO(etapa.getTipoEtapa());
        }

        if (etapa.getRegistroDeProducao() != null) {
            this.registroDeProducao =
                    new RegistroDeProducaoSummaryDTO(etapa.getRegistroDeProducao());
        }

        this.perdas = etapa.getPerdas()
                .stream()
                .map(PerdaSummaryDTO::new)
                .toList();

        this.paradas = etapa.getParadas()
                .stream()
                .map(ParadaSummaryDTO::new)
                .toList();

        this.retrabalhos = etapa.getRetrabalhos()
                .stream()
                .map(RetrabalhoSummaryDTO::new)
                .toList();
    }
}