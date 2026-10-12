package com.foodProIA.FoodProIA.dto.summary;

import java.time.LocalDateTime;

import com.foodProIA.FoodProIA.entity.RegistroManutencaoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistroManutencaoSummaryDTO {

    private Long id;
    private LocalDateTime data;
    private String numeroDeSerieEquipamento;

    public RegistroManutencaoSummaryDTO(
            RegistroManutencaoEntity registro) {

        this.id = registro.getId();
        this.data = registro.getData();
        this.numeroDeSerieEquipamento =
                registro.getNumeroDeSerieEquipamento();
    }
}