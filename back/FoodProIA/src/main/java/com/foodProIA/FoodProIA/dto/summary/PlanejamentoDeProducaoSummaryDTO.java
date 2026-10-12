package com.foodProIA.FoodProIA.dto.summary;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.foodProIA.FoodProIA.entity.PlanejamentoDeProducaoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlanejamentoDeProducaoSummaryDTO {

    private Long id;
    private BigDecimal quantidadePlanejada;
    private Boolean status;
    private LocalDateTime dataInicio;
    private LocalDateTime prazo;

    public PlanejamentoDeProducaoSummaryDTO(
            PlanejamentoDeProducaoEntity planejamento) {

        this.id = planejamento.getId();
        this.quantidadePlanejada =
                planejamento.getQuantidadePlanejada();
        this.status = planejamento.getStatus();
        this.dataInicio = planejamento.getDataInicio();
        this.prazo = planejamento.getPrazo();
    }
}