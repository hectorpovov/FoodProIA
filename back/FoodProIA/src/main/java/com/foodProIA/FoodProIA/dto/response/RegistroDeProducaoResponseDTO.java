package com.foodProIA.FoodProIA.dto.response;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import com.foodProIA.FoodProIA.dto.summary.EtapaSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.LoteProdutoSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.ParadaSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.PerdaSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.ProdutoSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.RetrabalhoSummaryDTO;
import com.foodProIA.FoodProIA.entity.RegistroDeProducaoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistroDeProducaoResponseDTO {

    private Long id;

    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    private BigDecimal quantidadeProduzida;
    private Duration tempoMaquina;
    private BigDecimal quantidadeGasta;
    private Boolean aprovado;
    private BigDecimal quantidadeRejeitada;
    private String observacoes;

    private ProdutoSummaryDTO produto;

    private List<EtapaSummaryDTO> etapas;

    private List<LoteMateriaPrimaRegistroDeProducaoResponseDTO>
            lotesMateriaPrima;

    private List<LoteInsumoRegistroDeProducaoResponseDTO>
            lotesInsumos;

    private List<PerdaSummaryDTO> perdas;

    private List<ParadaSummaryDTO> paradas;

    private List<RetrabalhoSummaryDTO> retrabalhos;

    private LoteProdutoSummaryDTO loteProduto;

    public RegistroDeProducaoResponseDTO(
            RegistroDeProducaoEntity registro) {

        this.id = registro.getId();

        this.dataInicio = registro.getDataInicio();
        this.dataFim = registro.getDataFim();

        this.quantidadeProduzida =
                registro.getQuantidadeProduzida();

        this.tempoMaquina =
                registro.getTempoMaquina();

        this.quantidadeGasta =
                registro.getQuantidadeGasta();

        this.aprovado =
                registro.getAprovado();

        this.quantidadeRejeitada =
                registro.getQuantidadeRejeitada();

        this.observacoes =
                registro.getObservacoes();

        if (registro.getProduto() != null) {
            this.produto =
                    new ProdutoSummaryDTO(registro.getProduto());
        }

        this.etapas = registro.getEtapas()
                .stream()
                .map(EtapaSummaryDTO::new)
                .toList();

        this.lotesMateriaPrima =
                registro.getLotesMateriaPrima()
                        .stream()
                        .map(LoteMateriaPrimaRegistroDeProducaoResponseDTO::new)
                        .toList();

        this.lotesInsumos =
                registro.getLotesInsumos()
                        .stream()
                        .map(LoteInsumoRegistroDeProducaoResponseDTO::new)
                        .toList();

        this.perdas = registro.getPerdas()
                .stream()
                .map(PerdaSummaryDTO::new)
                .toList();

        this.paradas = registro.getParadas()
                .stream()
                .map(ParadaSummaryDTO::new)
                .toList();

        this.retrabalhos = registro.getRetrabalhos()
                .stream()
                .map(RetrabalhoSummaryDTO::new)
                .toList();

        if (registro.getLoteProduto() != null) {
            this.loteProduto =
                    new LoteProdutoSummaryDTO(
                            registro.getLoteProduto());
        }
    }
}