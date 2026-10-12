package com.foodProIA.FoodProIA.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_PRODUTO")
@Getter
@Setter
@NoArgsConstructor
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String nomeComercial;

    private String descricao;

    private String categoria;

    private String unidadeDeMedida;

    @ManyToMany
    @JoinTable(
        name = "TB_PRODUTO_PARAMETRO",
        joinColumns = @JoinColumn(name = "produto_id"),
        inverseJoinColumns = @JoinColumn(name = "tipo_parametro_id")
    )
    private List<TipoParametroEntity> parametros = new ArrayList<>();

    private Integer anosValidade;

    private Integer mesesValidade;

    private Integer diasValidade;

    @OneToOne(mappedBy = "produto")
    private FichaProdutoEntity fichaProduto;

    @OneToMany(mappedBy = "produto")
    private List<RegistroDeProducaoEntity> registrosDeProducao = new ArrayList<>(); 

    @OneToMany(mappedBy = "produto")
    private List<InformacaoNutricionalProdutoEntity> informacoesNutricionais = new ArrayList<>();

    @OneToMany(mappedBy = "produto")
    private List<PlanejamentoDeProducaoEntity> planejamentos = new ArrayList<>();

    @OneToMany(mappedBy = "produto")
    private List<LoteProdutoEntity> lotes = new ArrayList<>();

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass()!= obj.getClass()) return false;

        ProdutoEntity other = (ProdutoEntity) obj;
        return Objects.equals(id, other.id);
    }
}
