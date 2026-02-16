package UFV.FoodProIA.Entity;

import java.util.ArrayList;
import java.util.List;

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
@Table(name = "tb_materia_prima")
@Getter
@Setter
@NoArgsConstructor
public class MateriaPrima {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "pk_id_materia_prima")
    private int IdMateriaPrima;

    @Column(nullable = false, length = 100)
    private String nome;

    @OneToMany(mappedBy = "materiaPrima")
    @Column(nullable = true)
    private List<LoteMateriaPrima> lotesMateriaPrima = new ArrayList<>();

    public MateriaPrima(String nome){
        this.nome = nome;
    }
}
