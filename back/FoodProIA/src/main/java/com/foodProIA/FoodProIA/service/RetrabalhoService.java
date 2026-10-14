package com.foodProIA.FoodProIA.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodProIA.FoodProIA.dto.request.LoteInsumoPerdaRequestDTO;
import com.foodProIA.FoodProIA.dto.request.LoteMateriaPrimaPerdaRequestDTO;
import com.foodProIA.FoodProIA.dto.request.PerdaRequestDTO;
import com.foodProIA.FoodProIA.dto.request.RetrabalhoRequestDTO;
import com.foodProIA.FoodProIA.dto.response.RetrabalhoResponseDTO;
import com.foodProIA.FoodProIA.entity.EtapaEntity;
import com.foodProIA.FoodProIA.entity.LoteInsumoEntity;
import com.foodProIA.FoodProIA.entity.LoteInsumoPerdaEntity;
import com.foodProIA.FoodProIA.entity.LoteMateriaPrimaEntity;
import com.foodProIA.FoodProIA.entity.LoteMateriaPrimaPerdaEntity;
import com.foodProIA.FoodProIA.entity.PerdaEntity;
import com.foodProIA.FoodProIA.entity.RegistroDeProducaoEntity;
import com.foodProIA.FoodProIA.entity.RetrabalhoEntity;
import com.foodProIA.FoodProIA.repository.EtapaRepository;
import com.foodProIA.FoodProIA.repository.LoteInsumoRepository;
import com.foodProIA.FoodProIA.repository.LoteMateriaPrimaRepository;
import com.foodProIA.FoodProIA.repository.PerdaRepository;
import com.foodProIA.FoodProIA.repository.RegistroDeProducaoRepository;
import com.foodProIA.FoodProIA.repository.RetrabalhoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RetrabalhoService {

    private final RetrabalhoRepository retrabalhoRepository;
    private final PerdaRepository perdaRepository;
    private final RegistroDeProducaoRepository registroDeProducaoRepository;
    private final EtapaRepository etapaRepository;
    private final LoteMateriaPrimaRepository loteMateriaPrimaRepository;
    private final LoteInsumoRepository loteInsumoRepository;
    private final EntityManager entityManager;

    public RetrabalhoResponseDTO criar(RetrabalhoRequestDTO dto) {

        RetrabalhoEntity retrabalho = new RetrabalhoEntity();

        preencherDados(retrabalho, dto);

        retrabalho = retrabalhoRepository.save(retrabalho);

        criarPerdas(retrabalho, dto.getPerdas());

        return new RetrabalhoResponseDTO(retrabalho);
    }

    @Transactional(readOnly = true)
    public RetrabalhoResponseDTO buscarPorId(Long id) {

        RetrabalhoEntity retrabalho = buscarEntidadePorId(id);

        return new RetrabalhoResponseDTO(retrabalho);
    }

    @Transactional(readOnly = true)
    public List<RetrabalhoResponseDTO> listarTodos() {

        return retrabalhoRepository.findAll()
                .stream()
                .map(RetrabalhoResponseDTO::new)
                .toList();
    }

    public RetrabalhoResponseDTO atualizar(
            Long id,
            RetrabalhoRequestDTO dto) {

        RetrabalhoEntity retrabalho = buscarEntidadePorId(id);

        preencherDados(retrabalho, dto);

        limparPerdas(retrabalho);

        retrabalho = retrabalhoRepository.save(retrabalho);

        criarPerdas(retrabalho, dto.getPerdas());

        return new RetrabalhoResponseDTO(retrabalho);
    }

    public void excluir(Long id) {

        RetrabalhoEntity retrabalho = buscarEntidadePorId(id);

        limparPerdas(retrabalho);

        retrabalhoRepository.delete(retrabalho);
    }

    private RetrabalhoEntity buscarEntidadePorId(Long id) {

        return retrabalhoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Retrabalho não encontrado com ID: " + id));
    }

    private void preencherDados(
            RetrabalhoEntity retrabalho,
            RetrabalhoRequestDTO dto) {

        retrabalho.setData(dto.getData());
        retrabalho.setTempoGasto(dto.getTempoGasto());
        retrabalho.setComentario(dto.getComentario());

        retrabalho.setRegistroDeProducao(
                buscarRegistroDeProducao(dto.getIdRegistroDeProducao()));

        retrabalho.setEtapa(
                buscarEtapa(dto.getIdEtapa()));
    }

    private RegistroDeProducaoEntity buscarRegistroDeProducao(Long id) {

        return registroDeProducaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Registro de produção não encontrado com ID: " + id));
    }

    private EtapaEntity buscarEtapa(Long id) {

        if (id == null) {
            return null;
        }

        return etapaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Etapa não encontrada com ID: " + id));
    }

    private LoteMateriaPrimaEntity buscarLoteMateriaPrima(Long id) {

        return loteMateriaPrimaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Lote de matéria-prima não encontrado com ID: " + id));
    }

    private LoteInsumoEntity buscarLoteInsumo(Long id) {

        return loteInsumoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Lote de insumo não encontrado com ID: " + id));
    }

    private void criarPerdas(
            RetrabalhoEntity retrabalho,
            List<PerdaRequestDTO> perdasDTO) {

        if (perdasDTO == null || perdasDTO.isEmpty()) {
            return;
        }

        for (PerdaRequestDTO dto : perdasDTO) {

            PerdaEntity perda = new PerdaEntity();

            perda.setData(dto.getData());
            perda.setComentario(dto.getComentario());
            perda.setRetrabalho(retrabalho);

            perda.setRegistroDeProducao(
                    buscarRegistroDeProducao(dto.getIdRegistroDeProducao()));

            perda.setEtapa(
                    buscarEtapa(dto.getIdEtapa()));

            perda = perdaRepository.save(perda);

            retrabalho.getPerdas().add(perda);

            criarLotesMateriaPrimaPerda(
                    perda,
                    dto.getLotesMateriaPrima());

            criarLotesInsumoPerda(
                    perda,
                    dto.getLotesInsumo());
        }
    }

    private void criarLotesMateriaPrimaPerda(
            PerdaEntity perda,
            List<LoteMateriaPrimaPerdaRequestDTO> lotesDTO) {

        if (lotesDTO == null || lotesDTO.isEmpty()) {
            return;
        }

        for (LoteMateriaPrimaPerdaRequestDTO dto : lotesDTO) {

            LoteMateriaPrimaEntity loteMateriaPrima =
                    buscarLoteMateriaPrima(dto.getIdLoteMateriaPrima());

            LoteMateriaPrimaPerdaEntity lotePerda =
                    new LoteMateriaPrimaPerdaEntity(
                            loteMateriaPrima,
                            perda,
                            dto.getQuantidade());

            entityManager.persist(lotePerda);
        }
    }

    private void criarLotesInsumoPerda(
            PerdaEntity perda,
            List<LoteInsumoPerdaRequestDTO> lotesDTO) {

        if (lotesDTO == null || lotesDTO.isEmpty()) {
            return;
        }

        for (LoteInsumoPerdaRequestDTO dto : lotesDTO) {

            LoteInsumoEntity loteInsumo =
                    buscarLoteInsumo(dto.getIdLoteInsumo());

            LoteInsumoPerdaEntity lotePerda =
                    new LoteInsumoPerdaEntity();

            lotePerda.setQuantidade(dto.getQuantidade());
            lotePerda.setLoteInsumo(loteInsumo);
            lotePerda.setPerda(perda);

            perda.getLotesInsumo().add(lotePerda);

            entityManager.persist(lotePerda);
        }
    }

    private void limparPerdas(RetrabalhoEntity retrabalho) {

        List<PerdaEntity> perdas =
                new ArrayList<>(retrabalho.getPerdas());

        for (PerdaEntity perda : perdas) {

            limparLotesMateriaPrimaPerda(perda);
            limparLotesInsumoPerda(perda);

            retrabalho.getPerdas().remove(perda);

            entityManager.remove(perda);
        }
    }

    private void limparLotesMateriaPrimaPerda(PerdaEntity perda) {

        List<LoteMateriaPrimaPerdaEntity> lotes =
                new ArrayList<>(perda.getLotesMateriaPrima());

        for (LoteMateriaPrimaPerdaEntity lote : lotes) {

            if (lote.getLoteMateriaPrima() != null) {
                lote.getLoteMateriaPrima()
                        .getPerdas()
                        .remove(lote);
            }

            perda.getLotesMateriaPrima().remove(lote);

            entityManager.remove(lote);
        }
    }

    private void limparLotesInsumoPerda(PerdaEntity perda) {

        List<LoteInsumoPerdaEntity> lotes =
                new ArrayList<>(perda.getLotesInsumo());

        for (LoteInsumoPerdaEntity lote : lotes) {

            perda.getLotesInsumo().remove(lote);

            entityManager.remove(lote);
        }
    }
}