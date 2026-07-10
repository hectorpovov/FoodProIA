package com.foodProIA.FoodProIA.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_FUNCIONARIO")
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class FuncionarioEntity extends UsuarioEntity{

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
}
