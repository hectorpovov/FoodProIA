package com.foodProIA.FoodProIA.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodProIA.FoodProIA.dto.request.ProgramacaoRotaRequestDTO;
import com.foodProIA.FoodProIA.dto.request.RotaRequestDTO;
import com.foodProIA.FoodProIA.dto.response.ProgramacaoRotaResponseDTO;
import com.foodProIA.FoodProIA.dto.response.RotaResponseDTO;
import com.foodProIA.FoodProIA.entity.FornecedorEntity;
import com.foodProIA.FoodProIA.entity.ProgramacaoRotaEntity;
import com.foodProIA.FoodProIA.entity.ProgramacaoRotaEntity.StatusProgramacaoRota;
import com.foodProIA.FoodProIA.entity.RotaEntity;
import com.foodProIA.FoodProIA.repository.FornecedorRepository;
import com.foodProIA.FoodProIA.repository.ProgramacaoRotaRepository;
import com.foodProIA.FoodProIA.repository.RotaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RotaService {

    private final RotaRepository rotaRepository;
    private final FornecedorRepository fornecedorRepository;
    private final ProgramacaoRotaRepository programacaoRotaRepository;


    // =========================================================
    // INSERIR ROTA
    // =========================================================

    @Transactional
    public RotaResponseDTO inserir(RotaRequestDTO dto) {

        FornecedorEntity fornecedor =
                fornecedorRepository.findById(dto.getIdFornecedor())
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Fornecedor não encontrado: "
                                        + dto.getIdFornecedor()
                                )
                        );

        RotaEntity rota = new RotaEntity();

        rota.setNome(dto.getNome());
        rota.setCidade(dto.getCidade());
        rota.setCodigo(dto.getCodigo());
        rota.setFornecedor(fornecedor);
        rota.setCodigoDeRodovia(dto.getCodigoDeRodovia());
        rota.setQuantidade(dto.getQuantidade());
        rota.setPrioridade(dto.getPrioridade());


        rota = rotaRepository.save(rota);

        return new RotaResponseDTO(rota);
    }


    // =========================================================
    // ALTERAR ROTA
    // =========================================================

    @Transactional
    public RotaResponseDTO alterar(
            Long id,
            RotaRequestDTO dto) {

        RotaEntity rota =
                rotaRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Rota não encontrada: " + id
                                )
                        );

        FornecedorEntity fornecedor =
                fornecedorRepository.findById(dto.getIdFornecedor())
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Fornecedor não encontrado: "
                                        + dto.getIdFornecedor()
                                )
                        );

        rota.setNome(dto.getNome());
        rota.setCidade(dto.getCidade());
        rota.setCodigo(dto.getCodigo());
        rota.setFornecedor(fornecedor);
        rota.setCodigoDeRodovia(dto.getCodigoDeRodovia());
        rota.setQuantidade(dto.getQuantidade());
        rota.setPrioridade(dto.getPrioridade());

        rota = rotaRepository.save(rota);

        return new RotaResponseDTO(rota);
    }


    // =========================================================
    // EXCLUIR ROTA
    // =========================================================

    @Transactional
    public void excluir(Long id) {

        RotaEntity rota =
                rotaRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Rota não encontrada: " + id
                                )
                        );

        /*
         * As programações serão excluídas automaticamente
         * pelo cascade = CascadeType.ALL e orphanRemoval = true.
         */
        rotaRepository.delete(rota);
    }


    // =========================================================
    // BUSCAR POR ID
    // =========================================================

    @Transactional(readOnly = true)
    public RotaResponseDTO buscarPorId(Long id) {

        RotaEntity rota =
                rotaRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Rota não encontrada: " + id
                                )
                        );

        return new RotaResponseDTO(rota);
    }


    // =========================================================
    // PROGRAMAR ROTA
    // =========================================================

    @Transactional
    public ProgramacaoRotaResponseDTO programarRota(
            ProgramacaoRotaRequestDTO dto) {

        RotaEntity rota =
                rotaRepository.findById(dto.getIdRota())
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Rota não encontrada: "
                                        + dto.getIdRota()
                                )
                        );

        ProgramacaoRotaEntity programacao =
                new ProgramacaoRotaEntity();

        programacao.setMotoristaResponsavel(
                dto.getMotoristaResponsavel()
        );

        programacao.setRota(rota);

        programacao.setQuilometragem(
                dto.getQuilometragem()
        );

        programacao.setVolumeEstimado(
                dto.getVolumeEstimado()
        );

        programacao.setCapacidadeDoCaminhao(
                dto.getCapacidadeDoCaminhao()
        );

        programacao.setPlacaVeiculo(
                dto.getPlacaVeiculo()
        );

        programacao.setHorarioSaida(
                dto.getHorarioSaida()
        );

        programacao.setCodigoRastreamentoMercadoria(
                dto.getCodigoRastreamentoMercadoria()
        );

        /*
         * Toda programação nova começa como pendente.
         */
        programacao.setStatus(
                StatusProgramacaoRota.PENDENTE
        );

        rota.adicionaProgramacao(programacao);

        programacao =
                programacaoRotaRepository.save(programacao);

        return new ProgramacaoRotaResponseDTO(programacao);
    }


    // =========================================================
    // REMOVER PROGRAMAÇÃO
    // =========================================================

    @Transactional
    public void removerProgramacao(
            Long idRota,
            Long idProgramacao) {

        RotaEntity rota =
                rotaRepository.findById(idRota)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Rota não encontrada: "
                                        + idRota
                                )
                        );

        ProgramacaoRotaEntity programacao =
                programacaoRotaRepository.findById(idProgramacao)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Programação não encontrada: "
                                        + idProgramacao
                                )
                        );

        /*
         * Garante que a programação realmente pertence
         * à rota informada.
         */
        if (!rota.equals(programacao.getRota())) {
            throw new IllegalArgumentException(
                    "A programação não pertence à rota informada."
            );
        }

        rota.removeProgramacao(programacao);

        /*
         * Como a programação foi removida da coleção e
         * orphanRemoval = true, ela será excluída.
         */
        programacaoRotaRepository.delete(programacao);
    }


    // =========================================================
    // ALTERAR PROGRAMAÇÃO
    // =========================================================

    @Transactional
    public ProgramacaoRotaResponseDTO alterarProgramacao(
            Long idRota,
            Long idProgramacao,
            ProgramacaoRotaRequestDTO dto) {

        RotaEntity rota =
                rotaRepository.findById(idRota)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Rota não encontrada: "
                                        + idRota
                                )
                        );

        ProgramacaoRotaEntity programacao =
                programacaoRotaRepository.findById(idProgramacao)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Programação não encontrada: "
                                        + idProgramacao
                                )
                        );

        /*
         * Garante que a programação pertence à rota
         * que foi informada na URL.
         */
        if (!rota.equals(programacao.getRota())) {
            throw new IllegalArgumentException(
                    "A programação não pertence à rota informada."
            );
        }

        /*
         * Garante também que o idRota enviado no DTO
         * corresponde à rota da URL.
         */
        if (!rota.getId().equals(dto.getIdRota())) {
            throw new IllegalArgumentException(
                    "O id da rota informado no DTO não corresponde "
                    + "à rota da programação."
            );
        }

        programacao.setMotoristaResponsavel(
                dto.getMotoristaResponsavel()
        );

        programacao.setQuilometragem(
                dto.getQuilometragem()
        );

        programacao.setVolumeEstimado(
                dto.getVolumeEstimado()
        );

        programacao.setCapacidadeDoCaminhao(
                dto.getCapacidadeDoCaminhao()
        );

        programacao.setPlacaVeiculo(
                dto.getPlacaVeiculo()
        );

        programacao.setHorarioSaida(
                dto.getHorarioSaida()
        );

        programacao.setCodigoRastreamentoMercadoria(
                dto.getCodigoRastreamentoMercadoria()
        );

        /*
         * O status não é alterado pelo DTO.
         * Assim, se estava EM_ROTA, continua EM_ROTA.
         */

        programacao =
                programacaoRotaRepository.save(programacao);

        return new ProgramacaoRotaResponseDTO(programacao);
    }


}
