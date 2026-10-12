package com.foodProIA.FoodProIA.dto.response;

import java.time.Duration;
import java.util.List;

import com.foodProIA.FoodProIA.dto.summary.EquipamentoSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.EtapaSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.InsumoSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.MateriaPrimaSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.RoteiroDeProducaoSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.TipoParametroSummaryDTO;
import com.foodProIA.FoodProIA.entity.TipoEtapaEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TipoEtapaResponseDTO {

    private Long id;

    private String nome;

    private List<TipoParametroSummaryDTO> parametros;

    private String observacoes;

    private Duration tempoEstimado;

    private EquipamentoSummaryDTO equipamento;

    private List<RoteiroDeProducaoSummaryDTO> roteirosDeProducao;

    private List<EtapaSummaryDTO> etapas;

    private List<MateriaPrimaSummaryDTO> materiasPrimas;

    private List<InsumoSummaryDTO> insumos;

    public TipoEtapaResponseDTO(TipoEtapaEntity tipoEtapa) {

        this.id = tipoEtapa.getId();
        this.nome = tipoEtapa.getNome();

        this.parametros = tipoEtapa.getParametros()
                .stream()
                .map(TipoParametroSummaryDTO::new)
                .toList();

        this.observacoes = tipoEtapa.getObservacoes();
        this.tempoEstimado = tipoEtapa.getTempoEstimado();

        if (tipoEtapa.getEquipamento() != null) {
            this.equipamento =
                    new EquipamentoSummaryDTO(
                            tipoEtapa.getEquipamento());
        }

        this.roteirosDeProducao =
                tipoEtapa.getRoteirosDeProducao()
                        .stream()
                        .map(RoteiroDeProducaoSummaryDTO::new)
                        .toList();

        this.etapas = tipoEtapa.getEtapas()
                .stream()
                .map(EtapaSummaryDTO::new)
                .toList();

        this.materiasPrimas =
                tipoEtapa.getMateriasPrimas()
                        .stream()
                        .map(MateriaPrimaSummaryDTO::new)
                        .toList();

        this.insumos = tipoEtapa.getInsumos()
                .stream()
                .map(InsumoSummaryDTO::new)
                .toList();
    }
}