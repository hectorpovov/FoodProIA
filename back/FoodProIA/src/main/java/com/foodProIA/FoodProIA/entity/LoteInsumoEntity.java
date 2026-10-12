package com.foodProIA.FoodProIA.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_LOTE_INSUMO")
@Getter
@Setter
@NoArgsConstructor
public class LoteInsumoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private LocalDateTime data;

    private String marca;

    private BigDecimal quantidade;

    private LocalDateTime dataDeValidade;

    private String codigoNFe;

    private BigDecimal valorCompra;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private FornecedorEntity fornecedor;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private FuncionarioEntity funcionario;

    @ManyToOne
    @JoinColumn(name = "insumo_id", nullable = false)
    private InsumoEntity insumo;

    @OneToMany(mappedBy = "loteInsumo")
    private List<LoteInsumoPerdaEntity> perdas = new ArrayList<>();

    @OneToMany(mappedBy = "loteInsumo")
    private List<LoteInsumoRegistroDeProducaoEntity> registrosDeProducao = new ArrayList<>();

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass()!= obj.getClass()) return false;

        LoteInsumoEntity other = (LoteInsumoEntity) obj;
        return Objects.equals(id, other.id);
    }
}