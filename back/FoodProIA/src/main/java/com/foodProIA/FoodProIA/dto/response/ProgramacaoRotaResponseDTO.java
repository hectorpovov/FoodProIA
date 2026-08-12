package com.foodProIA.FoodProIA.dto.response;

import java.time.LocalDateTime;

import com.foodProIA.FoodProIA.dto.summary.RotaSummaryDTO;
import com.foodProIA.FoodProIA.entity.ProgramacaoRotaEntity;
import com.foodProIA.FoodProIA.entity.ProgramacaoRotaEntity.StatusProgramacaoRota;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProgramacaoRotaResponseDTO {

    private Long id;
    private String motoristaResponsavel;
    private RotaSummaryDTO rota;
    private Double quilometragem;
    private Double volumeEstimado;
    private Double capacidadeDoCaminhao;
    private String placaVeiculo;
    private LocalDateTime horarioSaida;
    private String codigoRastreamentoMercadoria;
    private StatusProgramacaoRota status;


    public ProgramacaoRotaResponseDTO(ProgramacaoRotaEntity programacao){
        this.id = programacao.getId();
        this.motoristaResponsavel = programacao.getMotoristaResponsavel();
        this.rota = new RotaSummaryDTO(programacao.getRota());
        this.quilometragem = programacao.getQuilometragem();
        this.volumeEstimado = programacao.getVolumeEstimado();
        this.capacidadeDoCaminhao = programacao.getCapacidadeDoCaminhao();
        this.placaVeiculo = programacao.getPlacaVeiculo();
        this.horarioSaida = programacao.getHorarioSaida();
        this.codigoRastreamentoMercadoria = programacao.getCodigoRastreamentoMercadoria();
        this.status = programacao.getStatus();
    }
}
