package UFV.FoodProIA.Entity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_processamento")
@Getter
@Setter

public class Processamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IdProcessamento;    

    
    private LocalDateTime data;
    private float quantidadeProduzida;
    private float quantidadeDescartada;
    private LocalTime tempoProduzindo;
    private LocalTime tempoParado;
    private LocalTime tempoTotal;

    @ManyToMany(mappedBy = "processamentos")
    private List<Etapa> etapas;

    @OneToMany(mappedBy = "processamentos")//relacionamento n pra n, porém com atributos
    private List<ProcessamentoMaquina> maquinas;

    @OneToMany(mappedBy = "processamento")//relacionamento n pra n, porém com atributos
    private List<ProcessamentoLoteMateriaPrima> lotesMateriaPrima;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @OneToOne(mappedBy = "processamento")
    private LoteFinalizado loteFinalizado;

    
}
