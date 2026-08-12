package com.foodProIA.FoodProIA.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_ROTA")
@Getter
@NoArgsConstructor
public class RotaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String nome;

    @Setter
    @Column(nullable = false)
    private String cidade;

    @Setter
    @Column(nullable = false)
    private String codigo;

    @Setter
    @ManyToOne
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private FornecedorEntity fornecedor;


    @OneToMany(mappedBy = "rota",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ProgramacaoRotaEntity> programacoes = new ArrayList<>();

    @Setter
    private String codigoDeRodovia;

    @Setter
    @Column(nullable = false)
    private Double quantidade;

    @Setter
    @Column(nullable = false)
    private int prioridade;


    public void adicionaProgramacao(ProgramacaoRotaEntity programacao){
        programacoes.add(programacao);
        programacao.setRota(this);
    }

    public void removeProgramacao(ProgramacaoRotaEntity programacao){
        programacoes.remove(programacao);
        programacao.setRota(null);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass()!= obj.getClass()) return false;

        RotaEntity other = (RotaEntity) obj;
        return Objects.equals(id, other.id);
    }


}
