package com.foodProIA.FoodProIA.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.foodProIA.FoodProIA.dto.summary.FornecedorSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.FuncionarioSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.LoteMateriaPrimaPerdaSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.TesteDeQualidadeSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.MateriaPrimaSummaryDTO;
import com.foodProIA.FoodProIA.entity.LoteMateriaPrimaEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoteMateriaPrimaResponseDTO {

    private Long id;

    private LocalDate data;

    private String marca;

    private BigDecimal quantidade;

    private String codigoNFe;

    private BigDecimal valorCompra;

    private LocalDate dataValidade;

    private MateriaPrimaSummaryDTO materiaPrima;

    private List<TesteDeQualidadeSummaryDTO> testes;

    private FornecedorSummaryDTO fornecedor;

    private FuncionarioSummaryDTO recebedor;

    private List<LoteMateriaPrimaPerdaSummaryDTO> perdas;

    public LoteMateriaPrimaResponseDTO(
            LoteMateriaPrimaEntity loteMateriaPrima) {

        this.id = loteMateriaPrima.getId();
        this.data = loteMateriaPrima.getData();
        this.marca = loteMateriaPrima.getMarca();
        this.quantidade = loteMateriaPrima.getQuantidade();
        this.codigoNFe = loteMateriaPrima.getCodigoNFe();
        this.valorCompra = loteMateriaPrima.getValorCompra();
        this.dataValidade = loteMateriaPrima.getDataValidade();

        if (loteMateriaPrima.getMateriaPrima() != null) {
            this.materiaPrima =
                    new MateriaPrimaSummaryDTO(
                            loteMateriaPrima.getMateriaPrima());
        }

        this.testes = loteMateriaPrima.getTestes()
                .stream()
                .map(TesteDeQualidadeSummaryDTO::new)
                .toList();

        if (loteMateriaPrima.getFornecedor() != null) {
            this.fornecedor =
                    new FornecedorSummaryDTO(
                            loteMateriaPrima.getFornecedor());
        }

        if (loteMateriaPrima.getRecebedor() != null) {
            this.recebedor =
                    new FuncionarioSummaryDTO(
                            loteMateriaPrima.getRecebedor());
        }

        this.perdas = loteMateriaPrima.getPerdas()
                .stream()
                .map(LoteMateriaPrimaPerdaSummaryDTO::new)
                .toList();
    }
}