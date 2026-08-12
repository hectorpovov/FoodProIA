package com.foodProIA.FoodProIA.dto.summary;

import java.time.LocalDateTime;

import com.foodProIA.FoodProIA.entity.TesteDeQualidadeEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TesteDeQualidadeSummaryDTO {
    private Long id;
    private LocalDateTime dataEHorario;

    public TesteDeQualidadeSummaryDTO(TesteDeQualidadeEntity teste){
        this.id = teste.getId();
        this.dataEHorario = teste.getDataEHorario();
    }
}
