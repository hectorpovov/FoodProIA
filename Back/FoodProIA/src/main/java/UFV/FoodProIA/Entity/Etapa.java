package UFV.FoodProIA.Entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_etapaProcessamento")
@Getter
@Setter
public class Etapa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEtapa;

    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String nome;

    @ManyToMany
    @JoinTable(
        name = "processamento_etapa",
        joinColumns = @JoinColumn(name = "etapa_id"),
        inverseJoinColumns = @JoinColumn(name = "processamento_id")
    )
    private List<Processamento> processamentos;

    



    public Etapa(){

    }

    public Etapa(LocalDateTime dataInicio, LocalDateTime dataFim, String nome){
        this.dataFim = dataFim;
        this.dataInicio = dataInicio;
        this.nome = nome;
    }

}
