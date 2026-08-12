package com.foodProIA.FoodProIA.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModeloTesteRequestDTO {

    @NotBlank
    private String nome;

    private List<TipoParametroRequestDTO> parametros;
}