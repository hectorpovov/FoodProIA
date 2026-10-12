package com.foodProIA.FoodProIA.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_TIPO_ETAPA")
@Getter
@Setter
@NoArgsConstructor
public class TipoEtapaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String nome;

    @ManyToMany
    @JoinTable(
        name = "TB_TIPO_ETAPA_PARAMETRO",
        joinColumns = @JoinColumn(name = "tipo_etapa_id"),
        inverseJoinColumns = @JoinColumn(name = "tipo_parametro_id")
    )
    private List<TipoParametroEntity> parametros = new ArrayList<>();

    private String observacoes;

    private Duration tempoEstimado;

    @ManyToOne
    @JoinColumn(name = "equipamento_id")
    private EquipamentoEntity equipamento;

    @ManyToMany
    @JoinTable(
        name = "TB_TIPO_ETAPA_ROTEIRO",
        joinColumns = @JoinColumn(name = "tipo_etapa_id"),
        inverseJoinColumns = @JoinColumn(name = "roteiro_id")
    )
    private List<RoteiroDeProducaoEntity> roteirosDeProducao = new ArrayList<>();

    @OneToMany(mappedBy = "tipoEtapa")
    private List<EtapaEntity> etapas = new ArrayList<>();

    @ManyToMany(mappedBy = "tiposEtapa")
    private List<MateriaPrimaEntity> materiasPrimas = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "TB_TIPO_ETAPA_INSUMO",
        joinColumns = @JoinColumn(name = "tipo_etapa_id"),
        inverseJoinColumns = @JoinColumn(name = "insumo_id")
    )
    private List<InsumoEntity> insumos = new ArrayList<>();

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass()!= obj.getClass()) return false;

        TipoEtapaEntity other = (TipoEtapaEntity) obj;
        return Objects.equals(id, other.id);
    }
}