package com.foodProIA.FoodProIA.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_PLANEJAMENTO_DE_PRODUCAO")
@Getter
@Setter
@NoArgsConstructor
public class PlanejamentoDeProducaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private BigDecimal quantidadePlanejada;

    private Boolean status;

    private LocalDateTime dataInicio;

    private LocalDateTime prazo;

    @ManyToOne
    @JoinColumn(name = "turno_id")
    private TurnoEntity turno;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoEntity produto;

    @ManyToOne
    @JoinColumn(name = "roteiro_id")
    private RoteiroDeProducaoEntity roteiro;

    @ManyToMany
    @JoinTable(
        name = "TB_PLANEJAMENTO_EQUIPAMENTO",
        joinColumns = @JoinColumn(name = "planejamento_id"),
        inverseJoinColumns = @JoinColumn(name = "equipamento_id")
    )
    private List<EquipamentoEntity> equipamentos = new ArrayList<>();

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass()!= obj.getClass()) return false;

        PlanejamentoDeProducaoEntity other = (PlanejamentoDeProducaoEntity) obj;
        return Objects.equals(id, other.id);
    }
}
