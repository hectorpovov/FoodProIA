package com.foodProIA.FoodProIA.dto.response;

import java.time.LocalTime;
import java.util.List;

import com.foodProIA.FoodProIA.dto.summary.FuncionarioSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.PlanejamentoDeProducaoSummaryDTO;
import com.foodProIA.FoodProIA.entity.TurnoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TurnoResponseDTO {

    private Long id;
    private String descricao;
    private LocalTime horaInicio;
    private LocalTime horaTermino;

    private List<PlanejamentoDeProducaoSummaryDTO>
            planejamentosDeProducao;

    private List<FuncionarioSummaryDTO> funcionarios;

    public TurnoResponseDTO(TurnoEntity turno) {

        this.id = turno.getId();
        this.descricao = turno.getDescricao();
        this.horaInicio = turno.getHoraInicio();
        this.horaTermino = turno.getHoraTermino();

        this.planejamentosDeProducao =
                turno.getPlanejamentosDeProducao()
                        .stream()
                        .map(PlanejamentoDeProducaoSummaryDTO::new)
                        .toList();

        this.funcionarios = turno.getFuncionarios()
                .stream()
                .map(FuncionarioSummaryDTO::new)
                .toList();
    }
}