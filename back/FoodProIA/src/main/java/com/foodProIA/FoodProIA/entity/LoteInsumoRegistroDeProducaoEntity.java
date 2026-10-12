package com.foodProIA.FoodProIA.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "TB_LOTE_INSUMO_REGISTRO_PRODUCAO")
@Getter
@Setter
@NoArgsConstructor
public class LoteInsumoRegistroDeProducaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private BigDecimal quantidade;

    @ManyToOne
    @JoinColumn(name = "lote_insumo_id", nullable = false)
    private LoteInsumoEntity loteInsumo;

    @ManyToOne
    @JoinColumn(name = "registro_de_producao_id", nullable = false)
    private RegistroDeProducaoEntity registroDeProducao;

    public LoteInsumoRegistroDeProducaoEntity(LoteInsumoEntity loteInsumo,
                                                RegistroDeProducaoEntity registroDeProducao,
                                                BigDecimal quantidade){

        this.loteInsumo = loteInsumo;
        this.registroDeProducao = registroDeProducao;
        this.quantidade = quantidade;

        loteInsumo.getRegistrosDeProducao().add(this);
        registroDeProducao.getLotesInsumos().add(this);

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

        LoteInsumoRegistroDeProducaoEntity other = (LoteInsumoRegistroDeProducaoEntity) obj;
        return Objects.equals(id, other.id);
    }
}