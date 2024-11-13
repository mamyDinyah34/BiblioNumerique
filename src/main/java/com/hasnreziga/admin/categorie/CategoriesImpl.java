package com.hasnreziga.admin.categorie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesImpl extends JpaRepository<Categories, Integer>{
    List<Categories> findByNomContainingIgnoreCase(String nom);
    List<Categories> findAllByOrderByNomAsc();
    List<Categories> findAllByOrderByNomDesc();
    List<Categories> findAllByOrderByIdDesc();
    List<Categories> findAllByOrderByIdAsc();
}
