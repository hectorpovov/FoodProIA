package com.foodProIA.FoodProIA.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "TB_INFORMACAO_NUTRICIONAL_MATERIA_PRIMA")
@Getter
@Setter
@NoArgsConstructor
public class InformacaoNutricionalMateriaPrimaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "materia_prima_id", nullable = false)
    private MateriaPrimaEntity materiaPrima;

    @ManyToOne
    @JoinColumn(name = "informacao_nutricional_id", nullable = false)
    private InformacaoNutricionalEntity informacaoNutricional;

    public InformacaoNutricionalMateriaPrimaEntity(BigDecimal valor,
                                                    MateriaPrimaEntity materiaPrima,
                                                    InformacaoNutricionalEntity informacaoNutricional){
                                                        
        this.informacaoNutricional = informacaoNutricional;
        this.materiaPrima = materiaPrima;
        this.valor = valor;

        materiaPrima.getInformacoesNutricionais().add(this);
        informacaoNutricional.getMateriasPrimas().add(this);
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

        InformacaoNutricionalMateriaPrimaEntity other = (InformacaoNutricionalMateriaPrimaEntity) obj;
        return Objects.equals(id, other.id);
    }
}