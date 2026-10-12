package com.foodProIA.FoodProIA.dto.summary;

import com.foodProIA.FoodProIA.entity.TipoEtapaEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TipoEtapaSummaryDTO {

    private Long id;
    private String nome;

    public TipoEtapaSummaryDTO(TipoEtapaEntity tipoEtapa) {
        this.id = tipoEtapa.getId();
        this.nome = tipoEtapa.getNome();
    }
}