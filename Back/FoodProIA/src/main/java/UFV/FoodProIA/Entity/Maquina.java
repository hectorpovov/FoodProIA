package UFV.FoodProIA.Entity;

import java.util.ArrayList;
import java.util.List;

import UFV.FoodProIA.EstadoMaquina;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_maquina")
@Getter
@Setter
@NoArgsConstructor
public class Maquina {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_maquina")
    private long idMaquina;

    @Column(nullable = false, length = 100)
    private String nome;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoMaquina estado = EstadoMaquina.PARADA; 

    @OneToMany(mappedBy = "maquina")
    @Column(nullable = true)
    private List<ProcessamentoMaquina> processamentos = new ArrayList<>();

    @ManyToMany(mappedBy = "maquinas")
    @Column(nullable = true)
    private List<Envase> envases = new ArrayList<>();

    public Maquina(String nome){
        this.nome = nome;
     }


    

}
