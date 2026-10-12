package com.foodProIA.FoodProIA.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.foodProIA.FoodProIA.enums.EstadoEtapa;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EtapaRequestDTO {

    @Valid
    private List<ParametroRequestDTO> parametros;

    @NotNull
    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    private String observacoes;

    private BigDecimal consumoAgua;

    @NotNull
    private EstadoEtapa estado;

    @NotNull
    private Long idTipoEtapa;

    @NotNull
    private Long idRegistroDeProducao;
}