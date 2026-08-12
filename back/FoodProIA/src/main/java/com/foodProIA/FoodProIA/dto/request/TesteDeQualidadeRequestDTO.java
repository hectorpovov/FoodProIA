package com.foodProIA.FoodProIA.dto.request;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TesteDeQualidadeRequestDTO {

    private LocalDateTime dataEHorario;
    private Long idModeloTeste;
    private List<ParametroRequestDTO> parametros;

}
