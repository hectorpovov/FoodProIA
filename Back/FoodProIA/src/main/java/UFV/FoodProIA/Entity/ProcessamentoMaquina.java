package UFV.FoodProIA.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "processamento_maquina")
@Getter
@Setter
public class ProcessamentoMaquina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IdProcessamentoMaquina;

    @ManyToOne
    @JoinColumn(name = "id_processamento")
    private Processamento processamento;

    @ManyToOne
    @JoinColumn(name = "id_maquina")
    private Maquina maquina;

    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    public ProcessamentoMaquina(){}

    public ProcessamentoMaquina( Processamento processamento, Maquina maquina,
                                 LocalDateTime dataInicio, LocalDateTime dataFim){

        
        this.processamento = processamento;
        this.maquina = maquina;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;

    }

}