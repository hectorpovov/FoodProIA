package com.foodProIA.FoodProIA.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "TB_MATERIA_PRIMA_ROTEIRO")
@Getter
@Setter
@NoArgsConstructor
public class MateriaPrimaRoteiroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private BigDecimal quantidade;

    @ManyToOne
    @JoinColumn(name = "materia_prima_id", nullable = false)
    private MateriaPrimaEntity materiaPrima;

    @ManyToOne
    @JoinColumn(name = "roteiro_id", nullable = false)
    private RoteiroDeProducaoEntity roteiro;

    public MateriaPrimaRoteiroEntity(MateriaPrimaEntity materiaPrima, RoteiroDeProducaoEntity roteiro, BigDecimal quantidade){

        this.materiaPrima = materiaPrima;
        this.roteiro = roteiro;
        this.quantidade = quantidade;

        roteiro.getMateriasPrimas().add(this);
        materiaPrima.getRoteiros().add(this);
    }


    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass()!= obj.getClass()) return false;

        MateriaPrimaRoteiroEntity other = (MateriaPrimaRoteiroEntity) obj;
        return Objects.equals(id, other.id);
    }
}