package com.foodProIA.FoodProIA.dto.response;

import com.foodProIA.FoodProIA.entity.EnderecoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnderecoResponseDTO {

    private Long id;

    private String complemento;

    private String numero;

    private String rua;

    private String bairro;

    private String cidade;

    private String estado;

    private String pais;

    private String cep;

    public EnderecoResponseDTO( EnderecoEntity endereco){

        this.id = endereco.getId();
        this.bairro = endereco.getBairro();
        this.cep = endereco.getCep();
        this.complemento = endereco.getComplemento();
        this.cidade = endereco.getCidade();
        this.estado = endereco.getEstado();
        this.numero = endereco.getNumero();
        this.pais = endereco.getPais();
        this.rua = endereco.getRua();

    }
}
