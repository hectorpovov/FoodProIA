package com.foodProIA.FoodProIA.entity;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_PARAMETRO")
@Getter
@NoArgsConstructor
public class ParametroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String valor;

    @Setter
    @ManyToOne
    @JoinColumn(name = "tipo_parametro_id")
    private TipoParametroEntity tipo;

    @Setter
    @ManyToMany
    @JoinTable(name = "parametro_teste_de_qualidade",
                joinColumns = @JoinColumn(name = "parametro_id"),
                inverseJoinColumns = @JoinColumn(name = "teste_de_qualidade_id")
    )
    private List<TesteDeQualidadeEntity> testesDeQualidade;

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
