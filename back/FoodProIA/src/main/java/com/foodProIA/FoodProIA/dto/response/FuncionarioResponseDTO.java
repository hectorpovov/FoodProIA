package com.foodProIA.FoodProIA.dto.response;

import java.math.BigDecimal;

import com.foodProIA.FoodProIA.dto.summary.EmpresaSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.SetorSummaryDTO;
import com.foodProIA.FoodProIA.entity.FuncionarioEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FuncionarioResponseDTO extends UsuarioResponseDTO{



    private String cargo;

    private BigDecimal custoPorHora;

    private SetorSummaryDTO setor;
    private EmpresaSummaryDTO empresa;

    public FuncionarioResponseDTO(FuncionarioEntity funcionario){

        setAtivo(funcionario.isAtivo()); 
        setCpf(funcionario.getCpf());
        setDataNascimento(funcionario.getDataNascimento());
        setEmail(funcionario.getEmail());
        setId(funcionario.getId());
        setNome(funcionario.getNome());
        setTelefone(funcionario.getTelefone());
        setPapel(funcionario.getPapel());

        this.cargo = funcionario.getCargo();
        this.custoPorHora = funcionario.getCustoPorHora();
        this.setor = new SetorSummaryDTO(funcionario.getSetor());
        this.empresa = new EmpresaSummaryDTO(funcionario.getEmpresa());
    }


}
