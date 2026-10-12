package com.foodProIA.FoodProIA.service;

import com.foodProIA.FoodProIA.dto.request.LoteMateriaPrimaRequestDTO;
import com.foodProIA.FoodProIA.dto.response.LoteMateriaPrimaResponseDTO;

import com.foodProIA.FoodProIA.entity.LoteMateriaPrimaEntity;
import com.foodProIA.FoodProIA.entity.MateriaPrimaEntity;
import com.foodProIA.FoodProIA.entity.FornecedorEntity;
import com.foodProIA.FoodProIA.entity.FuncionarioEntity;

import com.foodProIA.FoodProIA.repository.LoteMateriaPrimaRepository;
import com.foodProIA.FoodProIA.repository.MateriaPrimaRepository;
import com.foodProIA.FoodProIA.repository.FornecedorRepository;
import com.foodProIA.FoodProIA.repository.FuncionarioRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoteMateriaPrimaService {

    private final LoteMateriaPrimaRepository loteMateriaPrimaRepository;

    private final MateriaPrimaRepository materiaPrimaRepository;

    private final FornecedorRepository fornecedorRepository;

    private final FuncionarioRepository funcionarioRepository;


    // =========================
    // CRIAR
    // =========================

    @Transactional
    public LoteMateriaPrimaResponseDTO criar(
            LoteMateriaPrimaRequestDTO dto) {

        LoteMateriaPrimaEntity lote = new LoteMateriaPrimaEntity();

        lote.setData(dto.getData());
        lote.setMarca(dto.getMarca());
        lote.setQuantidade(dto.getQuantidade());
        lote.setCodigoNFe(dto.getCodigoNFe());
        lote.setValorCompra(dto.getValorCompra());
        lote.setDataValidade(dto.getDataValidade());

        adicionarMateriaPrima(lote, dto.getIdMateriaPrima());

        adicionarFornecedor(lote, dto.getIdFornecedor());

        adicionarRecebedor(lote, dto.getIdRecebedor());

        loteMateriaPrimaRepository.save(lote);

        return new LoteMateriaPrimaResponseDTO(lote);
    }


    // =========================
    // ALTERAR
    // =========================

    @Transactional
    public LoteMateriaPrimaResponseDTO alterar(
            Long id,
            LoteMateriaPrimaRequestDTO dto) {

        LoteMateriaPrimaEntity lote =
                loteMateriaPrimaRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Lote de matéria-prima não encontrado"
                                )
                        );

        lote.setData(dto.getData());
        lote.setMarca(dto.getMarca());
        lote.setQuantidade(dto.getQuantidade());
        lote.setCodigoNFe(dto.getCodigoNFe());
        lote.setValorCompra(dto.getValorCompra());
        lote.setDataValidade(dto.getDataValidade());

        adicionarMateriaPrima(lote, dto.getIdMateriaPrima());

        adicionarFornecedor(lote, dto.getIdFornecedor());

        adicionarRecebedor(lote, dto.getIdRecebedor());

        return new LoteMateriaPrimaResponseDTO(lote);
    }


    // =========================
    // EXCLUIR
    // =========================

    @Transactional
    public void excluir(Long id) {

        LoteMateriaPrimaEntity lote =
                loteMateriaPrimaRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Lote de matéria-prima não encontrado"
                                )
                        );

        loteMateriaPrimaRepository.delete(lote);
    }


    // =========================
    // BUSCAR POR ID
    // =========================

    @Transactional(readOnly = true)
    public LoteMateriaPrimaResponseDTO buscarPorId(Long id) {

        LoteMateriaPrimaEntity lote =
                loteMateriaPrimaRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Lote de matéria-prima não encontrado"
                                )
                        );

        return new LoteMateriaPrimaResponseDTO(lote);
    }


    // =========================
    // LISTAR TODOS
    // =========================

    @Transactional(readOnly = true)
    public List<LoteMateriaPrimaResponseDTO> listarTodos() {

        return loteMateriaPrimaRepository.findAll()
                .stream()
                .map(LoteMateriaPrimaResponseDTO::new)
                .toList();
    }


    // =========================
    // MATÉRIA-PRIMA
    // =========================

    private void adicionarMateriaPrima(
            LoteMateriaPrimaEntity lote,
            Long idMateriaPrima) {

        MateriaPrimaEntity materiaPrima =
                materiaPrimaRepository.findById(idMateriaPrima)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Matéria-prima não encontrada"
                                )
                        );

        lote.setMateriaPrima(materiaPrima);
    }


    // =========================
    // FORNECEDOR
    // =========================

    private void adicionarFornecedor(
            LoteMateriaPrimaEntity lote,
            Long idFornecedor) {

        if (idFornecedor == null) {
            lote.setFornecedor(null);
            return;
        }

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
    // RECEBEDOR
    // =========================

    private void adicionarRecebedor(
            LoteMateriaPrimaEntity lote,
            Long idRecebedor) {

        if (idRecebedor == null) {
            lote.setRecebedor(null);
            return;
        }

        FuncionarioEntity recebedor =
                funcionarioRepository.findById(idRecebedor)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Funcionário recebedor não encontrado"
                                )
                        );

        lote.setRecebedor(recebedor);
    }

}