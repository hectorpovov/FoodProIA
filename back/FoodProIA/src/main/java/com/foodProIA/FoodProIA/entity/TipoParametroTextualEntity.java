package com.foodProIA.FoodProIA.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_TIPO_PARAMETRO_TEXTUAL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoParametroTextualEntity extends TipoParametroEntity{

    @Column(nullable = false)
    private String esperado;
    
    @ElementCollection
    @CollectionTable(
        name = "TB_TIPO_PARAMETRO_TEXTO_POSSIBILIDADES",
        joinColumns = @JoinColumn(name = "tipo_parametro_id")
    )
    @Column(name = "possibilidade")
    private List<String> possibilidades = new ArrayList<>();

}
