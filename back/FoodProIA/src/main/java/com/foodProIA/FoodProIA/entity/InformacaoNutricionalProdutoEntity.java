package com.foodProIA.FoodProIA.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "TB_INFORMACAO_NUTRICIONAL_PRODUTO")
@Getter
@Setter
@NoArgsConstructor
public class InformacaoNutricionalProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoEntity produto;

    @ManyToOne
    @JoinColumn(name = "informacao_nutricional_id", nullable = false)
    private InformacaoNutricionalEntity informacaoNutricional;

    public InformacaoNutricionalProdutoEntity(ProdutoEntity produto,
                                                InformacaoNutricionalEntity informacaoNutricional,
                                                BigDecimal valor
    ){
        this.produto = produto;
        this.informacaoNutricional = informacaoNutricional;
        this.valor = valor;

        produto.getInformacoesNutricionais().add(this);
        informacaoNutricional.getProdutos().add(this);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass()!= obj.getClass()) return false;

        InformacaoNutricionalProdutoEntity other = (InformacaoNutricionalProdutoEntity) obj;
        return Objects.equals(id, other.id);
    }
}