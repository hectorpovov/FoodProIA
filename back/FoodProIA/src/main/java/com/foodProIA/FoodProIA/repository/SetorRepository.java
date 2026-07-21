package com.foodProIA.FoodProIA.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodProIA.FoodProIA.entity.SetorEntity;

public interface SetorRepository extends JpaRepository<SetorEntity,Long>{

    boolean existsByNomeAndEmpresaId(String nome, Long empresaId);

}
