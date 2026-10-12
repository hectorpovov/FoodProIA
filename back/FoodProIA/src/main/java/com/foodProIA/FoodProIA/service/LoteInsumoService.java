package com.foodProIA.FoodProIA.service;

import com.foodProIA.FoodProIA.dto.request.LoteInsumoRequestDTO;
import com.foodProIA.FoodProIA.dto.response.LoteInsumoResponseDTO;

import com.foodProIA.FoodProIA.entity.LoteInsumoEntity;
import com.foodProIA.FoodProIA.entity.FornecedorEntity;
import com.foodProIA.FoodProIA.entity.FuncionarioEntity;
import com.foodProIA.FoodProIA.entity.InsumoEntity;

import com.foodProIA.FoodProIA.repository.LoteInsumoRepository;
import com.foodProIA.FoodProIA.repository.FornecedorRepository;
import com.foodProIA.FoodProIA.repository.FuncionarioRepository;
import com.foodProIA.FoodProIA.repository.InsumoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoteInsumoService {

    private final LoteInsumoRepository loteInsumoRepository;

    private final FornecedorRepository fornecedorRepository;

    private final FuncionarioRepository funcionarioRepository;

    private final InsumoRepository insumoRepository;


    // =========================
    // CRIAR
    // =========================

    @Transactional
    public LoteInsumoResponseDTO criar(LoteInsumoRequestDTO dto) {

        LoteInsumoEntity lote = new LoteInsumoEntity();

        lote.setData(dto.getData());
        lote.setMarca(dto.getMarca());
        lote.setQuantidade(dto.getQuantidade());
        lote.setDataDeValidade(dto.getDataDeValidade());
        lote.setCodigoNFe(dto.getCodigoNFe());
        lote.setValorCompra(dto.getValorCompra());

        adicionarFornecedor(lote, dto.getIdFornecedor());

        adicionarFuncionario(lote, dto.getIdFuncionario());

        adicionarInsumo(lote, dto.getIdInsumo());

        loteInsumoRepository.save(lote);

        return new LoteInsumoResponseDTO(lote);
    }


    // =========================
    // ALTERAR
    // =========================

    @Transactional
    public LoteInsumoResponseDTO alterar(
            Long id,
            LoteInsumoRequestDTO dto) {

        LoteInsumoEntity lote =
                loteInsumoRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Lote de insumo não encontrado"
                                )
                        );

        lote.setData(dto.getData());
        lote.setMarca(dto.getMarca());
        lote.setQuantidade(dto.getQuantidade());
        lote.setDataDeValidade(dto.getDataDeValidade());
        lote.setCodigoNFe(dto.getCodigoNFe());
        lote.setValorCompra(dto.getValorCompra());

        adicionarFornecedor(lote, dto.getIdFornecedor());

        adicionarFuncionario(lote, dto.getIdFuncionario());

        adicionarInsumo(lote, dto.getIdInsumo());

        return new LoteInsumoResponseDTO(lote);
    }


    // =========================
    // EXCLUIR
    // =========================

    @Transactional
    public void excluir(Long id) {

        LoteInsumoEntity lote =
                loteInsumoRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Lote de insumo não encontrado"
                                )
                        );

        loteInsumoRepository.delete(lote);
    }


    // =========================
    // BUSCAR POR ID
    // =========================

    @Transactional(readOnly = true)
    public LoteInsumoResponseDTO buscarPorId(Long id) {

        LoteInsumoEntity lote =
                loteInsumoRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Lote de insumo não encontrado"
                                )
                        );

        return new LoteInsumoResponseDTO(lote);
    }


    // =========================
    // LISTAR TODOS
    // =========================

    @Transactional(readOnly = true)
    public List<LoteInsumoResponseDTO> listarTodos() {

        return loteInsumoRepository.findAll()
                .stream()
                .map(LoteInsumoResponseDTO::new)
                .toList();
    }


    // =========================
    // FORNECEDOR
    // =========================

    private void adicionarFornecedor(
            LoteInsumoEntity lote,
            Long idFornecedor) {

        FornecedorEntity fornecedor =
                fornecedorRepository.findById(idFornecedor)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Fornecedor não encontrado"
                                )
                        );

        lote.setFornecedor(fornecedor);
    }


    // =========================
    // FUNCIONÁRIO
    // =========================

    private void adicionarFuncionario(
            LoteInsumoEntity lote,
            Long idFuncionario) {

        FuncionarioEntity funcionario =
                funcionarioRepository.findById(idFuncionario)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Funcionário não encontrado"
                                )
                        );

        lote.setFuncionario(funcionario);
    }


    // =========================
    // INSUMO
    // =========================

    private void adicionarInsumo(
            LoteInsumoEntity lote,
            Long idInsumo) {

        InsumoEntity insumo =
                insumoRepository.findById(idInsumo)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Insumo não encontrado"
                                )
                        );

        lote.setInsumo(insumo);
    }

}