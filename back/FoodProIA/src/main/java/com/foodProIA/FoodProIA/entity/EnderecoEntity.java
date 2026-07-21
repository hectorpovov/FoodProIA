package com.foodProIA.FoodProIA.entity;


import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter

@Entity
@Table(name = "TB_ENDERECO")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String complemento;

    @Column(nullable = false)
    @Setter
    private String numero;

    @Column(nullable = false)
    @Setter
    private String rua;

    @Column(nullable = false)
    @Setter
    private String bairro;

    @Column(nullable = false)
    @Setter
    private String cidade;

    @Column(nullable = false)
    @Setter
    private String estado;

    @Column(nullable = false)
    @Setter
    private String pais;

    @Column(nullable = false)
    @Setter
    private String cep;

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass()!= obj.getClass()) return false;

        EnderecoEntity other = (EnderecoEntity) obj;
        return Objects.equals(id, other.id);
    }
}
