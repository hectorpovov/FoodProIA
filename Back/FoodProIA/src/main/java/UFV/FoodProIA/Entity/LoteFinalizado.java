package UFV.FoodProIA.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_lote_finalizado")
@Getter
@Setter

public class LoteFinalizado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IdLoteFinalizado;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @OneToOne
    @JoinColumn(name = "id_envase", unique = true)
    private Envase envase;

    @OneToOne
    @JoinColumn(name = "id_processamento", unique = true)
    private Processamento processamento;

    @ManyToOne
    @JoinColumn(name = "id_expedicao")
    private Expedicao expedicao;

    private float quantidade;
    private boolean vendido;
    private String formaDeArmazenamento;


}
