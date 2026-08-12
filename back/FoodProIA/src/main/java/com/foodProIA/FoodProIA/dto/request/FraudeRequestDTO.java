package com.foodProIA.FoodProIA.dto.request;

import java.time.LocalDateTime;

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
public class FraudeRequestDTO {

    @NotNull
    private LocalDateTime dataEHorario;
    @NotBlank
    private String classificacao;
    private String descricao;
    @NotNull
    private Long idFornecedor;
    @NotNull
    private Long idTesteDeQualidade;


}
