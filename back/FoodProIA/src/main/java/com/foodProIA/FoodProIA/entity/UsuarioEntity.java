package com.foodProIA.FoodProIA.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.foodProIA.FoodProIA.enums.UserRole;

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
public class UsuarioEntity implements UserDetails{

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
    private UserRole role;

    @Column(nullable = false)
    @Getter
    @Setter
    private String password;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), 
                                                       new SimpleGrantedAuthority("ROLE_GESTOR"), 
                                                       new SimpleGrantedAuthority("ROLE_OPERADOR"));

        else if (this.role == UserRole.GESTOR) return List.of(new SimpleGrantedAuthority("ROLE_GESTOR"), 
                                                              new SimpleGrantedAuthority("ROLE_OPERADOR"));
        
        else return List.of(new SimpleGrantedAuthority("ROLE_OPERADOR"));
    }

    @Override
    public String getUsername(){
        return email;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }

}
