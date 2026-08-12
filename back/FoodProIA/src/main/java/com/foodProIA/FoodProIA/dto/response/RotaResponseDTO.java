package com.foodProIA.FoodProIA.dto.response;

import java.util.List;

import com.foodProIA.FoodProIA.dto.summary.FornecedorSummaryDTO;
import com.foodProIA.FoodProIA.entity.RotaEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RotaResponseDTO {

    private Long id;
    private String nome;
    private String cidade;
    private String codigo;
    private FornecedorSummaryDTO fornecedor;
    private List<ProgramacaoRotaResponseDTO> programacoes;
    private String codigoDeRodovia;
    private Double quantidade;
    private int prioridade;

    public RotaResponseDTO(RotaEntity rota){
        this.id = rota.getId();
        this.nome = rota.getNome();
        this.cidade = rota.getCidade();
        this.codigo = rota.getCodigo();
        this.fornecedor = new FornecedorSummaryDTO(rota.getFornecedor());
        this.programacoes = rota.getProgramacoes()
                                .stream()
                                .map(ProgramacaoRotaResponseDTO::new)
                                .toList();
        this.codigoDeRodovia = rota.getCodigoDeRodovia();
        this.quantidade = rota.getQuantidade();
        this.prioridade = rota.getPrioridade();
    }
}