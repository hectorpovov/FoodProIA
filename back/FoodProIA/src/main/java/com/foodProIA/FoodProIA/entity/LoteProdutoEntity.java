package com.foodProIA.FoodProIA.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "TB_LOTE_PRODUTO")
@Getter
@Setter
@NoArgsConstructor
public class LoteProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private BigDecimal quantidade;

    private LocalDateTime dataFabricacao;

    private LocalDateTime dataValidade;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoEntity produto;

    @OneToOne
    @JoinColumn(name = "registro_de_producao_id", unique = true)
    private RegistroDeProducaoEntity registroDeProducao;

    @OneToOne(mappedBy = "loteProduto")
    private EstoqueEntity estoque;

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass()!= obj.getClass()) return false;

        LoteProdutoEntity other = (LoteProdutoEntity) obj;
        return Objects.equals(id, other.id);
    }
}