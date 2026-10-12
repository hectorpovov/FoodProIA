package com.foodProIA.FoodProIA.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "TB_PLANEJAMENTO_MANUTENCAO")
@Getter
@Setter
@NoArgsConstructor
public class PlanejamentoManutencaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private LocalDateTime data;

    private String numeroDeSerieEquipamento;

    private Duration tempoParadoEstipulado;

    private Integer diasParadoEstipulado;

    private Integer mesesParadoEstipulado;

    private String comentario;

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass()!= obj.getClass()) return false;

        PlanejamentoManutencaoEntity other = (PlanejamentoManutencaoEntity) obj;
        return Objects.equals(id, other.id);
    }
}
