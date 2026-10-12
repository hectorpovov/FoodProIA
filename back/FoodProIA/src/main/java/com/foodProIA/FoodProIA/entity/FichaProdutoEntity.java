package com.foodProIA.FoodProIA.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_FICHA_PRODUTO")
@Getter
@Setter
@NoArgsConstructor
public class FichaProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String nomeTecnico;

    private LocalDateTime dataCriacao;

    private String instrucaoDeManipulacao;

    private String tipoDeEmbalagem;

    private Boolean status;

    @ManyToMany
    @JoinTable(
        name = "TB_FICHA_PRODUTO_MATERIA_PRIMA",
        joinColumns = @JoinColumn(name = "ficha_produto_id"),
        inverseJoinColumns = @JoinColumn(name = "materia_prima_id")
    )
    private List<MateriaPrimaEntity> materiasPrimas = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "produto_id", nullable = false, unique = true)
    private ProdutoEntity produto;

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass()!= obj.getClass()) return false;

        FichaProdutoEntity other = (FichaProdutoEntity) obj;
        return Objects.equals(id, other.id);
    }
}