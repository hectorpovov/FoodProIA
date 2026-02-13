package UFV.FoodProIA;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Endereco {
    
    private String estado;
    private int CEP;
    private String cidade;
    private String bairro;
    private String rua;
    private int numero;
    private String complemento;

    public Endereco(){

    }

    public Endereco(String estado, int CEP, 
                    String cidade, String bairro,
                    String rua, int numero,
                    String complemento ){
        
        this.CEP = CEP;
        this.bairro = bairro;
        this.cidade = cidade;
        this.complemento = complemento;
        this.estado = estado;
        this.numero = numero;
        this.rua = rua;

    }

}
