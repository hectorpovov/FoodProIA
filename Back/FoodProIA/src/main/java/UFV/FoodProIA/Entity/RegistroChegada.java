package UFV.FoodProIA.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.*;

import UFV.FoodProIA.Endereco;

@Entity
@Getter
@Setter

public class RegistroChegada {

    @Column(nullable = false)
    private LocalDateTime data;
    private Endereco local;
    private float quantidade;
    private Usuario recebedor;
    private String responsavelEntrega;

    public RegistroChegada(){

    }

    public RegistroChegada(LocalDateTime data, Endereco local, 
                                float quantidade, Usuario recebedor, 
                                String responsavelEntrega){

        this.data = data;
        this.local = local;
        this.quantidade = quantidade;
        this.recebedor = recebedor;
        this.responsavelEntrega = responsavelEntrega;
    }


    
}