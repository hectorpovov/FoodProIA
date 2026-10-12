package com.foodProIA.FoodProIA.dto.summary;

import java.time.LocalTime;

import com.foodProIA.FoodProIA.entity.TurnoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TurnoSummaryDTO {

    private Long id;
    private String descricao;
    private LocalTime horaInicio;
    private LocalTime horaTermino;

    public TurnoSummaryDTO(TurnoEntity turno) {

        this.id = turno.getId();
        this.descricao = turno.getDescricao();
        this.horaInicio = turno.getHoraInicio();
        this.horaTermino = turno.getHoraTermino();
    }
}