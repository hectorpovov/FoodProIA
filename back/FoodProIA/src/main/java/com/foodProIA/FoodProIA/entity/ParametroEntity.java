package com.foodProIA.FoodProIA.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_PARAMETRO")
@Getter
@Setter
@NoArgsConstructor
public class ParametroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String valor;

    @ManyToOne 
    @JoinColumn(name = "etapa_id")
    private EtapaEntity etapa;

    @ManyToOne
    @JoinColumn(name = "tipo_parametro_id")
    private TipoParametroEntity tipo;

    @ManyToOne
    @JoinColumn(name = "teste_de_qualidade_id")
    private TesteDeQualidadeEntity testeDeQualidade;

    public ParametroEntity(String valor, TipoParametroEntity tipo){
        this.valor = valor;
        this.tipo = tipo;
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

        ParametroEntity other = (ParametroEntity) obj;
        return Objects.equals(id, other.id);
    }



}
