package com.foodProIA.FoodProIA.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodProIA.FoodProIA.dto.request.EmpresaRequestDTO;
import com.foodProIA.FoodProIA.dto.request.EnderecoRequestDTO;
import com.foodProIA.FoodProIA.dto.response.EmpresaResponseDTO;
import com.foodProIA.FoodProIA.dto.response.FuncionarioResponseDTO;
import com.foodProIA.FoodProIA.dto.response.SetorResponseDTO;
import com.foodProIA.FoodProIA.entity.EmpresaEntity;
import com.foodProIA.FoodProIA.entity.EnderecoEntity;
import com.foodProIA.FoodProIA.exception.CnpjJaCadastradoException;
import com.foodProIA.FoodProIA.exception.EmailEmpresaJaCadastradoException;
import com.foodProIA.FoodProIA.exception.EmpresaNaoEncontradaException;
import com.foodProIA.FoodProIA.exception.RazaoSocialJaCadastradaException;
import com.foodProIA.FoodProIA.repository.EmpresaRepository;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class EmpresaService {
    
    private final EmpresaRepository empresaRepository; 

    @Transactional(readOnly = true)
    public List<EmpresaResponseDTO> listarTodos(){
        List<EmpresaEntity> empresas = empresaRepository.findAll();
        return empresas.stream().map(EmpresaResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public List<FuncionarioResponseDTO> listarFuncionarios(Long empresaId){

        EmpresaEntity empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new EmpresaNaoEncontradaException());

        return empresa.getFuncionarios()
                .stream()
                .map(FuncionarioResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<SetorResponseDTO> listarSetores(Long empresaId){

        EmpresaEntity empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new EmpresaNaoEncontradaException());

        return empresa.getSetores()
                .stream()
                .map(SetorResponseDTO::new)
                .toList();
    }

    @Transactional
    public EmpresaResponseDTO inserir(EmpresaRequestDTO empresa){

        if(empresaRepository.existsByEmail(empresa.getEmail())){
            throw new EmailEmpresaJaCadastradoException();
        }

        if(empresaRepository.existsByCnpj(empresa.getCnpj())){
            throw new CnpjJaCadastradoException();
        }

        if(empresaRepository.existsByRazaoSocial(empresa.getRazaoSocial())){
            throw new RazaoSocialJaCadastradaException();
        }

        EmpresaEntity empresaEntity = new EmpresaEntity();

        copiarDados(empresa, empresaEntity);

        EnderecoEntity enderecoEntity = new EnderecoEntity();
        EnderecoRequestDTO enderecoRequestDTO = empresa.getEndereco();

        copiarEndereco(enderecoRequestDTO, enderecoEntity);

        empresaEntity.setEndereco(enderecoEntity);      

        empresaRepository.save(empresaEntity);

        return new EmpresaResponseDTO(empresaEntity);

    }

    @Transactional
    public EmpresaResponseDTO alterar(Long id, EmpresaRequestDTO empresa){

        EmpresaEntity empresaEntity = empresaRepository.findById(id).orElseThrow(() -> new EmpresaNaoEncontradaException());


        if (!empresaEntity.getEmail().equals(empresa.getEmail())
                && empresaRepository.existsByEmail(empresa.getEmail())) {

            throw new EmailEmpresaJaCadastradoException();
        }

        if (!empresaEntity.getCnpj().equals(empresa.getCnpj())
                && empresaRepository.existsByCnpj(empresa.getCnpj())) {

            throw new CnpjJaCadastradoException();
        }   

        if (!empresaEntity.getRazaoSocial().equals(empresa.getRazaoSocial())
                && empresaRepository.existsByRazaoSocial(empresa.getRazaoSocial())) {

            throw new RazaoSocialJaCadastradaException();
        }   

        copiarDados(empresa, empresaEntity);   

        if (empresa.getEndereco() != null) {
            EnderecoEntity enderecoEntity = empresaEntity.getEndereco();

            if(enderecoEntity == null){
                enderecoEntity = new EnderecoEntity();
                empresaEntity.setEndereco(enderecoEntity);
            }

            EnderecoRequestDTO enderecoRequestDTO = empresa.getEndereco();

            copiarEndereco(enderecoRequestDTO, enderecoEntity);

        }

        

        empresaRepository.save(empresaEntity);

        return new EmpresaResponseDTO(empresaEntity);
    }

    @Transactional
    public void excluir(Long id){
        EmpresaEntity empresa = empresaRepository.findById(id).orElseThrow(() -> new EmpresaNaoEncontradaException());
        empresaRepository.delete(empresa);
    }

    @Transactional(readOnly = true)
    public EmpresaResponseDTO buscarPorId(Long id){
        EmpresaEntity empresa = empresaRepository.findById(id).orElseThrow(() -> new EmpresaNaoEncontradaException());
        return new EmpresaResponseDTO(empresa);
    }

    private void copiarDados(EmpresaRequestDTO empresaRequest, EmpresaEntity empresaEntity){
        empresaEntity.setCnpj(empresaRequest.getCnpj());
        empresaEntity.setEmail(empresaRequest.getEmail());
        empresaEntity.setNomeFantasia(empresaRequest.getNomeFantasia());
        empresaEntity.setObservacoes(empresaRequest.getObservacoes());
        empresaEntity.setRazaoSocial(empresaRequest.getRazaoSocial());
        empresaEntity.setTelefone(empresaRequest.getTelefone());
        
    }

    private void copiarEndereco(EnderecoRequestDTO enderecoRequestDTO, EnderecoEntity enderecoEntity){
        enderecoEntity.setBairro(enderecoRequestDTO.getBairro());
        enderecoEntity.setCep(enderecoRequestDTO.getCep());
        enderecoEntity.setCidade(enderecoRequestDTO.getCidade());
        enderecoEntity.setComplemento(enderecoRequestDTO.getComplemento());
        enderecoEntity.setEstado(enderecoRequestDTO.getEstado());
        enderecoEntity.setNumero(enderecoRequestDTO.getNumero());
        enderecoEntity.setPais(enderecoRequestDTO.getPais());
        enderecoEntity.setRua(enderecoRequestDTO.getRua());
    }
}
