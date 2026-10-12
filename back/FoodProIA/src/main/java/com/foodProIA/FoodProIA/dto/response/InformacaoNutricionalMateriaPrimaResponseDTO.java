package com.foodProIA.FoodProIA.dto.response;

import java.math.BigDecimal;

import com.foodProIA.FoodProIA.dto.summary.InformacaoNutricionalSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.MateriaPrimaSummaryDTO;
import com.foodProIA.FoodProIA.entity.InformacaoNutricionalMateriaPrimaEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InformacaoNutricionalMateriaPrimaResponseDTO {

    private Long id;
    private BigDecimal valor;

    private MateriaPrimaSummaryDTO materiaPrima;

    private InformacaoNutricionalSummaryDTO informacaoNutricional;

    public InformacaoNutricionalMateriaPrimaResponseDTO(
            InformacaoNutricionalMateriaPrimaEntity informacao) {

        this.id = informacao.getId();
        this.valor = informacao.getValor();

        if (informacao.getMateriaPrima() != null) {
            this.materiaPrima =
                    new MateriaPrimaSummaryDTO(
                            informacao.getMateriaPrima());
        }

        if (informacao.getInformacaoNutricional() != null) {
            this.informacaoNutricional =
                    new InformacaoNutricionalSummaryDTO(
                            informacao.getInformacaoNutricional());
        }
    }
}