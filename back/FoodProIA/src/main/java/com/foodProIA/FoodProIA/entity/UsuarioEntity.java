package com.foodProIA.FoodProIA.entity;

import java.time.LocalDate;
import java.util.Objects;

import com.foodProIA.FoodProIA.enums.Papel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_USUARIO")
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String nome;

    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    private String cpf;

    @Column(nullable = false)
    @Getter
    @Setter
    private String telefone;

    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    private String email;

    @Column(nullable = false)
    @Getter
    @Setter
    private LocalDate dataNascimento;

    @Column(nullable = false)
    @Getter
    @Setter
    private boolean ativo;

    @Column(nullable = false)
    @Getter
    @Setter
    private Papel papel;

    @Column(nullable = false)
    @Getter
    @Setter
    private String hashSenha;

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass()!= obj.getClass()) return false;

        UsuarioEntity other = (UsuarioEntity) obj;
        return Objects.equals(id, other.id);
    }

}
