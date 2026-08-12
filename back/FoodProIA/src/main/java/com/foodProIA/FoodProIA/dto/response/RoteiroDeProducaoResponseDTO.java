package com.foodProIA.FoodProIA.dto.response;

import java.util.List;

import com.foodProIA.FoodProIA.entity.RoteiroDeProducaoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoteiroDeProducaoResponseDTO {

    private Long id;

    private String nome;

    private boolean status;

    private List<InsumoRoteiroResponseDTO> insumos;

    public RoteiroDeProducaoResponseDTO(RoteiroDeProducaoEntity entity) {

        this.id = entity.getId();
        this.nome = entity.getNome();
        this.status = entity.isStatus();

        this.insumos = entity.getInsumos()
                .stream()
                .map(InsumoRoteiroResponseDTO::new)
                .toList();
    }
}