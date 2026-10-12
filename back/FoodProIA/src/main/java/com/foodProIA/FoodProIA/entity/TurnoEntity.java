package com.foodProIA.FoodProIA.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_TURNO")
@Getter
@Setter
@NoArgsConstructor
public class TurnoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String descricao;

    private LocalTime horaInicio;

    private LocalTime horaTermino;

    @OneToMany(mappedBy = "turno")
    private List<PlanejamentoDeProducaoEntity> planejamentosDeProducao = new ArrayList<>();

    @OneToMany(mappedBy = "turno")
    private List<FuncionarioEntity> funcionarios = new ArrayList<>();

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass()!= obj.getClass()) return false;

        TurnoEntity other = (TurnoEntity) obj;
        return Objects.equals(id, other.id);
    }
}
