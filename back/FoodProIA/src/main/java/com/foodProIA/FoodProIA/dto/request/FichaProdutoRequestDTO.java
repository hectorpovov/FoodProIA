package com.foodProIA.FoodProIA.dto.request;

import java.time.LocalDateTime;
import java.util.List;

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
public class FichaProdutoRequestDTO {

    @NotBlank
    private String nomeTecnico;

    @NotNull
    private LocalDateTime dataCriacao;

    @NotBlank
    private String instrucaoDeManipulacao;

    @NotBlank
    private String tipoDeEmbalagem;

    @NotNull
    private Boolean status;

    private List<Long> idsMateriasPrimas;
}