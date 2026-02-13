package UFV.FoodProIA.Entity;

import java.time.LocalDateTime;
import java.util.List;

import UFV.FoodProIA.Endereco;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_expedicao")
@Getter
@Setter
public class Expedicao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IdExpedicao;

    private LocalDateTime data;
    private Endereco local;

    @OneToMany(mappedBy = "expedicao")
    private List<LoteFinalizado> lotesFinalizados;
}
