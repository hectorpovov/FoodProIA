package UFV.FoodProIA.Entity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_envase")
@Getter
@Setter
@NoArgsConstructor
public class Envase {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_envase")
    private long IdEnvase;


    @Column(nullable = true)    
    private LocalTime tempoGasto;

    @Column(nullable = true)
    private float descarteEmbalagem;

    @Column(nullable = true)
    private float desperdicio;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean testado;


    @OneToOne
    @JoinColumn(name = "fk_id_processamento",unique = true, nullable = false)
    private Processamento processamento;
    
    @ManyToOne
    @JoinColumn(name = "fk_id_produto", nullable = false)
    private Produto produto;

    @OneToOne(mappedBy = "envase")
    @Column(nullable = true)
    private LoteFinalizado loteFinalizado;

    @ManyToMany
    @JoinTable(name = "envase_maquina",
            joinColumns = @JoinColumn(name = "id_envase"),
            inverseJoinColumns = @JoinColumn(name = "id_maquina")
    )
    private List<Maquina> maquinas = new ArrayList<>();

    public Envase(Produto produto, Processamento processamento){
        this.produto = produto;
        this.processamento = processamento;
    }


    

}
