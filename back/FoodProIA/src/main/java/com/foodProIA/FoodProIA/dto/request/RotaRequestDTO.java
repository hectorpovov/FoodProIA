package com.foodProIA.FoodProIA.dto.request;


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
public class RotaRequestDTO {

    @NotBlank
    private String nome;
    @NotBlank
    private String cidade;
    @NotBlank
    private String codigo;
    @NotNull
    private Long idFornecedor;
    @NotBlank
    private String codigoDeRodovia;
    @NotNull
    private Double quantidade;
    @NotNull
    private int prioridade;
}
