package UFV.FoodProIA.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_materia_prima")
@Getter
@Setter

public class MateriaPrima {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private int IdMateriaPrima;

    private String nome;

    @OneToMany(mappedBy = "materiaPrima")
    private List<LoteMateriaPrima> lotesMateriaPrima;
}
