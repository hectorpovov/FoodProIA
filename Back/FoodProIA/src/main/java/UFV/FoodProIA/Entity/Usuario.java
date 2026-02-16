package UFV.FoodProIA.Entity;

import UFV.FoodProIA.Funcao;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Entity transforma uma classe em uma entidade do BD
@Entity
@Table(name = "tb_cadastro_usuario")
@Getter
@Setter
@NoArgsConstructor
public class Usuario {
    
    private String nome;
    private String email;
    private String CPF;
    private Funcao funcao;


    public Usuario(String nome, String email, String CPF, Funcao funcao){
        this.nome = nome;
        this.email = email;
        this.CPF = CPF;
        this.funcao = funcao;
    }
 

}
