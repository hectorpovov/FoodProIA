package com.foodProIA.FoodProIA.service;

import com.foodProIA.FoodProIA.dto.request.InformacaoNutricionalMateriaPrimaRequestDTO;
import com.foodProIA.FoodProIA.dto.request.MateriaPrimaRequestDTO;
import com.foodProIA.FoodProIA.dto.response.MateriaPrimaResponseDTO;
import com.foodProIA.FoodProIA.entity.InformacaoNutricionalEntity;
import com.foodProIA.FoodProIA.entity.InformacaoNutricionalMateriaPrimaEntity;
import com.foodProIA.FoodProIA.entity.MateriaPrimaEntity;
import com.foodProIA.FoodProIA.repository.InformacaoNutricionalRepository;
import com.foodProIA.FoodProIA.repository.MateriaPrimaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MateriaPrimaService {

    private final MateriaPrimaRepository materiaPrimaRepository;

    private final InformacaoNutricionalRepository informacaoNutricionalRepository;


    // =========================
    // CRIAR
    // =========================

    @Transactional
    public MateriaPrimaResponseDTO criar(
            MateriaPrimaRequestDTO dto) {

        MateriaPrimaEntity materiaPrima = new MateriaPrimaEntity();

        materiaPrima.setNome(dto.getNome());
        materiaPrima.setCategoria(dto.getCategoria());
        materiaPrima.setUnidadeDeMedida(dto.getUnidadeDeMedida());
        materiaPrima.setDescricao(dto.getDescricao());
        materiaPrima.setAnosValidade(dto.getAnosValidade());
        materiaPrima.setMesesValidade(dto.getMesesValidade());
        materiaPrima.setDiasValidade(dto.getDiasValidade());

        // Salva primeiro para gerar o ID
        materiaPrimaRepository.save(materiaPrima);

        // Cria as relações nutricionais
        criarRelacoesNutricionais(
                materiaPrima,
                dto.getInformacoesNutricionais()
        );

        // Retorna a matéria-prima com as relações
        return new MateriaPrimaResponseDTO(materiaPrima);
    }


    // =========================
    // ALTERAR
    // =========================

    @Transactional
    public MateriaPrimaResponseDTO alterar(
            Long id,
            MateriaPrimaRequestDTO dto) {

        MateriaPrimaEntity materiaPrima =
                materiaPrimaRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Matéria-prima não encontrada"
                                )
                        );

        materiaPrima.setNome(dto.getNome());
        materiaPrima.setCategoria(dto.getCategoria());
        materiaPrima.setUnidadeDeMedida(dto.getUnidadeDeMedida());
        materiaPrima.setDescricao(dto.getDescricao());
        materiaPrima.setAnosValidade(dto.getAnosValidade());
        materiaPrima.setMesesValidade(dto.getMesesValidade());
        materiaPrima.setDiasValidade(dto.getDiasValidade());

        // Sincroniza as relações nutricionais
        atualizarRelacoesNutricionais(
                materiaPrima,
                dto.getInformacoesNutricionais()
        );

        return new MateriaPrimaResponseDTO(materiaPrima);
    }


    // =========================
    // EXCLUIR
    // =========================

    @Transactional
    public void excluir(Long id) {

        MateriaPrimaEntity materiaPrima =
                materiaPrimaRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Matéria-prima não encontrada"
                                )
                        );

        materiaPrimaRepository.delete(materiaPrima);
    }


    // =========================
    // BUSCAR POR ID
    // =========================

    @Transactional(readOnly = true)
    public MateriaPrimaResponseDTO buscarPorId(Long id) {

        MateriaPrimaEntity materiaPrima =
                materiaPrimaRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Matéria-prima não encontrada"
                                )
                        );

        return new MateriaPrimaResponseDTO(materiaPrima);
    }


    // =========================
    // LISTAR TODOS
    // =========================

    @Transactional(readOnly = true)
    public List<MateriaPrimaResponseDTO> listarTodos() {

        return materiaPrimaRepository.findAll()
                .stream()
                .map(MateriaPrimaResponseDTO::new)
                .toList();
    }


    // =========================
    // MÉTODOS AUXILIARES
    // =========================

    private void criarRelacoesNutricionais(
            MateriaPrimaEntity materiaPrima,
            List<InformacaoNutricionalMateriaPrimaRequestDTO> dtos) {

        if (dtos == null) {
            return;
        }

        for (InformacaoNutricionalMateriaPrimaRequestDTO dto : dtos) {

            InformacaoNutricionalEntity informacaoNutricional =
                    informacaoNutricionalRepository.findById(
                            dto.getIdInformacaoNutricional()
                    ).orElseThrow(() ->
                            new EntityNotFoundException(
                                    "Informação nutricional não encontrada"
                            )
                    );

            new InformacaoNutricionalMateriaPrimaEntity(
                    dto.getValor(),
                    materiaPrima,
                    informacaoNutricional
            );
        }
    }


    private void atualizarRelacoesNutricionais(
            MateriaPrimaEntity materiaPrima,
            List<InformacaoNutricionalMateriaPrimaRequestDTO> dtos) {

        // Remove as relações antigas
        materiaPrima.getInformacoesNutricionais().clear();

        // Cria as novas relações
        criarRelacoesNutricionais(materiaPrima, dtos);
    }

}