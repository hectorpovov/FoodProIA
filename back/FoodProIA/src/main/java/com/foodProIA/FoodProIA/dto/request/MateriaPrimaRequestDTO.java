package com.foodProIA.FoodProIA.dto.request;

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
public class MateriaPrimaRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String categoria;

    @NotBlank
    private String unidadeDeMedida;

    private String descricao;

    @NotNull
    private Integer anosValidade;

    @NotNull
    private Integer mesesValidade;

    @NotNull
    private Integer diasValidade;

    @Valid
    private List<InformacaoNutricionalMateriaPrimaRequestDTO> informacoesNutricionais;
}