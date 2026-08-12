package com.foodProIA.FoodProIA.dto.response;

import java.util.List;

import com.foodProIA.FoodProIA.entity.FornecedorEntity;
import com.foodProIA.FoodProIA.enums.TipoInsumo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FornecedorResponseDTO {

    private Long id;
    private String nome;
    private String registroEmpresa;
    private String email;
    private TipoInsumo tipoInsumo;
    private boolean status;
    private List<FraudeResponseDTO> fraudes;
    private EnderecoResponseDTO endereco;
    
    public FornecedorResponseDTO(FornecedorEntity fornecedor){
        this.id = fornecedor.getId();
        this.nome = fornecedor.getNome();
        this.registroEmpresa = fornecedor.getRegistroEmpresa();
        this.email = fornecedor.getEmail();
        this.tipoInsumo = fornecedor.getTipoInsumo();
        this.status = fornecedor.isStatus();
        this.fraudes = fornecedor.getFraudes()
                                .stream()
                                .map(FraudeResponseDTO::new)
                                .toList();
        this.endereco = new EnderecoResponseDTO(fornecedor.getEndereco());
    }

}
