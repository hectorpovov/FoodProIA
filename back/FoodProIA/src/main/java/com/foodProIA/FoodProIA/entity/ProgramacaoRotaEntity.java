package com.foodProIA.FoodProIA.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "TB_PROGRAMACAO_ROTA")
@Getter
@Setter
@NoArgsConstructor
public class ProgramacaoRotaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String motoristaResponsavel;//TODO: verificar se o motorista é string mesmo ou é alguma classe separada

    @ManyToOne
    @JoinColumn(name = "rota_id", nullable = false)
    private RotaEntity rota;

    @Column(nullable = false)
    private Double quilometragem;

    @Column(nullable = false)
    private Double volumeEstimado;

    @Column(nullable = false)
    private Double capacidadeDoCaminhao;

    @Column(nullable = false)
    private String placaVeiculo;

    @Column(nullable = false)
    private LocalDateTime horarioSaida;

    @Column(nullable = false)
    private String codigoRastreamentoMercadoria;

    @Column(nullable = false)
    private StatusProgramacaoRota status;


    public enum StatusProgramacaoRota{
        EM_ROTA,
        PENDENTE
    }
}
