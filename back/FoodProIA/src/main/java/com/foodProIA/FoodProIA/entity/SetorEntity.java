package com.foodProIA.FoodProIA.entity;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_SETOR")
@Getter
@NoArgsConstructor
public class SetorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    @Lob
    @Setter
    private String nome;

    @Column
    @Setter
    private String descricao;
    
    @OneToMany(mappedBy = "setor")
    private List<FuncionarioEntity> funcionarios = new ArrayList<>();;

    @ManyToOne
    @JoinColumn(name = "id_empresa", nullable = false)
    @Setter
    private EmpresaEntity empresa;

    public void adicionaFuncionario(FuncionarioEntity funcionario){
        funcionarios.add(funcionario);
        funcionario.setSetor(this);
    }

    public void removerFuncionario(FuncionarioEntity funcionario) {
        funcionarios.remove(funcionario);
        funcionario.setSetor(null);
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

        SetorEntity other = (SetorEntity) obj;
        return Objects.equals(id, other.id);
    }
}
