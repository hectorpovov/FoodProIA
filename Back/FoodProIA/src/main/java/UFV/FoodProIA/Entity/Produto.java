package UFV.FoodProIA.Entity;

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
@Table(name = "tb_produto")
@Getter
@Setter
@NoArgsConstructor
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_produto")
    private long IdProduto;

    @Column(nullable = false, length = 100)
    private String nome;

    @OneToMany(mappedBy = "produto")
    @Column(nullable = true)
    private List<Envase> envases;

    @OneToMany(mappedBy = "produto")
    @Column(nullable = true)
    private List<Processamento>  processamentos;
    
    @OneToMany(mappedBy = "produto")
    @Column(nullable = true)
    private List<LoteFinalizado> lotesFinalizados;

    public Produto(String nome){
        this.nome = nome;
    }

}
