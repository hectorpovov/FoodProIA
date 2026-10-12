package com.foodProIA.FoodProIA.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.foodProIA.FoodProIA.enums.TipoInsumo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_INSUMO")
@Getter
@NoArgsConstructor
@Setter
public class InsumoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoInsumo tipoInsumo;

    @OneToMany(mappedBy = "insumo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InsumoRoteiroEntity> roteiros = new ArrayList<>();

    @ManyToMany(mappedBy = "insumos")
    private List<TipoEtapaEntity> tipoEtapas = new ArrayList<>();

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass()!= obj.getClass()) return false;

        InsumoEntity other = (InsumoEntity) obj;
        return Objects.equals(id, other.id);
    }



}
