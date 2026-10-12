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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_ROTEIRO_DE_PRODUCAO")
@Getter
@Setter
@NoArgsConstructor
public class RoteiroDeProducaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;


    @Column(nullable = false)
    private String nome;

    private boolean status;

    @OneToMany(mappedBy = "roteiro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InsumoRoteiroEntity> insumos = new ArrayList<>();

    @OneToMany(mappedBy = "roteiro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MateriaPrimaRoteiroEntity> materiasPrimas = new ArrayList<>();

    @OneToMany(mappedBy = "roteiro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlanejamentoDeProducaoEntity> planejamentos = new ArrayList<>();

    @ManyToMany(mappedBy = "roteirosDeProducao")
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

        RoteiroDeProducaoEntity other = (RoteiroDeProducaoEntity) obj;
        return Objects.equals(id, other.id);
    }


}
