package com.foodProIA.FoodProIA.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.foodProIA.FoodProIA.dto.summary.EquipamentoSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.ProdutoSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.RoteiroDeProducaoSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.TurnoSummaryDTO;
import com.foodProIA.FoodProIA.entity.PlanejamentoDeProducaoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlanejamentoDeProducaoResponseDTO {

    private Long id;
    private BigDecimal quantidadePlanejada;
    private Boolean status;
    private LocalDateTime dataInicio;
    private LocalDateTime prazo;

    private TurnoSummaryDTO turno;
    private ProdutoSummaryDTO produto;
    private RoteiroDeProducaoSummaryDTO roteiro;
    private List<EquipamentoSummaryDTO> equipamentos;

    public PlanejamentoDeProducaoResponseDTO(
            PlanejamentoDeProducaoEntity planejamento) {

        this.id = planejamento.getId();
        this.quantidadePlanejada =
                planejamento.getQuantidadePlanejada();
        this.status = planejamento.getStatus();
        this.dataInicio = planejamento.getDataInicio();
        this.prazo = planejamento.getPrazo();

        if (planejamento.getTurno() != null) {
            this.turno =
                    new TurnoSummaryDTO(planejamento.getTurno());
        }

        if (planejamento.getProduto() != null) {
            this.produto =
                    new ProdutoSummaryDTO(planejamento.getProduto());
        }

        if (planejamento.getRoteiro() != null) {
            this.roteiro =
                    new RoteiroDeProducaoSummaryDTO(
                            planejamento.getRoteiro());
        }

        this.equipamentos = planejamento.getEquipamentos()
                .stream()
                .map(EquipamentoSummaryDTO::new)
                .toList();
    }
}