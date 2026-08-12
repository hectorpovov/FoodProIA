package com.foodProIA.FoodProIA.dto.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.foodProIA.FoodProIA.enums.TipoInsumo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FornecedorRequestDTO {

    @NotBlank
    private String nome;
    @NotBlank
    private String registroEmpresa;
    @NotBlank
    private String telefone;
    @NotBlank
    @Email
    private String email;
    @NotNull
    private TipoInsumo tipoInsumo;
    private MultipartFile certidaoDeNegativas;
    private MultipartFile licensasDeFuncionamento;
    private List<MultipartFile> certificacoesDeQualidade;
    @NotNull
    private EnderecoRequestDTO endereco;
}
