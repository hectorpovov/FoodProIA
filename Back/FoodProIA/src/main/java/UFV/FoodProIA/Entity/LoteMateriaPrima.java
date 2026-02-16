package UFV.FoodProIA.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_lote_materia_prima")
@Getter
@Setter
@NoArgsConstructor
public class LoteMateriaPrima {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_lote_materia_prima")
    private long IdLoteMateriaPrima;

    @Column(nullable = false)
    private LocalDateTime dataChegada;
    
    @Column(nullable = false, length = 100)
    private String local;

    @Column(nullable = false, length = 100)
    private String responsavelPelaEntrega;

    @Column(nullable = false)
    private float quantidadeRecebida;
    
    @Column(nullable = false)
    private float quantidadeAtual;


    @ManyToOne
    @JoinColumn(name = "id_materia_prima", nullable = false)
    private MateriaPrima materiaPrima;

    @OneToMany(mappedBy = "loteMateriaPrima")
    @Column(nullable = true)
    private List<Teste> testes = new ArrayList<>();
    
    @OneToMany(mappedBy = "loteMateriaPrima")
    @Column(nullable = true)
    private List<ProcessamentoLoteMateriaPrima> processamentos = new ArrayList<>();

    public LoteMateriaPrima(LocalDateTime dataChegada, String local, 
                            String responsavelPelaEntrega, float quantidadeRecebida, 
                            MateriaPrima materiaPrima){
                                
        this.dataChegada = dataChegada;
        this.local = local;
        this.responsavelPelaEntrega = responsavelPelaEntrega;
        this.quantidadeRecebida = quantidadeRecebida;
        this.quantidadeAtual = quantidadeRecebida;
        this.materiaPrima = materiaPrima;
    }

}
