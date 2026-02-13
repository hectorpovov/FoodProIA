package UFV.FoodProIA.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_teste")
@Getter
@Setter

public class Teste {
    
    @Id
    @GeneratedValue
    private long IdTeste;

    private String nome;
    private String resultado;

    @ManyToOne
    @JoinColumn(name = "id_lote_materia_prima")
    private LoteMateriaPrima loteMateriaPrima;

}
