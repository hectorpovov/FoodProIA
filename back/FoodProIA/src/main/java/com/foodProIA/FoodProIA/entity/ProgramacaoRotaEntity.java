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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_PROGRAMACAO_ROTA")
@Getter
@NoArgsConstructor
public class ProgramacaoRotaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String motoristaResponsavel;//TODO: verificar se o motorista é string mesmo ou é alguma classe separada
    
    @Setter
    @ManyToOne
    @JoinColumn(name = "rota_id", nullable = false)
    private RotaEntity rota;

    @Setter
    @Column(nullable = false)
    private Double quilometragem;

    @Setter
    @Column(nullable = false)
    private Double volumeEstimado;

    @Setter
    @Column(nullable = false)
    private Double capacidadeDoCaminhao;

    @Setter
    @Column(nullable = false)
    private String placaVeiculo;

    @Setter
    @Column(nullable = false)
    private LocalDateTime horarioSaida;

    @Setter
    @Column(nullable = false)
    private String codigoRastreamentoMercadoria;

    @Setter
    @Column(nullable = false)
    private StatusProgramacaoRota status;


    public enum StatusProgramacaoRota{
        EM_ROTA,
        PENDENTE
    }
}
