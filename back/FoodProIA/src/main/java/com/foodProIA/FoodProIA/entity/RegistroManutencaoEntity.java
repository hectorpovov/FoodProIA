package com.foodProIA.FoodProIA.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.foodProIA.FoodProIA.enums.TipoManutencao;

@Entity
@Table(name = "TB_REGISTRO_MANUTENCAO")
@Getter
@Setter
@NoArgsConstructor
public class RegistroManutencaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private LocalDateTime data;

    private String numeroDeSerieEquipamento;

    @Enumerated(EnumType.STRING)
    private TipoManutencao tipoDeManutencao;

    private Duration tempoParado;

    private Integer diasParado;

    private Integer mesesParado;

    private LocalDateTime dataDeLiberacao;

    private String comentario;

    @ManyToMany
    @JoinTable(
        name = "TB_REGISTRO_MANUTENCAO_FUNCIONARIO",
        joinColumns = @JoinColumn(name = "registro_manutencao_id"),
        inverseJoinColumns = @JoinColumn(name = "funcionario_id")
    )
    private List<FuncionarioEntity> funcionarios = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "planejamento_manutencao_id")
    private PlanejamentoManutencaoEntity planejamentoManutencao;

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass()!= obj.getClass()) return false;

        RegistroManutencaoEntity other = (RegistroManutencaoEntity) obj;
        return Objects.equals(id, other.id);
    }
}
