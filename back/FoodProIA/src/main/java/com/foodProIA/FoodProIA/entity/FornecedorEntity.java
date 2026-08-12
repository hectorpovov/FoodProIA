package com.foodProIA.FoodProIA.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.foodProIA.FoodProIA.enums.TipoInsumo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_FORNECEDOR")
@Getter
@NoArgsConstructor
public class FornecedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String nome;

    @Setter
    private String registroEmpresa;

    @Setter
    @Column(nullable = false)    
    private String telefone;

    @Setter
    @Column(nullable = false, unique = true)
    private String email;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoInsumo tipoInsumo;

    @Setter
    @Column(nullable = false)
    private boolean status;


    @Setter
    @Lob
    private byte[] certidaoDeNegativas;
    
    @Setter
    @Lob
    private byte[] licensasDeFuncionamento;
    
    @Setter
    @ElementCollection
    @CollectionTable(
        name = "TB_FORNECEDOR_CERTIFICACAO",
        joinColumns = @JoinColumn(name = "fornecedor_id")
    )
    @Lob
    @Column(name = "arquivo")
    private List<byte[]> certificacoesDeQualidade = new ArrayList<>();

    @OneToMany(mappedBy = "fornecedor",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<FraudeEntity> fraudes = new ArrayList<>();

    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", unique = true, nullable = false)
    private EnderecoEntity endereco;

    @OneToMany(mappedBy = "fornecedor",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<RotaEntity> rotas = new ArrayList<>();

    public void adicionarRota(RotaEntity rota) {
        rotas.add(rota);
        rota.setFornecedor(this);
    }

    public void removerRota(RotaEntity rota) {
        rotas.remove(rota);
        rota.setFornecedor(null);
    }

    public void adicionarFraude(FraudeEntity fraude) {
        fraudes.add(fraude);
        fraude.setFornecedor(this);
    }

    public void removerFraude(FraudeEntity fraude) {
        fraudes.remove(fraude);
        fraude.setFornecedor(null);
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

        FornecedorEntity other = (FornecedorEntity) obj;
        return Objects.equals(id, other.id);
    }



}
