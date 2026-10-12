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

import com.foodProIA.FoodProIA.enums.EstadoEtapa;

@Entity
@Table(name = "TB_ETAPA")
@Getter
@Setter
@NoArgsConstructor
public class EtapaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @OneToMany(mappedBy = "etapa")
    private List<ParametroEntity> parametros = new ArrayList<>();

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    private String observacoes;

    private Duration tempoGasto;

    private BigDecimal consumoAgua;

    @Enumerated(EnumType.STRING)
    private EstadoEtapa estado;

    @ManyToOne
    @JoinColumn(name = "tipo_etapa_id", nullable = false)
    private TipoEtapaEntity tipoEtapa;

    @ManyToOne
    @JoinColumn(name = "registro_de_producao_id", nullable = false)
    private RegistroDeProducaoEntity registroDeProducao;

    @OneToMany(mappedBy = "etapa")
    private List<PerdaEntity> perdas = new ArrayList<>();

    @OneToMany(mappedBy = "etapa")
    private List<ParadaEntity> paradas = new ArrayList<>();

    @OneToMany(mappedBy = "etapa")
    private List<RetrabalhoEntity> retrabalhos = new ArrayList<>();


    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass()!= obj.getClass()) return false;

        EtapaEntity other = (EtapaEntity) obj;
        return Objects.equals(id, other.id);
    }
}
