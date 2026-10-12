package com.foodProIA.FoodProIA.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "TB_LOTE_MATERIA_PRIMA_PERDA")
@Getter
@Setter
@NoArgsConstructor
public class LoteMateriaPrimaPerdaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private BigDecimal quantidade;

    @ManyToOne
    @JoinColumn(name = "lote_materia_prima_id", nullable = false)
    private LoteMateriaPrimaEntity loteMateriaPrima;

    @ManyToOne
    @JoinColumn(name = "perda_id", nullable = false)
    private PerdaEntity perda;

    public LoteMateriaPrimaPerdaEntity(LoteMateriaPrimaEntity loteMateriaPrima,
                                        PerdaEntity perda,
                                        BigDecimal quantidade){
        
        this.loteMateriaPrima = loteMateriaPrima;
        this.perda = perda;
        this.quantidade = quantidade;

        loteMateriaPrima.getPerdas().add(this);
        perda.getLotesMateriaPrima().add(this);
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

        LoteMateriaPrimaPerdaEntity other = (LoteMateriaPrimaPerdaEntity) obj;
        return Objects.equals(id, other.id);
    }
}
