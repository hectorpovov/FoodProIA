package com.foodProIA.FoodProIA.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_LOTE_MATERIA_PRIMA")
@Getter
@Setter
@NoArgsConstructor
public class LoteMateriaPrimaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private LocalDate data;

    private String marca;   

    private BigDecimal quantidade;

    private String codigoNFe;

    private BigDecimal valorCompra;

    private LocalDate dataValidade;

    @ManyToOne
    @JoinColumn(name = "materia_prima_id", nullable = false)
    private MateriaPrimaEntity materiaPrima;

    @OneToMany(mappedBy = "loteMateriaPrima")
    private List<TesteDeQualidadeEntity> testes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private FornecedorEntity fornecedor;

    @ManyToOne
    @JoinColumn(name = "recebedor_id")
    private FuncionarioEntity recebedor;

    @OneToMany(mappedBy = "loteMateriaPrima")
    private List<LoteMateriaPrimaRegistroDeProducaoEntity> registrosDeProducao = new ArrayList<>();

    @OneToMany(mappedBy = "loteMateriaPrima")
    private List<LoteMateriaPrimaPerdaEntity> perdas = new ArrayList<>();

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass()!= obj.getClass()) return false;

        LoteMateriaPrimaEntity other = (LoteMateriaPrimaEntity) obj;
        return Objects.equals(id, other.id);
    }
}