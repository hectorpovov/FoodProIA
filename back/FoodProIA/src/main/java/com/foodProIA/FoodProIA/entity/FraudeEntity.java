package com.foodProIA.FoodProIA.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_FRAUDE")
@Getter
@NoArgsConstructor
public class FraudeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private LocalDateTime dataEHorario;

    @Setter
    @Column(nullable = false)
    private String classificacao;

    @Setter
    @Column(nullable = false)
    private String descricao;

    @Setter 
    @ManyToOne
    @JoinColumn(name = "teste_qualidade_id")
    private TesteDeQualidadeEntity testeDeQualidade;

    @Setter
    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private FornecedorEntity fornecedor;

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass()!= obj.getClass()) return false;

        FraudeEntity other = (FraudeEntity) obj;
        return Objects.equals(id, other.id);
    }

}
