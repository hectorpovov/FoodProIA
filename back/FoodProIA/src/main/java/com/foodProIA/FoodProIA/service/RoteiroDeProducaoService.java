package com.foodProIA.FoodProIA.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodProIA.FoodProIA.dto.request.InsumoRoteiroRequestDTO;
import com.foodProIA.FoodProIA.dto.request.RoteiroDeProducaoRequestDTO;
import com.foodProIA.FoodProIA.dto.response.RoteiroDeProducaoResponseDTO;
import com.foodProIA.FoodProIA.entity.InsumoEntity;
import com.foodProIA.FoodProIA.entity.InsumoRoteiroEntity;
import com.foodProIA.FoodProIA.entity.RoteiroDeProducaoEntity;
import com.foodProIA.FoodProIA.repository.InsumoRepository;
import com.foodProIA.FoodProIA.repository.RoteiroDeProducaoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoteiroDeProducaoService {

    private final RoteiroDeProducaoRepository roteiroRepository;
    private final InsumoRepository insumoRepository;


    // =========================================================
    // INSERIR ROTEIRO
    // =========================================================

    @Transactional
    public RoteiroDeProducaoResponseDTO inserir(
            RoteiroDeProducaoRequestDTO dto) {

        RoteiroDeProducaoEntity roteiro =
                new RoteiroDeProducaoEntity();

        roteiro.setNome(dto.getNome());

        /*
         * Um roteiro novo começa desativado.
         */
        roteiro.setStatus(dto.isStatus());

        /*
         * Adiciona todos os insumos enviados no momento
         * da criação do roteiro.
         */
        if (dto.getInsumos() != null) {

            for (InsumoRoteiroRequestDTO insumoDTO
                    : dto.getInsumos()) {

                adicionarInsumoNaEntidade(
                        roteiro,
                        insumoDTO
                );
            }
        }

        roteiro =
                roteiroRepository.save(roteiro);

        return new RoteiroDeProducaoResponseDTO(roteiro);
    }


    // =========================================================
    // ALTERAR ROTEIRO
    // =========================================================

    @Transactional
    public RoteiroDeProducaoResponseDTO alterar(
            Long id,
            RoteiroDeProducaoRequestDTO dto) {

        RoteiroDeProducaoEntity roteiro =
                roteiroRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Roteiro de produção não encontrado: "
                                        + id
                                )
                        );

        roteiro.setNome(dto.getNome());
        roteiro.setStatus(dto.isStatus());
    
        roteiro =
                roteiroRepository.save(roteiro);

        return new RoteiroDeProducaoResponseDTO(roteiro);
    }


    // =========================================================
    // EXCLUIR ROTEIRO
    // =========================================================

    @Transactional
    public void excluir(Long id) {

        RoteiroDeProducaoEntity roteiro =
                roteiroRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Roteiro de produção não encontrado: "
                                        + id
                                )
                        );

        /*
         * Os InsumoRoteiroEntity serão excluídos automaticamente
         * pelo cascade + orphanRemoval da entidade Roteiro.
         */
        roteiroRepository.delete(roteiro);
    }


    // =========================================================
    // BUSCAR POR ID
    // =========================================================

    @Transactional(readOnly = true)
    public RoteiroDeProducaoResponseDTO buscarPorId(Long id) {

        RoteiroDeProducaoEntity roteiro =
                roteiroRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Roteiro de produção não encontrado: "
                                        + id
                                )
                        );

        return new RoteiroDeProducaoResponseDTO(roteiro);
    }


    // =========================================================
    // ADICIONAR INSUMO
    // =========================================================

    @Transactional
    public RoteiroDeProducaoResponseDTO adicionarInsumo(
            Long roteiroId,
            InsumoRoteiroRequestDTO dto) {

        RoteiroDeProducaoEntity roteiro =
                buscarRoteiro(roteiroId);

        /*
         * Verifica se o insumo existe.
         */
        InsumoEntity insumo =
                buscarInsumo(dto.getInsumoId());

        /*
         * Não permite adicionar o mesmo insumo duas vezes
         * ao mesmo roteiro.
         */
        boolean jaExiste =
                roteiro.getInsumos()
                        .stream()
                        .anyMatch(ir ->
                                ir.getInsumo()
                                        .getId()
                                        .equals(dto.getInsumoId())
                        );

        if (jaExiste) {
            throw new IllegalArgumentException(
                    "O insumo já está associado a este roteiro."
            );
        }

        InsumoRoteiroEntity insumoRoteiro =
                new InsumoRoteiroEntity();

        insumoRoteiro.setInsumo(insumo);
        insumoRoteiro.setRoteiro(roteiro);
        insumoRoteiro.setQuantidade(dto.getQuantidade());
        insumoRoteiro.setUnidadeDeMedida(
                dto.getUnidadeDeMedida()
        );

        /*
         * Como o relacionamento é responsabilidade do
         * RoteiroService, adicionamos manualmente a associação
         * nos dois lados.
         */
        roteiro.getInsumos().add(insumoRoteiro);
        insumo.getRoteiros().add(insumoRoteiro);

        /*
         * O cascade do roteiro fará a persistência da
         * associação quando o roteiro for salvo.
         */
        roteiro =
                roteiroRepository.save(roteiro);

        return new RoteiroDeProducaoResponseDTO(roteiro);
    }


    // =========================================================
    // REMOVER INSUMO
    // =========================================================

    @Transactional
    public RoteiroDeProducaoResponseDTO removerInsumo(
            Long roteiroId,
            Long insumoId) {

        RoteiroDeProducaoEntity roteiro =
                buscarRoteiro(roteiroId);

        InsumoRoteiroEntity insumoRoteiro =
                roteiro.getInsumos()
                        .stream()
                        .filter(ir ->
                                ir.getInsumo()
                                        .getId()
                                        .equals(insumoId)
                        )
                        .findFirst()
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "O insumo não está associado "
                                        + "a este roteiro."
                                )
                        );

        /*
         * Remove dos dois lados do relacionamento.
         */
        roteiro.getInsumos()
                .remove(insumoRoteiro);

        insumoRoteiro.getInsumo()
                .getRoteiros()
                .remove(insumoRoteiro);

        /*
         * orphanRemoval = true no Roteiro faz com que a
         * entidade InsumoRoteiro seja excluída do banco.
         */
        roteiro =
                roteiroRepository.save(roteiro);

        return new RoteiroDeProducaoResponseDTO(roteiro);
    }


    // =========================================================
    // ALTERAR INSUMO DO ROTEIRO
    // =========================================================

    @Transactional
    public RoteiroDeProducaoResponseDTO alterarInsumo(
            Long roteiroId,
            Long insumoId,
            InsumoRoteiroRequestDTO dto) {

        RoteiroDeProducaoEntity roteiro =
                buscarRoteiro(roteiroId);

        /*
         * Garante que o insumo enviado no DTO é o mesmo
         * que está sendo alterado.
         */
        if (!insumoId.equals(dto.getInsumoId())) {
            throw new IllegalArgumentException(
                    "O ID do insumo da URL não corresponde "
                    + "ao ID informado no DTO."
            );
        }

        InsumoRoteiroEntity insumoRoteiro =
                roteiro.getInsumos()
                        .stream()
                        .filter(ir ->
                                ir.getInsumo()
                                        .getId()
                                        .equals(insumoId)
                        )
                        .findFirst()
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "O insumo não está associado "
                                        + "a este roteiro."
                                )
                        );

        /*
         * O insumo em si não muda.
         * Alteramos apenas os atributos da associação.
         */
        insumoRoteiro.setQuantidade(
                dto.getQuantidade()
        );

        insumoRoteiro.setUnidadeDeMedida(
                dto.getUnidadeDeMedida()
        );

        roteiro =
                roteiroRepository.save(roteiro);

        return new RoteiroDeProducaoResponseDTO(roteiro);
    }


    // =========================================================
    // MÉTODOS AUXILIARES
    // =========================================================

    private RoteiroDeProducaoEntity buscarRoteiro(
            Long id) {

        return roteiroRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Roteiro de produção não encontrado: "
                                + id
                        )
                );
    }


    private InsumoEntity buscarInsumo(
            Long id) {

        return insumoRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Insumo não encontrado: " + id
                        )
                );
    }


    private void adicionarInsumoNaEntidade(
            RoteiroDeProducaoEntity roteiro,
            InsumoRoteiroRequestDTO dto) {

        InsumoEntity insumo =
                buscarInsumo(dto.getInsumoId());

        /*
         * Impede o mesmo insumo de aparecer duas vezes
         * durante a criação do roteiro.
         */
        boolean jaExiste =
                roteiro.getInsumos()
                        .stream()
                        .anyMatch(ir ->
                                ir.getInsumo()
                                        .getId()
                                        .equals(dto.getInsumoId())
                        );

        if (jaExiste) {
            throw new IllegalArgumentException(
                    "O insumo "
                    + dto.getInsumoId()
                    + " foi informado mais de uma vez no roteiro."
            );
        }

        InsumoRoteiroEntity insumoRoteiro =
                new InsumoRoteiroEntity();

        insumoRoteiro.setInsumo(insumo);
        insumoRoteiro.setRoteiro(roteiro);
        insumoRoteiro.setQuantidade(
                dto.getQuantidade()
        );
        insumoRoteiro.setUnidadeDeMedida(
                dto.getUnidadeDeMedida()
        );

        roteiro.getInsumos()
                .add(insumoRoteiro);

        insumo.getRoteiros()
                .add(insumoRoteiro);
    }
}
