package com.foodProIA.FoodProIA.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_EMPRESA")
@NoArgsConstructor
public class EmpresaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false)
    private String cnpj;

    @Setter
    @Getter
    @Column(nullable = false)
    private String razaoSocial;

    @Setter
    @Getter
    @Column(nullable = false)
    private String nomeFantasia;


    @Setter
    @Getter
    @Column(nullable = false)
    private String email;

    @Setter
    @Getter
    @Column(nullable = false)
    private String telefone;
    
    @Lob
    @Setter
    @Getter
    @Column
    private String observacoes;

    @Getter
    @OneToMany(mappedBy = "empresa")
    private List<FuncionarioEntity> funcionarios;

    @Getter
    @OneToMany(mappedBy = "empresa")
    private List<SetorEntity> setores;

}
