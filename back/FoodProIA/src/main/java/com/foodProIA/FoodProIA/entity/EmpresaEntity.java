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
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table(name = "TB_EMPRESA")
@NoArgsConstructor
public class EmpresaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, unique = true)
    private String cnpj;

    @Setter
    @Column(nullable = false, unique = true)
    private String razaoSocial;

    @Setter
    @Column(nullable = false)
    private String nomeFantasia;


    @Setter
    @Column(nullable = false, unique = true)
    private String email;

    @Setter
    @Column(nullable = false)
    private String telefone;
    
    @Lob
    @Setter
    @Column
    private String observacoes;

    @OneToMany(mappedBy = "empresa")
    private List<FuncionarioEntity> funcionarios = new ArrayList<>();;

    @OneToMany(mappedBy = "empresa")
    private List<SetorEntity> setores = new ArrayList<>();;

    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", unique = true,nullable = false)
    private EnderecoEntity endereco;

    public void adicionaFuncionario(FuncionarioEntity funcionario){
        funcionarios.add(funcionario);
        funcionario.setEmpresa(this);
    }

    public void adicionaSetor(SetorEntity setor){
        setores.add(setor);
        setor.setEmpresa(this);
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

        EmpresaEntity other = (EmpresaEntity) obj;
        return Objects.equals(id, other.id);
    }

}
