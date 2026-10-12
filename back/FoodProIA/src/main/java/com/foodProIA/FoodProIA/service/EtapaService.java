package com.foodProIA.FoodProIA.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodProIA.FoodProIA.dto.request.EtapaRequestDTO;
import com.foodProIA.FoodProIA.dto.request.ParametroRequestDTO;
import com.foodProIA.FoodProIA.dto.request.TipoEtapaRequestDTO;
import com.foodProIA.FoodProIA.dto.request.TipoParametroBooleanoRequestDTO;
import com.foodProIA.FoodProIA.dto.request.TipoParametroNumericoRequestDTO;
import com.foodProIA.FoodProIA.dto.request.TipoParametroRequestDTO;
import com.foodProIA.FoodProIA.dto.request.TipoParametroTextualRequestDTO;

import com.foodProIA.FoodProIA.dto.response.EtapaResponseDTO;
import com.foodProIA.FoodProIA.dto.response.TipoEtapaResponseDTO;

import com.foodProIA.FoodProIA.entity.EquipamentoEntity;
import com.foodProIA.FoodProIA.entity.EtapaEntity;
import com.foodProIA.FoodProIA.entity.InsumoEntity;
import com.foodProIA.FoodProIA.entity.MateriaPrimaEntity;
import com.foodProIA.FoodProIA.entity.ParametroEntity;
import com.foodProIA.FoodProIA.entity.RegistroDeProducaoEntity;
import com.foodProIA.FoodProIA.entity.TipoEtapaEntity;
import com.foodProIA.FoodProIA.entity.TipoParametroBooleanoEntity;
import com.foodProIA.FoodProIA.entity.TipoParametroEntity;
import com.foodProIA.FoodProIA.entity.TipoParametroNumericoEntity;
import com.foodProIA.FoodProIA.entity.TipoParametroTextualEntity;

import com.foodProIA.FoodProIA.repository.EquipamentoRepository;
import com.foodProIA.FoodProIA.repository.EtapaRepository;
import com.foodProIA.FoodProIA.repository.InsumoRepository;
import com.foodProIA.FoodProIA.repository.MateriaPrimaRepository;
import com.foodProIA.FoodProIA.repository.ParametroRepository;
import com.foodProIA.FoodProIA.repository.RegistroDeProducaoRepository;
import com.foodProIA.FoodProIA.repository.TipoEtapaRepository;
import com.foodProIA.FoodProIA.repository.TipoParametroRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EtapaService {

    private final TipoEtapaRepository tipoEtapaRepository;
    private final EtapaRepository etapaRepository;

    private final TipoParametroRepository tipoParametroRepository;
    private final ParametroRepository parametroRepository;

    private final EquipamentoRepository equipamentoRepository;
    private final MateriaPrimaRepository materiaPrimaRepository;
    private final InsumoRepository insumoRepository;
    private final RegistroDeProducaoRepository registroDeProducaoRepository;


    // =========================================================
    // TIPO DE ETAPA
    // =========================================================

    public TipoEtapaResponseDTO criarTipoEtapa(
            TipoEtapaRequestDTO dto) {

        TipoEtapaEntity tipoEtapa = new TipoEtapaEntity();

        tipoEtapa.setNome(dto.getNome());
        tipoEtapa.setObservacoes(dto.getObservacoes());
        tipoEtapa.setTempoEstimado(dto.getTempoEstimado());

        definirEquipamento(tipoEtapa, dto.getIdEquipamento());

        substituirParametrosTipo(tipoEtapa, dto.getParametros());

        substituirMateriasPrimas(
                tipoEtapa,
                dto.getIdsMateriasPrimas()
        );

        substituirInsumos(
                tipoEtapa,
                dto.getIdsInsumos()
        );

        tipoEtapa = tipoEtapaRepository.save(tipoEtapa);

        return new TipoEtapaResponseDTO(tipoEtapa);
    }


    public TipoEtapaResponseDTO alterarTipoEtapa(
            Long id,
            TipoEtapaRequestDTO dto) {

        TipoEtapaEntity tipoEtapa = buscarTipoEtapa(id);

        tipoEtapa.setNome(dto.getNome());
        tipoEtapa.setObservacoes(dto.getObservacoes());
        tipoEtapa.setTempoEstimado(dto.getTempoEstimado());

        definirEquipamento(tipoEtapa, dto.getIdEquipamento());

        substituirParametrosTipo(tipoEtapa, dto.getParametros());

        substituirMateriasPrimas(
                tipoEtapa,
                dto.getIdsMateriasPrimas()
        );

        substituirInsumos(
                tipoEtapa,
                dto.getIdsInsumos()
        );

        tipoEtapa = tipoEtapaRepository.save(tipoEtapa);

        return new TipoEtapaResponseDTO(tipoEtapa);
    }


    @Transactional(readOnly = true)
    public TipoEtapaResponseDTO buscarTipoEtapaPorId(Long id) {

        return new TipoEtapaResponseDTO(buscarTipoEtapa(id));
    }


    @Transactional(readOnly = true)
    public List<TipoEtapaResponseDTO> listarTiposEtapa() {

        return tipoEtapaRepository.findAll()
                .stream()
                .map(TipoEtapaResponseDTO::new)
                .toList();
    }


    public void excluirTipoEtapa(Long id) {

        TipoEtapaEntity tipoEtapa = buscarTipoEtapa(id);

        if (!tipoEtapa.getEtapas().isEmpty()) {
            throw new IllegalStateException(
                    "Não é possível excluir um tipo de etapa "
                    + "que possui etapas registradas."
            );
        }

        // Remove a associação pelo lado proprietário.
        for (MateriaPrimaEntity materiaPrima :
                new ArrayList<>(tipoEtapa.getMateriasPrimas())) {

            materiaPrima.getTiposEtapa().remove(tipoEtapa);
        }

        tipoEtapa.getMateriasPrimas().clear();

        // Remove as associações com roteiros e insumos.
        tipoEtapa.getRoteirosDeProducao().clear();
        tipoEtapa.getInsumos().clear();

        /*
         * Remove as associações com os parâmetros, mas não
         * exclui fisicamente as definições.
         *
         * Elas podem ser referenciadas por parâmetros de
         * etapas históricas.
         */
        tipoEtapa.getParametros().clear();

        tipoEtapaRepository.delete(tipoEtapa);
    }


    // =========================================================
    // ETAPA CONCRETA
    // =========================================================

    public EtapaResponseDTO criarEtapa(EtapaRequestDTO dto) {

        TipoEtapaEntity tipoEtapa =
                buscarTipoEtapa(dto.getIdTipoEtapa());

        RegistroDeProducaoEntity registroDeProducao =
                registroDeProducaoRepository
                        .findById(dto.getIdRegistroDeProducao())
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Registro de produção não encontrado."
                                )
                        );

        EtapaEntity etapa = new EtapaEntity();

        etapa.setTipoEtapa(tipoEtapa);
        etapa.setRegistroDeProducao(registroDeProducao);

        etapa.setDataInicio(dto.getDataInicio());
        etapa.setDataFim(dto.getDataFim());
        etapa.setObservacoes(dto.getObservacoes());
        etapa.setConsumoAgua(dto.getConsumoAgua());
        etapa.setEstado(dto.getEstado());

        etapa.setParametros(new ArrayList<>());

        etapa = etapaRepository.save(etapa);

        substituirParametrosEtapa(
                etapa,
                tipoEtapa,
                dto.getParametros()
        );

        etapa = etapaRepository.save(etapa);

        return new EtapaResponseDTO(etapa);
    }


    public EtapaResponseDTO alterarEtapa(
            Long id,
            EtapaRequestDTO dto) {

        EtapaEntity etapa = buscarEtapa(id);

        /*
         * Uma etapa concreta mantém o tipo de etapa com o qual
         * foi criada. A troca de tipo poderia alterar o
         * significado dos parâmetros históricos.
         */
        if (!etapa.getTipoEtapa()
                .getId()
                .equals(dto.getIdTipoEtapa())) {

            throw new IllegalArgumentException(
                    "Não é permitido alterar o tipo de etapa "
                    + "de uma etapa já registrada."
            );
        }

        TipoEtapaEntity tipoEtapa = etapa.getTipoEtapa();

        /*
         * O registro de produção não é criado por este serviço.
         * Também não permitimos trocar o registro ao atualizar
         * uma etapa já existente.
         */
        if (!etapa.getRegistroDeProducao()
                .getId()
                .equals(dto.getIdRegistroDeProducao())) {

            throw new IllegalArgumentException(
                    "Não é permitido alterar o registro de produção "
                    + "de uma etapa já registrada."
            );
        }

        etapa.setDataInicio(dto.getDataInicio());
        etapa.setDataFim(dto.getDataFim());
        etapa.setObservacoes(dto.getObservacoes());
        etapa.setConsumoAgua(dto.getConsumoAgua());
        etapa.setEstado(dto.getEstado());

        substituirParametrosEtapa(
                etapa,
                tipoEtapa,
                dto.getParametros()
        );

        etapa = etapaRepository.save(etapa);

        return new EtapaResponseDTO(etapa);
    }


    @Transactional(readOnly = true)
    public EtapaResponseDTO buscarEtapaPorId(Long id) {

        return new EtapaResponseDTO(buscarEtapa(id));
    }


    @Transactional(readOnly = true)
    public List<EtapaResponseDTO> listarEtapas() {

        return etapaRepository.findAll()
                .stream()
                .map(EtapaResponseDTO::new)
                .toList();
    }


    public void excluirEtapa(Long id) {

        EtapaEntity etapa = buscarEtapa(id);

        /*
         * Este serviço não gerencia perdas, paradas ou
         * retrabalhos. Por isso, não exclui uma etapa que
         * possua registros dessas operações.
         */
        if (!etapa.getPerdas().isEmpty()
                || !etapa.getParadas().isEmpty()
                || !etapa.getRetrabalhos().isEmpty()) {

            throw new IllegalStateException(
                    "Não é possível excluir a etapa porque "
                    + "existem perdas, paradas ou retrabalhos "
                    + "associados a ela."
            );
        }

        // Exclui os valores dos parâmetros da etapa.
        parametroRepository.deleteAll(
                new ArrayList<>(etapa.getParametros())
        );

        etapa.getParametros().clear();

        etapaRepository.delete(etapa);
    }


    // =========================================================
    // PARÂMETROS DO TIPO DE ETAPA
    // =========================================================

    private void substituirParametrosTipo(
            TipoEtapaEntity tipoEtapa,
            List<TipoParametroRequestDTO> parametrosDTO) {

        if (tipoEtapa.getParametros() == null) {
            tipoEtapa.setParametros(new ArrayList<>());
        }

        List<TipoParametroEntity> parametrosAtuais =
                new ArrayList<>(tipoEtapa.getParametros());

        List<TipoParametroRequestDTO> novosDTO =
                parametrosDTO == null
                        ? List.of()
                        : parametrosDTO;

        Set<Long> idsRecebidos = new HashSet<>();

        List<TipoParametroEntity> parametrosAtualizados =
                new ArrayList<>();

        for (TipoParametroRequestDTO dto : novosDTO) {

            TipoParametroEntity parametro;

            if (dto.getId() == null) {

                // Nova definição de parâmetro.
                parametro = criarTipoParametro(dto);

                parametro = tipoParametroRepository.save(parametro);

            } else {

                Long idParametro = dto.getId();

                if (!idsRecebidos.add(idParametro)) {
                    throw new IllegalArgumentException(
                            "O mesmo parâmetro foi informado mais de uma vez."
                    );
                }

                parametro = parametrosAtuais.stream()
                        .filter(p -> p.getId().equals(idParametro))
                        .findFirst()
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "O parâmetro de ID "
                                        + idParametro
                                        + " não pertence ao tipo de etapa."
                                )
                        );

                /*
                 * Se a definição já foi usada por uma etapa,
                 * não permitimos modificar seu significado.
                 */
                if (parametroRepository.existsByTipo_Id(
                        parametro.getId())) {

                    if (!mesmaDefinicao(parametro, dto)) {
                        throw new IllegalStateException(
                                "O parâmetro de ID "
                                + parametro.getId()
                                + " já foi utilizado em uma etapa. "
                                + "Para alterar sua definição, remova-o "
                                + "da configuração atual e crie um "
                                + "novo parâmetro."
                        );
                    }

                } else {

                    atualizarTipoParametro(parametro, dto);

                    parametro = tipoParametroRepository.save(parametro);
                }
            }

            parametrosAtualizados.add(parametro);
        }

        /*
         * Substitui a lista de associações.
         *
         * As definições removidas não são excluídas fisicamente:
         * podem continuar sendo referenciadas por etapas antigas.
         */
        tipoEtapa.getParametros().clear();
        tipoEtapa.getParametros().addAll(parametrosAtualizados);
    }


    private TipoParametroEntity criarTipoParametro(
            TipoParametroRequestDTO dto) {

        TipoParametroEntity parametro;

        if (dto instanceof TipoParametroBooleanoRequestDTO booleanoDTO) {

            TipoParametroBooleanoEntity booleano =
                    new TipoParametroBooleanoEntity();

            booleano.setValorEsperado(booleanoDTO.isValorEsperado());

            parametro = booleano;

        } else if (
                dto instanceof TipoParametroNumericoRequestDTO numericoDTO) {

            TipoParametroNumericoEntity numerico =
                    new TipoParametroNumericoEntity();

            numerico.setValorMinimo(numericoDTO.getValorMinimo());
            numerico.setValorMaximo(numericoDTO.getValorMaximo());
            numerico.setUnidadeMedida(numericoDTO.getUnidadeMedida());

            parametro = numerico;

        } else if (
                dto instanceof TipoParametroTextualRequestDTO textualDTO) {

            TipoParametroTextualEntity textual =
                    new TipoParametroTextualEntity();

            textual.setEsperado(textualDTO.getEsperado());

            textual.setPossibilidades(
                    textualDTO.getPossibilidades() == null
                            ? new ArrayList<>()
                            : new ArrayList<>(textualDTO.getPossibilidades())
            );

            parametro = textual;

        } else {

            throw new IllegalArgumentException(
                    "Tipo de parâmetro não reconhecido."
            );
        }

        parametro.setNome(dto.getNome());

        return parametro;
    }


    private void atualizarTipoParametro(
            TipoParametroEntity parametro,
            TipoParametroRequestDTO dto) {

        if (parametro instanceof TipoParametroBooleanoEntity booleano
                && dto instanceof TipoParametroBooleanoRequestDTO booleanoDTO) {

            booleano.setNome(booleanoDTO.getNome());
            booleano.setValorEsperado(booleanoDTO.isValorEsperado());

        } else if (
                parametro instanceof TipoParametroNumericoEntity numerico
                && dto instanceof TipoParametroNumericoRequestDTO numericoDTO) {

            numerico.setNome(numericoDTO.getNome());
            numerico.setValorMinimo(numericoDTO.getValorMinimo());
            numerico.setValorMaximo(numericoDTO.getValorMaximo());
            numerico.setUnidadeMedida(numericoDTO.getUnidadeMedida());

        } else if (
                parametro instanceof TipoParametroTextualEntity textual
                && dto instanceof TipoParametroTextualRequestDTO textualDTO) {

            textual.setNome(textualDTO.getNome());
            textual.setEsperado(textualDTO.getEsperado());

            textual.setPossibilidades(
                    textualDTO.getPossibilidades() == null
                            ? new ArrayList<>()
                            : new ArrayList<>(textualDTO.getPossibilidades())
            );

        } else {

            throw new IllegalArgumentException(
                    "O tipo da definição não pode ser alterado. "
                    + "Crie um novo parâmetro."
            );
        }
    }


    private boolean mesmaDefinicao(
            TipoParametroEntity parametro,
            TipoParametroRequestDTO dto) {

        if (!parametro.getNome().equals(dto.getNome())) {
            return false;
        }

        if (parametro instanceof TipoParametroBooleanoEntity booleano
                && dto instanceof TipoParametroBooleanoRequestDTO booleanoDTO) {

            return booleano.isValorEsperado()
                    == booleanoDTO.isValorEsperado();
        }

        if (parametro instanceof TipoParametroNumericoEntity numerico
                && dto instanceof TipoParametroNumericoRequestDTO numericoDTO) {

            return Float.compare(
                        numerico.getValorMinimo(),
                        numericoDTO.getValorMinimo()
                    ) == 0
                    && Float.compare(
                        numerico.getValorMaximo(),
                        numericoDTO.getValorMaximo()
                    ) == 0
                    && java.util.Objects.equals(
                        numerico.getUnidadeMedida(),
                        numericoDTO.getUnidadeMedida()
                    );
        }

        if (parametro instanceof TipoParametroTextualEntity textual
                && dto instanceof TipoParametroTextualRequestDTO textualDTO) {

            List<String> possibilidadesDTO =
                    textualDTO.getPossibilidades() == null
                            ? List.of()
                            : textualDTO.getPossibilidades();

            return java.util.Objects.equals(
                        textual.getEsperado(),
                        textualDTO.getEsperado()
                    )
                    && java.util.Objects.equals(
                        textual.getPossibilidades(),
                        possibilidadesDTO
                    );
        }

        return false;
    }


    // =========================================================
    // PARÂMETROS DA ETAPA CONCRETA
    // =========================================================

    private void substituirParametrosEtapa(
            EtapaEntity etapa,
            TipoEtapaEntity tipoEtapa,
            List<ParametroRequestDTO> parametrosDTO) {

        List<ParametroRequestDTO> novosDTO =
                parametrosDTO == null
                        ? List.of()
                        : parametrosDTO;

        Map<Long, TipoParametroEntity> tiposPermitidos =
                new HashMap<>();

        for (TipoParametroEntity tipoParametro :
                tipoEtapa.getParametros()) {

            tiposPermitidos.put(
                    tipoParametro.getId(),
                    tipoParametro
            );
        }

        Map<Long, ParametroEntity> parametrosAtuaisPorTipo =
                new HashMap<>();

        for (ParametroEntity parametro : etapa.getParametros()) {

            if (parametro.getTipo() != null) {
                parametrosAtuaisPorTipo.put(
                        parametro.getTipo().getId(),
                        parametro
                );
            }
        }

        Set<Long> idsTiposRecebidos = new HashSet<>();

        List<ParametroEntity> parametrosAtualizados =
                new ArrayList<>();

        for (ParametroRequestDTO dto : novosDTO) {

            Long idTipoParametro = dto.getTipoParametroId();

            if (!idsTiposRecebidos.add(idTipoParametro)) {
                throw new IllegalArgumentException(
                        "O mesmo tipo de parâmetro foi informado "
                        + "mais de uma vez para a etapa."
                );
            }

            TipoParametroEntity tipoParametro =
                    tiposPermitidos.get(idTipoParametro);

            if (tipoParametro == null) {
                throw new IllegalArgumentException(
                        "O parâmetro de ID "
                        + idTipoParametro
                        + " não pertence ao tipo de etapa selecionado."
                );
            }

            ParametroEntity parametro =
                    parametrosAtuaisPorTipo.get(idTipoParametro);

            if (parametro == null) {

                parametro = new ParametroEntity();

                parametro.setTipo(tipoParametro);
                parametro.setValor(dto.getValor());
                parametro.setTesteDeQualidade(null);

            } else {

                parametro.setValor(dto.getValor());
            }

            parametro.setEtapa(etapa);

            parametrosAtualizados.add(parametro);
        }

        /*
         * Exclui apenas os valores que deixaram de fazer parte
         * da lista enviada para esta etapa.
         *
         * As definições TipoParametroEntity são preservadas.
         */
        List<ParametroEntity> removidos = new ArrayList<>();

        for (ParametroEntity parametro : etapa.getParametros()) {

            Long idTipoParametro = parametro.getTipo().getId();

            if (!idsTiposRecebidos.contains(idTipoParametro)) {
                removidos.add(parametro);
            }
        }

        if (!removidos.isEmpty()) {
            parametroRepository.deleteAll(removidos);
        }

        etapa.setParametros(parametrosAtualizados);
    }


    // =========================================================
    // EQUIPAMENTO, MATÉRIAS-PRIMAS E INSUMOS
    // =========================================================

    private void definirEquipamento(
            TipoEtapaEntity tipoEtapa,
            Long idEquipamento) {

        if (idEquipamento == null) {
            tipoEtapa.setEquipamento(null);
            return;
        }

        EquipamentoEntity equipamento =
                equipamentoRepository.findById(idEquipamento)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Equipamento não encontrado."
                                )
                        );

        tipoEtapa.setEquipamento(equipamento);
    }


    private void substituirMateriasPrimas(
            TipoEtapaEntity tipoEtapa,
            List<Long> idsMateriasPrimas) {

        List<MateriaPrimaEntity> atuais =
                new ArrayList<>(tipoEtapa.getMateriasPrimas());

        // Remove a associação pelo lado proprietário.
        for (MateriaPrimaEntity materiaPrima : atuais) {
            materiaPrima.getTiposEtapa().remove(tipoEtapa);
        }

        tipoEtapa.getMateriasPrimas().clear();

        if (idsMateriasPrimas == null) {
            return;
        }

        Set<Long> idsUnicos = new HashSet<>();

        for (Long idMateriaPrima : idsMateriasPrimas) {

            if (!idsUnicos.add(idMateriaPrima)) {
                continue;
            }

            MateriaPrimaEntity materiaPrima =
                    materiaPrimaRepository.findById(idMateriaPrima)
                            .orElseThrow(() ->
                                    new IllegalArgumentException(
                                            "Matéria-prima de ID "
                                            + idMateriaPrima
                                            + " não encontrada."
                                    )
                            );

            tipoEtapa.getMateriasPrimas().add(materiaPrima);

            if (!materiaPrima.getTiposEtapa().contains(tipoEtapa)) {
                materiaPrima.getTiposEtapa().add(tipoEtapa);
            }
        }
    }


    private void substituirInsumos(
            TipoEtapaEntity tipoEtapa,
            List<Long> idsInsumos) {

        tipoEtapa.getInsumos().clear();

        if (idsInsumos == null) {
            return;
        }

        Set<Long> idsUnicos = new HashSet<>();

        for (Long idInsumo : idsInsumos) {

            if (!idsUnicos.add(idInsumo)) {
                continue;
            }

            InsumoEntity insumo =
                    insumoRepository.findById(idInsumo)
                            .orElseThrow(() ->
                                    new IllegalArgumentException(
                                            "Insumo de ID "
                                            + idInsumo
                                            + " não encontrado."
                                    )
                            );

            tipoEtapa.getInsumos().add(insumo);
        }
    }


    // =========================================================
    // BUSCAS AUXILIARES
    // =========================================================

    private TipoEtapaEntity buscarTipoEtapa(Long id) {

        return tipoEtapaRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Tipo de etapa não encontrado."
                        )
                );
    }


    private EtapaEntity buscarEtapa(Long id) {

        return etapaRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Etapa não encontrada."
                        )
                );
    }
}