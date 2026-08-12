package com.foodProIA.FoodProIA.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_TESTE_DE_QUALIDADE")
@Getter
@NoArgsConstructor
public class TesteDeQualidadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private LocalDateTime dataEHorario;

    @Setter
    @ManyToOne
    @JoinColumn(name = "modelo_teste_id")
    private ModeloTesteEntity modelo;

    @ManyToMany(mappedBy = "testesDeQualidade")
    private List<ParametroEntity> parametros = new ArrayList<>();

    @OneToMany(mappedBy = "testeDeQualidade",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<FraudeEntity> fraudes = new ArrayList<>();

    public void adicionarFraude(FraudeEntity fraude) {
        fraudes.add(fraude);
        fraude.setTesteDeQualidade(this);
    }

    public void removerFraude(FraudeEntity fraude) {
        fraudes.remove(fraude);
        fraude.setTesteDeQualidade(null);
    }

    public void adicionarParametro(ParametroEntity parametro) {
        parametros.add(parametro);
        parametro.getTestesDeQualidade().add(this);
    }

    public void removerParametro(ParametroEntity parametro) {
        parametros.remove(parametro);
        parametro.getTestesDeQualidade().remove(this);
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

        TesteDeQualidadeEntity other = (TesteDeQualidadeEntity) obj;
        return Objects.equals(id, other.id);
    }


}
