package UFV.FoodProIA.Entity;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_envase")
@Getter
@Setter
public class Envase {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IdEnvase;


    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @OneToOne(mappedBy = "envase")
    private LoteFinalizado loteFinalizado;

    private LocalTime tempoGasto;
    private float descarteEmbalagem;
    private float desperdicio;
    private boolean testado;




}
