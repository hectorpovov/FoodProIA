package UFV.FoodProIA.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import UFV.FoodProIA.Endereco;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_expedicao")
@Getter
@Setter
@NoArgsConstructor
public class Expedicao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_expedicao")
    private long IdExpedicao;

    @Column(nullable = false)
    private LocalDateTime data;

    @Column(nullable = false)
    private Endereco local;

    @OneToMany(mappedBy = "expedicao")
    @Column(nullable = false)
    private List<LoteFinalizado> lotesFinalizados;

    public Expedicao(LocalDateTime data, Endereco local, List<LoteFinalizado> lotesFinalizados){
        this.data = data;
        this.local = local;
        this.lotesFinalizados = new ArrayList<>(Objects.requireNonNull(lotesFinalizados));
    }
}
