package com.foodProIA.FoodProIA.dto.response;

import java.util.List;

import com.foodProIA.FoodProIA.entity.EmpresaEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmpresaResponseDTO {

    private Long id;
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String email;
    private String telefone;
    private String observacoes;
    private List<FuncionarioResponseDTO> funcionarios;
    private List<SetorResponseDTO> setores;
    
    public EmpresaResponseDTO(EmpresaEntity empresa){

        this.id = empresa.getId();
        this.cnpj = empresa.getCnpj();
        this.email = empresa.getEmail();
        this.razaoSocial = empresa.getRazaoSocial();
        this.nomeFantasia = empresa.getNomeFantasia();
        this.telefone = empresa.getTelefone();
        

        this.funcionarios = empresa.getFuncionarios()
                              .stream()
                              .map(FuncionarioResponseDTO::new)
                              .toList();
        
        this.setores = empresa.getSetores()
                              .stream()
                              .map(SetorResponseDTO::new)
                              .toList();

    }


}