package com.foodProIA.FoodProIA.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodProIA.FoodProIA.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Long > {

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

}
