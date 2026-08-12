package com.foodProIA.FoodProIA.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProgramacaoRotaRequestDTO {

    @NotBlank
    private String motoristaResponsavel;
    @NotNull
    private Long idRota;
    @NotNull
    private Double quilometragem;
    @NotNull
    private Double volumeEstimado;
    @NotNull
    private Double capacidadeDoCaminhao;
    @NotBlank
    private String placaVeiculo;
    @NotNull
    private LocalDateTime horarioSaida;
    @NotNull
    private String codigoRastreamentoMercadoria;
}
