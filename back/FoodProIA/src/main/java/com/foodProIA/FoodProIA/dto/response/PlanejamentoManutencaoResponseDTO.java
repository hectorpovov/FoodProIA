package com.foodProIA.FoodProIA.dto.response;

import java.time.Duration;
import java.time.LocalDateTime;

import com.foodProIA.FoodProIA.entity.PlanejamentoManutencaoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlanejamentoManutencaoResponseDTO {

    private Long id;
    private LocalDateTime data;
    private String numeroDeSerieEquipamento;
    private Duration tempoParadoEstipulado;
    private Integer diasParadoEstipulado;
    private Integer mesesParadoEstipulado;
    private String comentario;

    public PlanejamentoManutencaoResponseDTO(
            PlanejamentoManutencaoEntity planejamento) {

        this.id = planejamento.getId();
        this.data = planejamento.getData();
        this.numeroDeSerieEquipamento =
                planejamento.getNumeroDeSerieEquipamento();
        this.tempoParadoEstipulado =
                planejamento.getTempoParadoEstipulado();
        this.diasParadoEstipulado =
                planejamento.getDiasParadoEstipulado();
        this.mesesParadoEstipulado =
                planejamento.getMesesParadoEstipulado();
        this.comentario = planejamento.getComentario();
    }
}