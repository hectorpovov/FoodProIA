package com.foodProIA.FoodProIA.dto.summary;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.foodProIA.FoodProIA.entity.RegistroDeProducaoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistroDeProducaoSummaryDTO {

    private Long id;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private BigDecimal quantidadeProduzida;
    private Boolean aprovado;

    public RegistroDeProducaoSummaryDTO(
            RegistroDeProducaoEntity registroDeProducao) {

        this.id = registroDeProducao.getId();
        this.dataInicio = registroDeProducao.getDataInicio();
        this.dataFim = registroDeProducao.getDataFim();
        this.quantidadeProduzida =
                registroDeProducao.getQuantidadeProduzida();
        this.aprovado = registroDeProducao.getAprovado();
    }
}