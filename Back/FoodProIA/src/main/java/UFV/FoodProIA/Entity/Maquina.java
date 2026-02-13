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
@Table(name = "tb_maquina")
@Getter
@Setter
public class Maquina {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMaquina;
    private String nome;
    private String estado; //EM ATIVIDADE, PARADA, ESPERANDO LAVAGEM, ESPERANDO MANUTENCAO...

    @OneToMany(mappedBy = "maquina")
    private List<ProcessamentoMaquina> processamentos;

    

}
