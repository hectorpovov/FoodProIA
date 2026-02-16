package UFV.FoodProIA.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "processamento_maquina")
@Getter
@Setter
@NoArgsConstructor
public class ProcessamentoMaquina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_processamento_maquina")
    private long IdProcessamentoMaquina;

    @ManyToOne
    @JoinColumn(name = "id_processamento", nullable = false)
    private Processamento processamento;

    @ManyToOne
    @JoinColumn(name = "id_maquina", nullable = false)
    private Maquina maquina;

    @Column(nullable = false)
    private LocalDateTime dataInicio;

    @Column(nullable = false)
    private LocalDateTime dataFim;


    public ProcessamentoMaquina( Processamento processamento, Maquina maquina,
                                 LocalDateTime dataInicio, LocalDateTime dataFim){

        
        this.processamento = processamento;
        this.maquina = maquina;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;

    }

}