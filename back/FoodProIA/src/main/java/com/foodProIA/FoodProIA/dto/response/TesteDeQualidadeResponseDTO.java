package com.foodProIA.FoodProIA.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.foodProIA.FoodProIA.dto.summary.ModeloTesteSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.ParametroSummaryDTO;
import com.foodProIA.FoodProIA.entity.TesteDeQualidadeEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TesteDeQualidadeResponseDTO {

    private Long id;

    private LocalDateTime dataEHorario;

    private ModeloTesteSummaryDTO modelo;

    private List<ParametroSummaryDTO> parametros;

    private List<FraudeResponseDTO> fraudes;

    public TesteDeQualidadeResponseDTO(TesteDeQualidadeEntity entity) {

        this.id = entity.getId();

        this.dataEHorario = entity.getDataEHorario();

        this.modelo = new ModeloTesteSummaryDTO(entity.getModelo());

        this.parametros = entity.getParametros()
                .stream()
                .map(ParametroSummaryDTO::new)
                .toList();

        this.fraudes = entity.getFraudes()
                .stream()
                .map(FraudeResponseDTO::new)
                .toList();
    }
}