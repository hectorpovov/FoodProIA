package com.foodProIA.FoodProIA.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodProIA.FoodProIA.dto.request.FraudeRequestDTO;
import com.foodProIA.FoodProIA.dto.request.ParametroRequestDTO;
import com.foodProIA.FoodProIA.dto.request.TesteDeQualidadeRequestDTO;
import com.foodProIA.FoodProIA.dto.response.TesteDeQualidadeResponseDTO;
import com.foodProIA.FoodProIA.entity.FornecedorEntity;
import com.foodProIA.FoodProIA.entity.FraudeEntity;
import com.foodProIA.FoodProIA.entity.ModeloTesteEntity;
import com.foodProIA.FoodProIA.entity.ParametroEntity;
import com.foodProIA.FoodProIA.entity.TesteDeQualidadeEntity;
import com.foodProIA.FoodProIA.entity.TipoParametroBooleanoEntity;
import com.foodProIA.FoodProIA.entity.TipoParametroEntity;
import com.foodProIA.FoodProIA.entity.TipoParametroNumericoEntity;
import com.foodProIA.FoodProIA.entity.TipoParametroTextualEntity;
import com.foodProIA.FoodProIA.repository.FornecedorRepository;
import com.foodProIA.FoodProIA.repository.ModeloTesteRepository;
import com.foodProIA.FoodProIA.repository.ParametroRepository;
import com.foodProIA.FoodProIA.repository.TesteDeQualidadeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TesteDeQualidadeService {

    private final TesteDeQualidadeRepository testeRepository;
    private final ModeloTesteRepository modeloTesteRepository;
    private final ParametroRepository parametroRepository;
    private final FornecedorRepository fornecedorRepository;


    // =========================================================
    // INSERIR
    // =========================================================

    @Transactional
    public TesteDeQualidadeResponseDTO inserir(
            TesteDeQualidadeRequestDTO dto) {

        ModeloTesteEntity modelo =
                buscarModelo(dto.getIdModeloTeste());

        validarParametrosDoTeste(modelo, dto.getParametros());

        TesteDeQualidadeEntity teste =
                new TesteDeQualidadeEntity();

        teste.setDataEHorario(
                dto.getDataEHorario() != null
                        ? dto.getDataEHorario()
                        : LocalDateTime.now()
        );

        teste.setModelo(modelo);

        /*
         * Cria todos os parâmetros enviados.
         */
        for (ParametroRequestDTO parametroDTO : dto.getParametros()) {

            TipoParametroEntity tipo =
                    buscarTipoParametroDoModelo(
                            modelo,
                            parametroDTO.getTipoParametroId()
                    );

            ParametroEntity parametro =
                    new ParametroEntity(
                            parametroDTO.getValor(),
                            tipo
                    );

            teste.adicionarParametro(parametro);

            /*
             * Se o valor não estiver de acordo com o tipo,
             * cria automaticamente uma fraude.
             */
            verificarParametroEAdicionarFraude(
                    teste,
                    parametro
            );
        }

        TesteDeQualidadeEntity salvo =
                testeRepository.save(teste);

        return new TesteDeQualidadeResponseDTO(salvo);
    }


    // =========================================================
    // ALTERAR
    // =========================================================

    @Transactional
    public TesteDeQualidadeResponseDTO alterar(
            Long id,
            TesteDeQualidadeRequestDTO dto) {

        TesteDeQualidadeEntity teste =
                buscarEntidade(id);

        ModeloTesteEntity modelo =
                buscarModelo(dto.getIdModeloTeste());

        validarParametrosDoTeste(
                modelo,
                dto.getParametros()
        );

        teste.setDataEHorario(
                dto.getDataEHorario()
        );

        teste.setModelo(modelo);

        /*
         * Como o DTO não possui IDs dos parâmetros,
         * vamos substituir os parâmetros antigos pelos novos.
         */
        if(dto.getParametros() != null){
            List<ParametroEntity> parametrosAntigos =
                    new ArrayList<>(teste.getParametros());

            for (ParametroEntity parametro : parametrosAntigos) {

                teste.removerParametro(parametro);

                parametroRepository.delete(parametro);
            }

            /*
            * As fraudes geradas anteriormente também precisam
            * ser recalculadas, pois os parâmetros mudaram.
            */
            List<FraudeEntity> fraudesAntigas =
                    new ArrayList<>(teste.getFraudes());

            for (FraudeEntity fraude : fraudesAntigas) {
                teste.removerFraude(fraude);
            }

            /*
            * Cria novamente os parâmetros.
            */
            for (ParametroRequestDTO parametroDTO : dto.getParametros()) {

                TipoParametroEntity tipo =
                        buscarTipoParametroDoModelo(
                                modelo,
                                parametroDTO.getTipoParametroId()
                        );

                ParametroEntity parametro =
                        new ParametroEntity(
                                parametroDTO.getValor(),
                                tipo
                        );

                teste.adicionarParametro(parametro);

                verificarParametroEAdicionarFraude(
                        teste,
                        parametro
                );
            }
        }

        return new TesteDeQualidadeResponseDTO(teste);
    }


    // =========================================================
    // EXCLUIR
    // =========================================================

    @Transactional
    public void excluir(Long id) {

        TesteDeQualidadeEntity teste =
                buscarEntidade(id);

        /*
         * Como ParametroEntity não possui cascade REMOVE
         * a partir de TesteDeQualidade, removemos os
         * relacionamentos e os parâmetros manualmente.
         */
        List<ParametroEntity> parametros =
                new ArrayList<>(teste.getParametros());

        for (ParametroEntity parametro : parametros) {
            teste.removerParametro(parametro);
        }

        testeRepository.delete(teste);
    }


    // =========================================================
    // BUSCAR POR ID
    // =========================================================

    @Transactional(readOnly = true)
    public TesteDeQualidadeResponseDTO buscarPorId(Long id) {

        TesteDeQualidadeEntity teste =
                buscarEntidade(id);

        return new TesteDeQualidadeResponseDTO(teste);
    }


    // =========================================================
    // ADICIONAR FRAUDE
    // =========================================================

    @Transactional
    public TesteDeQualidadeResponseDTO adicionarFraude(
            Long testeId,
            FraudeRequestDTO dto) {

        TesteDeQualidadeEntity teste =
                buscarEntidade(testeId);

        FornecedorEntity fornecedor =
                fornecedorRepository.findById(
                        dto.getIdFornecedor()
                ).orElseThrow(() ->
                        new IllegalArgumentException(
                                "Fornecedor não encontrado: "
                                        + dto.getIdFornecedor()
                        )
                );

        FraudeEntity fraude =
                new FraudeEntity();

        fraude.setDataEHorario(
                dto.getDataEHorario()
        );

        fraude.setClassificacao(
                dto.getClassificacao()
        );

        fraude.setDescricao(
                dto.getDescricao()
        );

        fraude.setFornecedor(fornecedor);

        teste.adicionarFraude(fraude);

        return new TesteDeQualidadeResponseDTO(teste);
    }


    // =========================================================
    // REMOVER FRAUDE
    // =========================================================

    @Transactional
    public TesteDeQualidadeResponseDTO removerFraude(
            Long testeId,
            Long fraudeId) {

        TesteDeQualidadeEntity teste =
                buscarEntidade(testeId);

        FraudeEntity fraude =
                teste.getFraudes()
                        .stream()
                        .filter(
                                f -> f.getId().equals(fraudeId)
                        )
                        .findFirst()
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Fraude não encontrada no teste: "
                                                + fraudeId
                                )
                        );

        teste.removerFraude(fraude);

        /*
         * Como existe orphanRemoval = true na relação
         * TesteDeQualidade -> Fraude, o JPA fará a exclusão.
         */

        return new TesteDeQualidadeResponseDTO(teste);
    }


    // =========================================================
    // VALIDAR TODOS OS PARÂMETROS
    // =========================================================

    private void validarParametrosDoTeste(
            ModeloTesteEntity modelo,
            List<ParametroRequestDTO> parametros) {

        if (parametros == null) {
            throw new IllegalArgumentException(
                    "O teste de qualidade deve possuir parâmetros."
            );
        }

        List<TipoParametroEntity> tiposDoModelo =
                modelo.getParametros();

        /*
         * Um teste precisa possuir exatamente a mesma
         * quantidade de parâmetros do modelo.
         */
        if (parametros.size() != tiposDoModelo.size()) {

            throw new IllegalArgumentException(
                    "O teste deve possuir exatamente "
                            + tiposDoModelo.size()
                            + " parâmetros, conforme o modelo."
            );
        }

        /*
         * Guarda os tipos já utilizados para impedir
         * parâmetros duplicados.
         */
        Set<Long> tiposUtilizados = new HashSet<>();

        for (ParametroRequestDTO parametro : parametros) {

            Long tipoId =
                    parametro.getTipoParametroId();

            /*
             * Verifica se o tipo pertence ao modelo.
             */
            boolean pertenceAoModelo =
                    tiposDoModelo
                            .stream()
                            .anyMatch(
                                    tipo -> tipo.getId()
                                            .equals(tipoId)
                            );

            if (!pertenceAoModelo) {

                throw new IllegalArgumentException(
                        "O tipo de parâmetro "
                                + tipoId
                                + " não pertence ao modelo de teste."
                );
            }

            /*
             * Verifica se não está repetido.
             */
            if (!tiposUtilizados.add(tipoId)) {

                throw new IllegalArgumentException(
                        "O tipo de parâmetro "
                                + tipoId
                                + " foi informado mais de uma vez."
                );
            }
        }

        /*
         * Como já verificamos que a quantidade é igual
         * e que todos pertencem ao modelo sem repetição,
         * garantimos que todos os tipos do modelo estão
         * presentes.
         */
    }


    // =========================================================
    // BUSCAR TIPO DE PARÂMETRO DO MODELO
    // =========================================================

    private TipoParametroEntity buscarTipoParametroDoModelo(
            ModeloTesteEntity modelo,
            Long tipoParametroId) {

        return modelo.getParametros()
                .stream()
                .filter(
                        tipo -> tipo.getId()
                                .equals(tipoParametroId)
                )
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "O tipo de parâmetro "
                                        + tipoParametroId
                                        + " não pertence ao modelo de teste."
                        )
                );
    }


    // =========================================================
    // VERIFICAR PARÂMETRO E CRIAR FRAUDE
    // =========================================================

    private void verificarParametroEAdicionarFraude(
            TesteDeQualidadeEntity teste,
            ParametroEntity parametro) {

        TipoParametroEntity tipo =
                parametro.getTipo();

        String valor =
                parametro.getValor();

        String motivo =
                verificarValor(tipo, valor);

        /*
         * Se motivo == null, o parâmetro está correto.
         *
         * Caso contrário, criamos automaticamente uma fraude.
         */
        if (motivo != null) {

            FraudeEntity fraude =
                    new FraudeEntity();

            fraude.setDataEHorario(
                    LocalDateTime.now()
            );

            fraude.setClassificacao(
                    "PARAMETRO_FORA_DO_ESPERADO"
            );

            fraude.setDescricao(
                    motivo
            );

            teste.adicionarFraude(fraude);
        }
    }


    // =========================================================
    // VERIFICAR VALOR
    // =========================================================

    private String verificarValor(
            TipoParametroEntity tipo,
            String valor) {

        /*
         * BOOLEANO
         */
        if (tipo instanceof TipoParametroBooleanoEntity booleano) {

            if (!valor.equalsIgnoreCase("true")
                    && !valor.equalsIgnoreCase("false")) {

                return "O valor informado para o parâmetro '"
                        + tipo.getNome()
                        + "' não é um valor booleano válido.";
            }

            boolean valorInformado =
                    Boolean.parseBoolean(valor);

            if (valorInformado != booleano.isValorEsperado()) {

                return "O parâmetro '"
                        + tipo.getNome()
                        + "' possui valor "
                        + valorInformado
                        + ", mas o esperado era "
                        + booleano.isValorEsperado()
                        + ".";
            }

            return null;
        }


        /*
         * NUMÉRICO
         */
        if (tipo instanceof TipoParametroNumericoEntity numerico) {

            float valorInformado;

            try {

                valorInformado =
                        Float.parseFloat(valor);

            } catch (NumberFormatException e) {

                return "O valor informado para o parâmetro '"
                        + tipo.getNome()
                        + "' não é um número válido.";
            }

            if (valorInformado < numerico.getValorMinimo()
                    || valorInformado > numerico.getValorMaximo()) {

                return "O valor do parâmetro '"
                        + tipo.getNome()
                        + "' está fora do intervalo esperado: "
                        + numerico.getValorMinimo()
                        + " a "
                        + numerico.getValorMaximo()
                        + " "
                        + numerico.getUnidadeMedida()
                        + ". Valor informado: "
                        + valorInformado
                        + ".";
            }

            return null;
        }


        /*
         * TEXTUAL
         */
        if (tipo instanceof TipoParametroTextualEntity textual) {

            /*
             * Se houver possibilidades cadastradas,
             * o valor precisa estar entre elas.
             */
            if (textual.getPossibilidades() != null
                    && !textual.getPossibilidades().isEmpty()
                    && !textual.getPossibilidades()
                            .contains(valor)) {

                return "O valor '"
                        + valor
                        + "' não é uma possibilidade válida para o parâmetro '"
                        + tipo.getNome()
                        + "'.";
            }

            /*
             * Além de ser uma possibilidade válida,
             * verifica se é o valor esperado.
             */
            if (textual.getEsperado() != null
                    && !textual.getEsperado().equals(valor)) {

                return "O parâmetro '"
                        + tipo.getNome()
                        + "' possui valor '"
                        + valor
                        + "', mas o esperado era '"
                        + textual.getEsperado()
                        + "'.";
            }

            return null;
        }


        throw new IllegalArgumentException(
                "Tipo de parâmetro não suportado: "
                        + tipo.getClass().getSimpleName()
        );
    }


    // =========================================================
    // BUSCAR TESTE
    // =========================================================

    private TesteDeQualidadeEntity buscarEntidade(Long id) {

        return testeRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Teste de qualidade não encontrado: "
                                        + id
                        )
                );
    }


    // =========================================================
    // BUSCAR MODELO
    // =========================================================

    private ModeloTesteEntity buscarModelo(Long id) {

        return modeloTesteRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Modelo de teste não encontrado: "
                                        + id
                        )
                );
    }
}