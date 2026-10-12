package com.foodProIA.FoodProIA.dto.request;

import java.time.Duration;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoEtapaRequestDTO {

    @NotBlank
    private String nome;

    @Valid
    private List<TipoParametroRequestDTO> parametros;

    private String observacoes;

    @NotNull
    private Duration tempoEstimado;

    private Long idEquipamento;

    private List<Long> idsMateriasPrimas;

    private List<Long> idsInsumos;
}