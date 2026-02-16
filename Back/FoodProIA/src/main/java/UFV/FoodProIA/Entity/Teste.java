package UFV.FoodProIA.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_teste")
@Getter
@Setter
@NoArgsConstructor
public class Teste {
    
    @Id
    @GeneratedValue
    @Column(name = "pk_id_teste")
    private long IdTeste;

    @Column(nullable = false, length = 100)
    private String nome;

    @Lob
    @Column(nullable = false)
    private String resultado;

    @ManyToOne
    @JoinColumn(name = "id_lote_materia_prima", nullable = false)
    private LoteMateriaPrima loteMateriaPrima;

    public Teste(String nome, String resultado, LoteMateriaPrima loteMateriaPrima){
        this.nome = nome;
        this.resultado = resultado;
        this.loteMateriaPrima = loteMateriaPrima;
    }

}
