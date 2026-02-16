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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_etapaProcessamento")
@Getter
@Setter
@NoArgsConstructor
public class Etapa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pf_id_etapa")
    private long idEtapa;

    @Column(nullable = false)
    private LocalDateTime dataInicio;
    @Column(nullable = true)
    private LocalDateTime dataFim;
    @Column(nullable = false,
            length = 100)
    private String nome;

    @ManyToMany
    @JoinTable(
        name = "processamento_etapa",
        joinColumns = @JoinColumn(name = "etapa_id"),
        inverseJoinColumns = @JoinColumn(name = "processamento_id")
    )
    private List<Processamento> processamentos = new ArrayList<>();


    public Etapa(LocalDateTime dataInicio, LocalDateTime dataFim, String nome){
        this.dataFim = dataFim;
        this.dataInicio = dataInicio;
        this.nome = nome;
    }

}
