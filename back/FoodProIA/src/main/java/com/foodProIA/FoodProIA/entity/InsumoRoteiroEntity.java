package com.foodProIA.FoodProIA.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_insumo_roteiro")
@Getter
@Setter
@NoArgsConstructor
public class InsumoRoteiroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insumo_id", nullable = false)
    private InsumoEntity insumo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roteiro_id", nullable = false)
    private RoteiroDeProducaoEntity roteiro;

    @Column(nullable = false)
    private Double quantidade;

    @Column(nullable = false)
    private String unidadeDeMedida;

    public InsumoRoteiroEntity(
            InsumoEntity insumo,
            RoteiroDeProducaoEntity roteiro,
            Double quantidade,
            String unidadeDeMedida) {

        this.insumo = insumo;
        this.roteiro = roteiro;
        this.quantidade = quantidade;
        this.unidadeDeMedida = unidadeDeMedida;

        insumo.getRoteiros().add(this);
        roteiro.getInsumos().add(this);
    }
}