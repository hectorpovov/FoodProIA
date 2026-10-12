package com.foodProIA.FoodProIA.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_REGISTRO_DE_PRODUCAO")
@Getter
@Setter
@NoArgsConstructor
public class RegistroDeProducaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    private BigDecimal quantidadeProduzida;

    private Duration tempoMaquina;

    private BigDecimal quantidadeGasta;

    private Boolean aprovado;

    private BigDecimal quantidadeRejeitada;

    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoEntity produto;

    @OneToMany(mappedBy = "registroDeProducao")
    private List<EtapaEntity> etapas = new ArrayList<>();

    @OneToMany(mappedBy = "registroDeProducao")
    private List<LoteMateriaPrimaRegistroDeProducaoEntity> lotesMateriaPrima = new ArrayList<>();

    @OneToMany(mappedBy = "registroDeProducao")
    private List<LoteInsumoRegistroDeProducaoEntity> lotesInsumos = new ArrayList<>();

    @OneToMany(mappedBy = "registroDeProducao")
    private List<PerdaEntity> perdas = new ArrayList<>();

    @OneToMany(mappedBy = "registroDeProducao")
    private List<ParadaEntity> paradas = new ArrayList<>();

    @OneToMany(mappedBy = "registroDeProducao")
    private List<RetrabalhoEntity> retrabalhos = new ArrayList<>();

    @OneToOne(mappedBy = "registroDeProducao")
    private LoteProdutoEntity loteProduto;

    @ManyToMany
    @JoinTable(
        name = "TB_REGISTRO_PRODUCAO_FUNCIONARIO",
        joinColumns = @JoinColumn(name = "registro_producao_id"),
        inverseJoinColumns = @JoinColumn(name = "funcionario_id")
    )
    private List<FuncionarioEntity> funcionarios;

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass()!= obj.getClass()) return false;

        RegistroDeProducaoEntity other = (RegistroDeProducaoEntity) obj;
        return Objects.equals(id, other.id);
    }
}