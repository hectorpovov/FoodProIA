package com.foodProIA.FoodProIA.dto.request;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import com.foodProIA.FoodProIA.enums.TipoManutencao;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroManutencaoRequestDTO {

    @NotNull
    private LocalDateTime data;

    @NotNull
    private String numeroDeSerieEquipamento;

    @NotNull
    private TipoManutencao tipoDeManutencao;

    @NotNull
    private Duration tempoParado;

    @NotNull
    private Integer diasParado;

    @NotNull
    private Integer mesesParado;

    @NotNull
    private LocalDateTime dataDeLiberacao;

    private String comentario;

    private List<Long> idsFuncionarios;

    private Long idPlanejamentoManutencao;
}