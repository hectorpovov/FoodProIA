package com.foodProIA.FoodProIA.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.foodProIA.FoodProIA.dto.summary.MateriaPrimaSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.ProdutoSummaryDTO;
import com.foodProIA.FoodProIA.entity.FichaProdutoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FichaProdutoResponseDTO {

    private Long id;
    private String nomeTecnico;
    private LocalDateTime dataCriacao;
    private String instrucaoDeManipulacao;
    private String tipoDeEmbalagem;
    private Boolean status;
    private List<MateriaPrimaSummaryDTO> materiasPrimas;
    private ProdutoSummaryDTO produto;

    public FichaProdutoResponseDTO(FichaProdutoEntity fichaProduto) {

        this.id = fichaProduto.getId();
        this.nomeTecnico = fichaProduto.getNomeTecnico();
        this.dataCriacao = fichaProduto.getDataCriacao();
        this.instrucaoDeManipulacao =
                fichaProduto.getInstrucaoDeManipulacao();
        this.tipoDeEmbalagem = fichaProduto.getTipoDeEmbalagem();
        this.status = fichaProduto.getStatus();

        this.materiasPrimas = fichaProduto.getMateriasPrimas()
                .stream()
                .map(MateriaPrimaSummaryDTO::new)
                .toList();

        if (fichaProduto.getProduto() != null) {
            this.produto =
                    new ProdutoSummaryDTO(fichaProduto.getProduto());
        }
    }
}