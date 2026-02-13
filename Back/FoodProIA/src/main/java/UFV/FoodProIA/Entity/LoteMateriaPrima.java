package UFV.FoodProIA.Entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_lote_materia_prima")
@Getter
@Setter

public class LoteMateriaPrima {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IdLoteMateriaPrima;

    private LocalDateTime dataChegada;
    private String local;
    private String responsavelPelaEntrega;
    private float quantidadeRecebida;
    private float quantidadeAtual;


    @ManyToOne
    @JoinColumn(name = "id_materia_prima")
    private MateriaPrima materiaPrima;

    @OneToMany(mappedBy = "loteMateriaPrima")
    private List<Teste> testes;
    
    @OneToMany(mappedBy = "loteMateriaPrima")
    private List<ProcessamentoLoteMateriaPrima> processamentos;

}
