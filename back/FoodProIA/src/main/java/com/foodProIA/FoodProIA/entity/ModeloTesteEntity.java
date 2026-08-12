package com.foodProIA.FoodProIA.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_MODELO_TESTE")
@Getter
@NoArgsConstructor
public class ModeloTesteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "modeloTeste",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<TipoParametroEntity> parametros = new ArrayList<>();

    public void adicionaParametro(TipoParametroEntity parametro){
        parametros.add(parametro);
        parametro.setModeloTeste(this);;
    }

    public void removeParametro(TipoParametroEntity parametro){
        parametros.remove(parametro);
        parametro.setModeloTeste(null);
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

        ModeloTesteEntity other = (ModeloTesteEntity) obj;
        return Objects.equals(id, other.id);
    }

}
