package com.foodProIA.FoodProIA.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodProIA.FoodProIA.dto.request.RegistroManutencaoRequestDTO;
import com.foodProIA.FoodProIA.dto.response.RegistroManutencaoResponseDTO;
import com.foodProIA.FoodProIA.entity.FuncionarioEntity;
import com.foodProIA.FoodProIA.entity.PlanejamentoManutencaoEntity;
import com.foodProIA.FoodProIA.entity.RegistroManutencaoEntity;
import com.foodProIA.FoodProIA.repository.FuncionarioRepository;
import com.foodProIA.FoodProIA.repository.PlanejamentoManutencaoRepository;
import com.foodProIA.FoodProIA.repository.RegistroManutencaoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RegistroManutencaoService {

    private final RegistroManutencaoRepository registroManutencaoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final PlanejamentoManutencaoRepository planejamentoManutencaoRepository;

    public RegistroManutencaoResponseDTO criar(
            RegistroManutencaoRequestDTO dto) {

        RegistroManutencaoEntity registro = new RegistroManutencaoEntity();

        preencherDados(registro, dto);

        registro = registroManutencaoRepository.save(registro);

        return new RegistroManutencaoResponseDTO(registro);
    }

    public RegistroManutencaoResponseDTO buscarPorId(Long id) {

        RegistroManutencaoEntity registro =
                buscarEntidadePorId(id);

        return new RegistroManutencaoResponseDTO(registro);
    }

    @Transactional(readOnly = true)
    public List<RegistroManutencaoResponseDTO> listarTodos() {

        return registroManutencaoRepository.findAll()
                .stream()
                .map(RegistroManutencaoResponseDTO::new)
                .toList();
    }

    public RegistroManutencaoResponseDTO atualizar(
            Long id,
            RegistroManutencaoRequestDTO dto) {

        RegistroManutencaoEntity registro =
                buscarEntidadePorId(id);

        preencherDados(registro, dto);

        registro = registroManutencaoRepository.save(registro);

        return new RegistroManutencaoResponseDTO(registro);
    }

    public void excluir(Long id) {

        RegistroManutencaoEntity registro =
                buscarEntidadePorId(id);

        registroManutencaoRepository.delete(registro);
    }

    private RegistroManutencaoEntity buscarEntidadePorId(Long id) {

        return registroManutencaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Registro de manutenção não encontrado com ID: " + id));
    }

    private void preencherDados(
            RegistroManutencaoEntity registro,
            RegistroManutencaoRequestDTO dto) {

        registro.setData(dto.getData());
        registro.setNumeroDeSerieEquipamento(
                dto.getNumeroDeSerieEquipamento());
        registro.setTipoDeManutencao(dto.getTipoDeManutencao());
        registro.setTempoParado(dto.getTempoParado());
        registro.setDiasParado(dto.getDiasParado());
        registro.setMesesParado(dto.getMesesParado());
        registro.setDataDeLiberacao(dto.getDataDeLiberacao());
        registro.setComentario(dto.getComentario());

        registro.setFuncionarios(
                buscarFuncionarios(dto.getIdsFuncionarios()));

        registro.setPlanejamentoManutencao(
                buscarPlanejamento(dto.getIdPlanejamentoManutencao()));
    }

    private List<FuncionarioEntity> buscarFuncionarios(
            List<Long> idsFuncionarios) {

        if (idsFuncionarios == null || idsFuncionarios.isEmpty()) {
            return new ArrayList<>();
        }

        Set<Long> idsUnicos = new HashSet<>(idsFuncionarios);

        if (idsUnicos.size() != idsFuncionarios.size()) {
            throw new IllegalArgumentException(
                    "A lista de funcionários contém IDs duplicados.");
        }

        List<FuncionarioEntity> funcionarios =
                funcionarioRepository.findAllById(idsFuncionarios);

        if (funcionarios.size() != idsUnicos.size()) {
            Set<Long> idsEncontrados = new HashSet<>();

            for (FuncionarioEntity funcionario : funcionarios) {
                idsEncontrados.add(funcionario.getId());
            }

            Set<Long> idsNaoEncontrados = new HashSet<>(idsUnicos);
            idsNaoEncontrados.removeAll(idsEncontrados);

            throw new EntityNotFoundException(
                    "Funcionários não encontrados para os IDs: "
                            + idsNaoEncontrados);
        }

        return funcionarios;
    }

    private PlanejamentoManutencaoEntity buscarPlanejamento(Long id) {

        if (id == null) {
            return null;
        }

        return planejamentoManutencaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Planejamento de manutenção não encontrado com ID: "
                                + id));
    }
}