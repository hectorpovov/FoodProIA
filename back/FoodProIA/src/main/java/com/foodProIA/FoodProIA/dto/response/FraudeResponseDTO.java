package com.foodProIA.FoodProIA.dto.response;

import java.time.LocalDateTime;

import com.foodProIA.FoodProIA.dto.summary.FornecedorSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.TesteDeQualidadeSummaryDTO;
import com.foodProIA.FoodProIA.entity.FraudeEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FraudeResponseDTO {

    private Long id;
    private LocalDateTime dataEHorario;
    private String classificacao;
    private String descricao;
    private FornecedorSummaryDTO fornecedor;
    private TesteDeQualidadeSummaryDTO testeDeQualidade;

    public FraudeResponseDTO(FraudeEntity fraude){
        this.id = fraude.getId();
        this.classificacao = fraude.getClassificacao();
        this.dataEHorario = fraude.getDataEHorario();
        this.descricao = fraude.getDescricao();
        this.fornecedor = new FornecedorSummaryDTO(fraude.getFornecedor());
        this.testeDeQualidade = new TesteDeQualidadeSummaryDTO(fraude.getTesteDeQualidade());
        
    }

}
