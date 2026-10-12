package com.foodProIA.FoodProIA.dto.summary;

import java.time.LocalDateTime;

import com.foodProIA.FoodProIA.entity.EtapaEntity;
import com.foodProIA.FoodProIA.enums.EstadoEtapa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EtapaSummaryDTO {

    private Long id;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private EstadoEtapa estado;

    public EtapaSummaryDTO(EtapaEntity etapa) {
        this.id = etapa.getId();
        this.dataInicio = etapa.getDataInicio();
        this.dataFim = etapa.getDataFim();
        this.estado = etapa.getEstado();
    }
}