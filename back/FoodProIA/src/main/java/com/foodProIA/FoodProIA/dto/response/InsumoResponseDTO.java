package com.foodProIA.FoodProIA.dto.response;

import java.util.List;

import com.foodProIA.FoodProIA.entity.InsumoEntity;
import com.foodProIA.FoodProIA.enums.TipoInsumo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InsumoResponseDTO {

    private Long id;

    private String nome;

    private TipoInsumo tipoInsumo;

    private List<InsumoRoteiroResponseDTO> roteiros;

    public InsumoResponseDTO(InsumoEntity entity) {

        this.id = entity.getId();
        this.nome = entity.getNome();
        this.tipoInsumo = entity.getTipoInsumo();

        this.roteiros = entity.getRoteiros()
                .stream()
                .map(InsumoRoteiroResponseDTO::new)
                .toList();
    }
}