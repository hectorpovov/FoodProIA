package com.foodProIA.FoodProIA.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "TB_LOTE_MATERIA_PRIMA_REGISTRO_PRODUCAO")
@Getter
@Setter
@NoArgsConstructor
public class LoteMateriaPrimaRegistroDeProducaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private BigDecimal quantidade;

    @ManyToOne
    @JoinColumn(name = "lote_materia_prima_id", nullable = false)
    private LoteMateriaPrimaEntity loteMateriaPrima;

    @ManyToOne
    @JoinColumn(name = "registro_de_producao_id", nullable = false)
    private RegistroDeProducaoEntity registroDeProducao;


    public LoteMateriaPrimaRegistroDeProducaoEntity(LoteMateriaPrimaEntity loteMateriaPrima,
                                                    RegistroDeProducaoEntity registroDeProducao,
                                                    BigDecimal quantidade
    ){
        this.loteMateriaPrima = loteMateriaPrima;
        this.quantidade = quantidade;
        this.registroDeProducao = registroDeProducao;

        loteMateriaPrima.getRegistrosDeProducao().add(this);
        registroDeProducao.getLotesMateriaPrima().add(this);
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

        LoteMateriaPrimaRegistroDeProducaoEntity other = (LoteMateriaPrimaRegistroDeProducaoEntity) obj;
        return Objects.equals(id, other.id);
    }
}
