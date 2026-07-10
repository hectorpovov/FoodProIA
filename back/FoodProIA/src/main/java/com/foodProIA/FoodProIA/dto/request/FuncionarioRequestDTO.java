package com.foodProIA.FoodProIA.dto.request;

import java.math.BigDecimal;

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
public class FuncionarioRequestDTO {
    
    @NotBlank
    private String cargo;

    @NotNull
    private BigDecimal custoPorHora;

    @NotNull
    private Long idSetor;
    
    @NotNull
    private Long idEmpresa;
}
