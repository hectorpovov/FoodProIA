package com.foodProIA.FoodProIA.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_INFORMACAO_NUTRICIONAL")
@Getter
@Setter
@NoArgsConstructor
public class InformacaoNutricionalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String nome;

    private String unidadeDeMedida;

    @OneToMany(mappedBy = "informacaoNutricional")
    private List<InformacaoNutricionalMateriaPrimaEntity> materiasPrimas = new ArrayList<>();

    @OneToMany(mappedBy = "informacaoNutricional")
    private List<InformacaoNutricionalProdutoEntity> produtos = new ArrayList<>();

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass()!= obj.getClass()) return false;

        InformacaoNutricionalEntity other = (InformacaoNutricionalEntity) obj;
        return Objects.equals(id, other.id);
    }
}