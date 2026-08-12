package com.foodProIA.FoodProIA.dto.response;

import com.foodProIA.FoodProIA.entity.InsumoRoteiroEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InsumoRoteiroResponseDTO {
    private Long insumoId;
    private Long roteiroId;
    private String nomeInsumo;
    private Double quantidade;
    private String unidadeDeMedida;

    public InsumoRoteiroResponseDTO(InsumoRoteiroEntity insumoRoteiro){
        this.insumoId = insumoRoteiro.getInsumo().getId();
        this.roteiroId = insumoRoteiro.getRoteiro().getId();
        this.nomeInsumo = insumoRoteiro.getInsumo().getNome();
        this.quantidade = insumoRoteiro.getQuantidade();
        this.unidadeDeMedida = insumoRoteiro.getUnidadeDeMedida();
    }
}
