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
@Table(name = "TB_MATERIA_PRIMA")
@Getter
@Setter
@NoArgsConstructor
public class MateriaPrimaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String nome;

    private String categoria;

    private String unidadeDeMedida;

    private String descricao;

    private Integer anosValidade;

    private Integer mesesValidade;

    private Integer diasValidade;

    @OneToMany(mappedBy = "materiaPrima")
    private List<LoteMateriaPrimaEntity> lotes = new ArrayList<>();

    @OneToMany(mappedBy = "materiaPrima", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InformacaoNutricionalMateriaPrimaEntity> informacoesNutricionais = new ArrayList<>();

    @OneToMany(mappedBy = "materiaPrima")
    private List<MateriaPrimaRoteiroEntity> roteiros = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "TB_MATERIA_PRIMA_TIPO_ETAPA",
        joinColumns = @JoinColumn(name = "materia_prima_id"),
        inverseJoinColumns = @JoinColumn(name = "tipo_etapa_id")
    )
    private List<TipoEtapaEntity> tiposEtapa = new ArrayList<>();

    @ManyToMany(mappedBy = "materiasPrimas")
    private List<FichaProdutoEntity> fichasProduto = new ArrayList<>();


    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass()!= obj.getClass()) return false;

        MateriaPrimaEntity other = (MateriaPrimaEntity) obj;
        return Objects.equals(id, other.id);
    }
}