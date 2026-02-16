package UFV.FoodProIA.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_lote_finalizado")
@Getter
@Setter
@NoArgsConstructor
public class LoteFinalizado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_lote_finalizado")
    private long IdLoteFinalizado;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @OneToOne
    @JoinColumn(name = "id_envase", unique = true, nullable = false)
    private Envase envase;

    @OneToOne
    @JoinColumn(name = "id_processamento", unique = true, nullable = false)
    private Processamento processamento;

    @ManyToOne
    @JoinColumn(name = "id_expedicao", nullable = true)
    private Expedicao expedicao;
    
    @Column(nullable = false)
    private float quantidade;
    
    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean vendido;

    @Column(nullable = false)
    private String formaDeArmazenamento;

    public LoteFinalizado(Produto produto, Envase envase, Processamento processamento, float quantidade, String formaDeArmazenamento){

        this.produto = produto;
        this.envase = envase;
        this.processamento = processamento;
        this.quantidade =quantidade;
        this.formaDeArmazenamento = formaDeArmazenamento;
    
    }

}
