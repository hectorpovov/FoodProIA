package UFV.FoodProIA.Entity;

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
@Table(name = "processamento_lote_materia_prima")
@Getter
@Setter
@NoArgsConstructor
public class ProcessamentoLoteMateriaPrima {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_processamento_lote_materia_prima")
    private long IdProcessamentoLoteMateriaPrima;

    @ManyToOne
    @JoinColumn(name = "id_processamento", nullable = false)
    private Processamento processamento;

    @ManyToOne
    @JoinColumn(name = "id_lote_materia_prima", nullable = false)
    private LoteMateriaPrima loteMateriaPrima;

    @Column(nullable = false)
    private float quantidadeUtilizada;

    public ProcessamentoLoteMateriaPrima(Processamento processamento, LoteMateriaPrima loteMateriaPrima, float quantidadeUtilizada){
        this.processamento = processamento;
        this.loteMateriaPrima = loteMateriaPrima;
        this.quantidadeUtilizada = quantidadeUtilizada;
    }
}
