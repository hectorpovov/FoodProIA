package UFV.FoodProIA.Entity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
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
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_processamento")
@Getter
@Setter
@NoArgsConstructor
public class Processamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_processamento")
    private long IdProcessamento;    

    @Column(nullable = false)
    private LocalDateTime data;

    @Column(nullable = true)
    private float quantidadeProduzida;

    @Column(nullable = true)
    private float quantidadeDescartada;
    
    @Column(nullable = true)
    private LocalTime tempoProduzindo;
    
    @Column(nullable = true)
    private LocalTime tempoParado;
    
    @Column(nullable = true)
    private LocalTime tempoTotal;

    @ManyToMany(mappedBy = "processamentos")
    @Column(nullable = false)
    private List<Etapa> etapas = new ArrayList<>();

    @OneToMany(mappedBy = "processamentos")//relacionamento n pra n, porém com atributos
    @Column(nullable = true)
    private List<ProcessamentoMaquina> maquinas = new ArrayList<>();

    @OneToMany(mappedBy = "processamento")//relacionamento n pra n, porém com atributos
    @Column(nullable = false)
    private List<ProcessamentoLoteMateriaPrima> lotesMateriaPrima = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false) 
    private Produto produto;

    @OneToOne(mappedBy = "processamento")
    @Column(nullable = true)
    private LoteFinalizado loteFinalizado;

    @OneToOne(mappedBy = "processamento")
    @Column(nullable = true)
    private Envase envase;  

    public Processamento( LocalDateTime data, Etapa etapa,List<LoteMateriaPrima> LotesMateriaPrima, List<Float> quantidades, Produto produto){

        this.data = data;
        this.produto = produto;

        adicionaEtapa(etapa);

        for(int i =0; i< lotesMateriaPrima.size();i++){
            adicionaMateriaPrima(LotesMateriaPrima.get(i), quantidades.get(i));
        }

    }


    public void adicionaMateriaPrima(LoteMateriaPrima materiaPrima, float quantidade){


        ProcessamentoLoteMateriaPrima relacao = new ProcessamentoLoteMateriaPrima(this, materiaPrima, quantidade);


        this.lotesMateriaPrima.add(relacao);


    }

    public void adicionaEtapa(Etapa etapa){
        this.etapas.add(etapa);
    }

    
}
