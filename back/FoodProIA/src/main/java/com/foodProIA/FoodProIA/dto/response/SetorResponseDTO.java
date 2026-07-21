package com.foodProIA.FoodProIA.dto.response;

import java.util.List;

import com.foodProIA.FoodProIA.dto.summary.EmpresaSummaryDTO;
import com.foodProIA.FoodProIA.entity.SetorEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SetorResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private List<FuncionarioResponseDTO> funcionarios;
    private EmpresaSummaryDTO empresa;

    public SetorResponseDTO( SetorEntity setor){
        this.id = setor.getId();
        this.nome = setor.getNome();
        this.descricao = setor.getDescricao();

        this.funcionarios = setor.getFuncionarios()
                         .stream()
                         .map(FuncionarioResponseDTO::new)
                         .toList();

        this.empresa = new EmpresaSummaryDTO(setor.getEmpresa());
    }

}
