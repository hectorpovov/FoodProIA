package com.foodProIA.FoodProIA.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodProIA.FoodProIA.dto.request.TurnoRequestDTO;
import com.foodProIA.FoodProIA.dto.response.TurnoResponseDTO;
import com.foodProIA.FoodProIA.entity.TurnoEntity;
import com.foodProIA.FoodProIA.repository.TurnoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TurnoService {

    private final TurnoRepository turnoRepository;

    public TurnoResponseDTO criarTurno(TurnoRequestDTO dto) {

        TurnoEntity turno = new TurnoEntity();

        turno.setDescricao(dto.getDescricao());
        turno.setHoraInicio(dto.getHoraInicio());
        turno.setHoraTermino(dto.getHoraTermino());

        turno = turnoRepository.save(turno);

        return new TurnoResponseDTO(turno);
    }

    public TurnoResponseDTO alterarTurno(Long id, TurnoRequestDTO dto) {

        TurnoEntity turno = turnoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Turno não encontrado com o ID: " + id));

        turno.setDescricao(dto.getDescricao());
        turno.setHoraInicio(dto.getHoraInicio());
        turno.setHoraTermino(dto.getHoraTermino());

        turno = turnoRepository.save(turno);

        return new TurnoResponseDTO(turno);
    }

    @Transactional(readOnly = true)
    public TurnoResponseDTO buscarTurnoPorId(Long id) {

        TurnoEntity turno = turnoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Turno não encontrado com o ID: " + id));

        return new TurnoResponseDTO(turno);
    }

    @Transactional(readOnly = true)
    public List<TurnoResponseDTO> listarTurnos() {

        return turnoRepository.findAll()
                .stream()
                .map(TurnoResponseDTO::new)
                .toList();
    }

    public void excluirTurno(Long id) {

        TurnoEntity turno = turnoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Turno não encontrado com o ID: " + id));

        if (!turno.getFuncionarios().isEmpty()) {
            throw new IllegalStateException(
                    "Não é possível excluir o turno, pois existem funcionários associados a ele.");
        }

        if (!turno.getPlanejamentosDeProducao().isEmpty()) {
            throw new IllegalStateException(
                    "Não é possível excluir o turno, pois existem planejamentos de produção associados a ele.");
        }

        turnoRepository.delete(turno);
    }
}