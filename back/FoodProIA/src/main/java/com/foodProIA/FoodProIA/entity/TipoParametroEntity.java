package com.foodProIA.FoodProIA.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_TIPO_PARAMETRO")
@Getter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class TipoParametroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   

    @Setter
    @Column(nullable = false)
    private String nome;

    @Setter
    @ManyToOne
    @JoinColumn(name = "modelo_teste_id")
    private ModeloTesteEntity modeloTeste;

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass()!= obj.getClass()) return false;

        TipoParametroEntity other = (TipoParametroEntity) obj;
        return Objects.equals(id, other.id);
    }

}
