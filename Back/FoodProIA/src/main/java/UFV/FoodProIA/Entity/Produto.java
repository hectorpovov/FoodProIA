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
@Table(name = "tb_produto")
@Getter
@Setter
public class Produto {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IdProduto;

    private String nome;


    @OneToMany(mappedBy = "produto")
    private List<Envase> envases;

    @OneToMany(mappedBy = "produto")
    private List<Processamento>  processamentos;
    
    @OneToMany(mappedBy = "produto")
    private List<LoteFinalizado> lotesFinalizados;


}
