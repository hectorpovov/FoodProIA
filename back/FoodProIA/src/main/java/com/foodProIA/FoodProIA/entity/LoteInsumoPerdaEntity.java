package com.foodProIA.FoodProIA.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "TB_LOTE_INSUMO_PERDA")
@Getter
@Setter
@NoArgsConstructor
public class LoteInsumoPerdaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private BigDecimal quantidade;

    @ManyToOne
    @JoinColumn(name = "lote_insumo_id", nullable = false)
    private LoteInsumoEntity loteInsumo;

    @ManyToOne
    @JoinColumn(name = "perda_id", nullable = false)
    private PerdaEntity perda;

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass()!= obj.getClass()) return false;

        LoteInsumoPerdaEntity other = (LoteInsumoPerdaEntity) obj;
        return Objects.equals(id, other.id);
    }
}