package UFV.FoodProIA.Entity;

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
@Table(name = "tb_processamento_lote_materia_prima")
@Getter
@Setter

public class ProcessamentoLoteMateriaPrima {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IdProcessamentoLoteMateriaPrima;

    @ManyToOne
    @JoinColumn(name = "id_processamento")
    private Processamento processamento;

    @ManyToOne
    @JoinColumn(name = "id_lote_materia_prima")
    private LoteMateriaPrima loteMateriaPrima;

    private float quantidadeUtilizada;
}
