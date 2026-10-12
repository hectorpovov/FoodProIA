package com.foodProIA.FoodProIA.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "TB_ESTOQUE")
@Getter
@Setter
@NoArgsConstructor
public class EstoqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String SKU;

    private String classificacaoDoItem;

    private LocalDateTime dataChegada;

    private Duration tempoParado;

    private BigDecimal quantidadeProduto;

    private Integer diasParado;

    private Integer mesesParado;

    private String destino;

    private BigDecimal valorDoProduto;

    private BigDecimal quantidadeSaida;

    @OneToOne
    @JoinColumn(name = "lote_produto_id", nullable = false, unique = true)
    private LoteProdutoEntity loteProduto;

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass()!= obj.getClass()) return false;

        EstoqueEntity other = (EstoqueEntity) obj;
        return Objects.equals(id, other.id);
    }
}
