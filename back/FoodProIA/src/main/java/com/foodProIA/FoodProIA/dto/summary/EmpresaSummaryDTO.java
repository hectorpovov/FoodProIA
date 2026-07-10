package com.foodProIA.FoodProIA.dto.summary;

import com.foodProIA.FoodProIA.entity.EmpresaEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmpresaSummaryDTO {

    private Long id;
    private String nomeFantasia;

    public EmpresaSummaryDTO(EmpresaEntity empresa){
        this.id = empresa.getId();
        this.nomeFantasia = empresa.getNomeFantasia();
    }
}
