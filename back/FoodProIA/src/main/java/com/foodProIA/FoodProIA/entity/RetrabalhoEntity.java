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

@Entity
@Table(name = "TB_RETRABALHO")
@Getter
@Setter
@NoArgsConstructor
public class RetrabalhoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private LocalDateTime data;

    private Duration tempoGasto;

    private String comentario;

    @ManyToOne
    @JoinColumn(name = "registro_de_producao_id", nullable = false)
    private RegistroDeProducaoEntity registroDeProducao;

    @ManyToOne
    @JoinColumn(name = "etapa_id")
    private EtapaEntity etapa;

    @OneToMany(mappedBy = "retrabalho")
    private List<PerdaEntity> perdas = new ArrayList<>();

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass()!= obj.getClass()) return false;

        RetrabalhoEntity other = (RetrabalhoEntity) obj;
        return Objects.equals(id, other.id);
    }
}
