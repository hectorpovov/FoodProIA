package com.foodProIA.FoodProIA.dto.summary;

import java.time.LocalDateTime;

import com.foodProIA.FoodProIA.entity.PerdaEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PerdaSummaryDTO {

    private Long id;
    private LocalDateTime data;
    private String comentario;

    public PerdaSummaryDTO(PerdaEntity perda) {
        this.id = perda.getId();
        this.data = perda.getData();
        this.comentario = perda.getComentario();
    }
}