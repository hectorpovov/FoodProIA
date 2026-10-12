package com.foodProIA.FoodProIA.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.foodProIA.FoodProIA.dto.summary.FornecedorSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.FuncionarioSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.InsumoSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.LoteInsumoPerdaSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.LoteInsumoRegistroDeProducaoSummaryDTO;
import com.foodProIA.FoodProIA.entity.LoteInsumoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoteInsumoResponseDTO {

    private Long id;
    private LocalDateTime data;
    private String marca;
    private BigDecimal quantidade;
    private LocalDateTime dataDeValidade;
    private String codigoNFe;
    private BigDecimal valorCompra;

    private FornecedorSummaryDTO fornecedor;
    private FuncionarioSummaryDTO funcionario;
    private InsumoSummaryDTO insumo;

    private List<LoteInsumoPerdaSummaryDTO> perdas;
    private List<LoteInsumoRegistroDeProducaoSummaryDTO> registrosDeProducao;

    public LoteInsumoResponseDTO(LoteInsumoEntity loteInsumo) {

        this.id = loteInsumo.getId();
        this.data = loteInsumo.getData();
        this.marca = loteInsumo.getMarca();
        this.quantidade = loteInsumo.getQuantidade();
        this.dataDeValidade = loteInsumo.getDataDeValidade();
        this.codigoNFe = loteInsumo.getCodigoNFe();
        this.valorCompra = loteInsumo.getValorCompra();

        if (loteInsumo.getFornecedor() != null) {
            this.fornecedor =
                    new FornecedorSummaryDTO(loteInsumo.getFornecedor());
        }

        if (loteInsumo.getFuncionario() != null) {
            this.funcionario =
                    new FuncionarioSummaryDTO(loteInsumo.getFuncionario());
        }

        if (loteInsumo.getInsumo() != null) {
            this.insumo =
                    new InsumoSummaryDTO(loteInsumo.getInsumo());
        }

        this.perdas = loteInsumo.getPerdas()
                .stream()
                .map(LoteInsumoPerdaSummaryDTO::new)
                .toList();

        this.registrosDeProducao = loteInsumo.getRegistrosDeProducao()
                .stream()
                .map(LoteInsumoRegistroDeProducaoSummaryDTO::new)
                .toList();
    }
}