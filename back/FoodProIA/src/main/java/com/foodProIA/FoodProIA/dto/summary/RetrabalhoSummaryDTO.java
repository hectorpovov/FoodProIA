package com.foodProIA.FoodProIA.dto.summary;

import java.time.LocalDateTime;

import com.foodProIA.FoodProIA.entity.RetrabalhoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RetrabalhoSummaryDTO {

    private Long id;
    private LocalDateTime data;

    public RetrabalhoSummaryDTO(RetrabalhoEntity retrabalho) {
        this.id = retrabalho.getId();
        this.data = retrabalho.getData();
    }
}