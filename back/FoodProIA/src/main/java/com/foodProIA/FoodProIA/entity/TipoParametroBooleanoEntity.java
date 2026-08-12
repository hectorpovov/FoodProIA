package com.foodProIA.FoodProIA.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_TIPO_PARAMETRO_BOOLEANO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoParametroBooleanoEntity extends TipoParametroEntity{

    @Column(nullable = false)
    private boolean valorEsperado;

}
