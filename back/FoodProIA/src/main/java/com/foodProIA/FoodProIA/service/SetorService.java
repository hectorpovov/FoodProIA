package com.foodProIA.FoodProIA.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodProIA.FoodProIA.dto.request.SetorCreateRequestDTO;
import com.foodProIA.FoodProIA.dto.request.SetorUpdateRequestDTO;
import com.foodProIA.FoodProIA.dto.response.FuncionarioResponseDTO;
import com.foodProIA.FoodProIA.dto.response.SetorResponseDTO;
import com.foodProIA.FoodProIA.entity.EmpresaEntity;
import com.foodProIA.FoodProIA.entity.SetorEntity;
import com.foodProIA.FoodProIA.exception.EmpresaNaoEncontradaException;
import com.foodProIA.FoodProIA.exception.SetorJaExisteException;
import com.foodProIA.FoodProIA.exception.SetorNaoEncontradoException;
import com.foodProIA.FoodProIA.repository.EmpresaRepository;
import com.foodProIA.FoodProIA.repository.SetorRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SetorService {

    private final SetorRepository setorRepository;
    private final EmpresaRepository empresaRepository;

    @Transactional(readOnly = true)
    public List<FuncionarioResponseDTO> listarFuncionarios(Long setorId){
        SetorEntity setor = setorRepository.findById(setorId)
                    .orElseThrow(() -> new SetorNaoEncontradoException());
        return setor.getFuncionarios()
                .stream()
                .map(FuncionarioResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<SetorResponseDTO> listarTodos(){
        List<SetorEntity> setores = setorRepository.findAll();
        return setores.stream().map(SetorResponseDTO::new).toList();
    }

    @Transactional
    public SetorResponseDTO inserir(SetorCreateRequestDTO setor){
        

        if(setorRepository.existsByNomeAndEmpresaId(setor.getNome(), setor.getIdEmpresa())){
            throw new SetorJaExisteException();
        }
        
        SetorEntity setorEntity = new SetorEntity();

        EmpresaEntity empresa = empresaRepository.findById(setor.getIdEmpresa())
                                                .orElseThrow(() -> new EmpresaNaoEncontradaException());
        empresa.adicionaSetor(setorEntity);

        setorEntity.setDescricao(setor.getDescricao());
        setorEntity.setNome(setor.getNome());

        setorRepository.save(setorEntity);

        return new SetorResponseDTO(setorEntity);

    }

    @Transactional
    public SetorResponseDTO alterar(Long id, SetorUpdateRequestDTO setor){
        SetorEntity setorEntity = setorRepository.findById(id).orElseThrow(() -> new SetorNaoEncontradoException());
        

        if (!setorEntity.getNome().equals(setor.getNome())
                && setorRepository.existsByNomeAndEmpresaId(
                        setor.getNome(), setorEntity.getEmpresa().getId())) {

            throw new SetorJaExisteException();
        }

        setorEntity.setDescricao(setor.getDescricao());
        setorEntity.setNome(setor.getNome());

        setorRepository.save(setorEntity);

        return new SetorResponseDTO(setorEntity);

    }

    @Transactional
    public void excluir(Long id){
        SetorEntity setorEntity = setorRepository.findById(id).orElseThrow(() -> new SetorNaoEncontradoException() );
        setorRepository.delete(setorEntity);
    }

    @Transactional(readOnly = true)
    public SetorResponseDTO buscarPorId(Long id){
        SetorEntity setorEntity = setorRepository.findById(id).orElseThrow(() -> new SetorNaoEncontradoException() );
        return new SetorResponseDTO(setorEntity);
    }

}
