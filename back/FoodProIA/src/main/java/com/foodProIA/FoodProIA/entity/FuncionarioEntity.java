package com.foodProIA.FoodProIA.entity;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_FUNCIONARIO")
@Getter
@Setter
@NoArgsConstructor
public class FuncionarioEntity extends UsuarioEntity{

    @Column(nullable = false)
    private String cargo;

    @Column(nullable = false)
    private BigDecimal custoPorHora;

    @ManyToOne
    @JoinColumn(name = "setor_id", nullable = false)
    private SetorEntity setor;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private EmpresaEntity empresa;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", unique = true, nullable = false)
    private EnderecoEntity endereco;

    @Override
    public int hashCode(){
        return Objects.hash(this.getId());
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass()!= obj.getClass()) return false;

        FuncionarioEntity other = (FuncionarioEntity) obj;
        return Objects.equals(this.getId(), other.getId());
    }
}
