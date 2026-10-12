package com.foodProIA.FoodProIA.dto.response;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import com.foodProIA.FoodProIA.dto.summary.FuncionarioSummaryDTO;
import com.foodProIA.FoodProIA.dto.summary.PlanejamentoManutencaoSummaryDTO;
import com.foodProIA.FoodProIA.entity.RegistroManutencaoEntity;
import com.foodProIA.FoodProIA.enums.TipoManutencao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistroManutencaoResponseDTO {

    private Long id;
    private LocalDateTime data;
    private String numeroDeSerieEquipamento;
    private TipoManutencao tipoDeManutencao;
    private Duration tempoParado;
    private Integer diasParado;
    private Integer mesesParado;
    private LocalDateTime dataDeLiberacao;
    private String comentario;

    private List<FuncionarioSummaryDTO> funcionarios;

    private PlanejamentoManutencaoSummaryDTO planejamentoManutencao;

    public RegistroManutencaoResponseDTO(
            RegistroManutencaoEntity registro) {

        this.id = registro.getId();
        this.data = registro.getData();
        this.numeroDeSerieEquipamento =
                registro.getNumeroDeSerieEquipamento();
        this.tipoDeManutencao =
                registro.getTipoDeManutencao();
        this.tempoParado =
                registro.getTempoParado();
        this.diasParado =
                registro.getDiasParado();
        this.mesesParado =
                registro.getMesesParado();
        this.dataDeLiberacao =
                registro.getDataDeLiberacao();
        this.comentario =
                registro.getComentario();

        this.funcionarios = registro.getFuncionarios()
                .stream()
                .map(FuncionarioSummaryDTO::new)
                .toList();

        if (registro.getPlanejamentoManutencao() != null) {
            this.planejamentoManutencao =
                    new PlanejamentoManutencaoSummaryDTO(
                            registro.getPlanejamentoManutencao());
        }
    }
}