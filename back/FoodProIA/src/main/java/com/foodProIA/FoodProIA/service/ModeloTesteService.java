package com.foodProIA.FoodProIA.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodProIA.FoodProIA.dto.request.ModeloTesteRequestDTO;
import com.foodProIA.FoodProIA.dto.request.TipoParametroBooleanoRequestDTO;
import com.foodProIA.FoodProIA.dto.request.TipoParametroNumericoRequestDTO;
import com.foodProIA.FoodProIA.dto.request.TipoParametroRequestDTO;
import com.foodProIA.FoodProIA.dto.request.TipoParametroTextualRequestDTO;
import com.foodProIA.FoodProIA.dto.response.ModeloTesteResponseDTO;
import com.foodProIA.FoodProIA.entity.ModeloTesteEntity;
import com.foodProIA.FoodProIA.entity.TipoParametroBooleanoEntity;
import com.foodProIA.FoodProIA.entity.TipoParametroEntity;
import com.foodProIA.FoodProIA.entity.TipoParametroNumericoEntity;
import com.foodProIA.FoodProIA.entity.TipoParametroTextualEntity;
import com.foodProIA.FoodProIA.repository.ModeloTesteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModeloTesteService {

    private final ModeloTesteRepository modeloTesteRepository;


    // =========================================================
    // INSERIR
    // =========================================================

    @Transactional
    public ModeloTesteResponseDTO inserir(ModeloTesteRequestDTO dto) {

        ModeloTesteEntity modeloTeste = new ModeloTesteEntity();

        modeloTeste.setNome(dto.getNome());

        if (dto.getParametros() != null) {

            for (TipoParametroRequestDTO parametroDTO : dto.getParametros()) {

                TipoParametroEntity parametro =
                        criarParametro(parametroDTO);

                modeloTeste.adicionaParametro(parametro);
            }
        }

        ModeloTesteEntity salvo =
                modeloTesteRepository.save(modeloTeste);

        return new ModeloTesteResponseDTO(salvo);
    }


    // =========================================================
    // ALTERAR
    // =========================================================

    @Transactional
    public ModeloTesteResponseDTO alterar(
            Long id,
            ModeloTesteRequestDTO dto) {

        ModeloTesteEntity modeloTeste = buscarEntidade(id);

        modeloTeste.setNome(dto.getNome());

        /*
         * Como TipoParametro é dependente de ModeloTeste,
         * os parâmetros antigos são removidos e novos são
         * criados a partir do DTO.
         *
         * orphanRemoval = true faz com que os parâmetros
         * antigos sejam excluídos do banco.
         */

        List<TipoParametroEntity> parametrosAtuais =
                new ArrayList<>(modeloTeste.getParametros());

        for (TipoParametroEntity parametro : parametrosAtuais) {
            modeloTeste.removeParametro(parametro);
        }

        if (dto.getParametros() != null) {

            for (TipoParametroRequestDTO parametroDTO : dto.getParametros()) {

                TipoParametroEntity parametro =
                        criarParametro(parametroDTO);

                modeloTeste.adicionaParametro(parametro);
            }
        }

        ModeloTesteEntity salvo =
                modeloTesteRepository.save(modeloTeste);

        return new ModeloTesteResponseDTO(salvo);
    }


    // =========================================================
    // EXCLUIR
    // =========================================================

    @Transactional
    public void excluir(Long id) {

        ModeloTesteEntity modeloTeste = buscarEntidade(id);

        modeloTesteRepository.delete(modeloTeste);
    }


    // =========================================================
    // BUSCAR POR ID
    // =========================================================

    @Transactional(readOnly = true)
    public ModeloTesteResponseDTO buscarPorId(Long id) {

        ModeloTesteEntity modeloTeste = buscarEntidade(id);

        return new ModeloTesteResponseDTO(modeloTeste);
    }


    // =========================================================
    // ADICIONAR PARÂMETRO
    // =========================================================

    @Transactional
    public ModeloTesteResponseDTO adicionarParametro(
            Long modeloTesteId,
            TipoParametroRequestDTO parametroDTO) {

        ModeloTesteEntity modeloTeste =
                buscarEntidade(modeloTesteId);

        TipoParametroEntity parametro =
                criarParametro(parametroDTO);

        modeloTeste.adicionaParametro(parametro);

        return new ModeloTesteResponseDTO(modeloTeste);
    }


    // =========================================================
    // REMOVER PARÂMETRO
    // =========================================================

    @Transactional
    public ModeloTesteResponseDTO removerParametro(
            Long modeloTesteId,
            Long parametroId) {

        ModeloTesteEntity modeloTeste =
                buscarEntidade(modeloTesteId);

        TipoParametroEntity parametro =
                modeloTeste.getParametros()
                        .stream()
                        .filter(p -> p.getId().equals(parametroId))
                        .findFirst()
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Parâmetro não encontrado no modelo de teste: "
                                                + parametroId
                                )
                        );

        modeloTeste.removeParametro(parametro);

        return new ModeloTesteResponseDTO(modeloTeste);
    }


    // =========================================================
    // BUSCAR ENTIDADE
    // =========================================================

    private ModeloTesteEntity buscarEntidade(Long id) {

        return modeloTesteRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Modelo de teste não encontrado: " + id
                        )
                );
    }


    // =========================================================
    // CRIAR TIPO DE PARÂMETRO
    // =========================================================

    private TipoParametroEntity criarParametro(
            TipoParametroRequestDTO dto) {

        TipoParametroEntity parametro;


        // -----------------------------------------------------
        // BOOLEANO
        // -----------------------------------------------------

        if (dto instanceof TipoParametroBooleanoRequestDTO booleano) {

            TipoParametroBooleanoEntity entidade =
                    new TipoParametroBooleanoEntity();

            entidade.setValorEsperado(
                    booleano.isValorEsperado()
            );

            parametro = entidade;


        // -----------------------------------------------------
        // NUMÉRICO
        // -----------------------------------------------------

        } else if (dto instanceof TipoParametroNumericoRequestDTO numerico) {

            TipoParametroNumericoEntity entidade =
                    new TipoParametroNumericoEntity();

            entidade.setValorMinimo(
                    numerico.getValorMinimo()
            );

            entidade.setValorMaximo(
                    numerico.getValorMaximo()
            );

            entidade.setUnidadeMedida(
                    numerico.getUnidadeMedida()
            );

            parametro = entidade;


        // -----------------------------------------------------
        // TEXTUAL
        // -----------------------------------------------------

        } else if (dto instanceof TipoParametroTextualRequestDTO textual) {

            TipoParametroTextualEntity entidade =
                    new TipoParametroTextualEntity();

            entidade.setEsperado(
                    textual.getEsperado()
            );

            entidade.setPossibilidades(
                    textual.getPossibilidades()
            );

            parametro = entidade;


        // -----------------------------------------------------
        // TIPO DESCONHECIDO
        // -----------------------------------------------------

        } else {

            throw new IllegalArgumentException(
                    "Tipo de parâmetro não suportado: "
                            + dto.getClass().getSimpleName()
            );
        }


        // O atributo comum a todos os tipos
        parametro.setNome(dto.getNome());

        return parametro;
    }
}