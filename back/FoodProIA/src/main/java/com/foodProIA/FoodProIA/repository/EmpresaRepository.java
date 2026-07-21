package com.foodProIA.FoodProIA.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodProIA.FoodProIA.entity.EmpresaEntity;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity,Long> {

    boolean existsByEmail(String email);

    boolean existsByCnpj(String cnpj);

    boolean existsByRazaoSocial(String razaoSocial);

}
