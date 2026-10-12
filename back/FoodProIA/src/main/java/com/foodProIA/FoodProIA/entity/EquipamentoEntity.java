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
@Table(name = "TB_EQUIPAMENTO")
@Getter
@Setter
@NoArgsConstructor
public class EquipamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String numeroDeSerie;

    private String fabricante;

    private LocalDateTime dataCompra;

    private BigDecimal capacidade;

    private Integer potencia;

    private Double consumoEnergetico;

    private String velocidadeDeOperacao;

    private Duration tempoEntreManutencoes;

    @ManyToMany(mappedBy = "equipamentos")
    private List<PlanejamentoDeProducaoEntity> planejamentosDeProducao = new ArrayList<>();

    @OneToMany(mappedBy = "equipamento")
    private List<TipoEtapaEntity> tiposEtapas = new ArrayList<>();

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass()!= obj.getClass()) return false;

        EquipamentoEntity other = (EquipamentoEntity) obj;
        return Objects.equals(id, other.id);
    }
}