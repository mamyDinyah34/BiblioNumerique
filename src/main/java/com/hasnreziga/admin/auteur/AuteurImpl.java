package com.hasnreziga.admin.auteur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuteurImpl extends JpaRepository<Auteur, Integer> {
    Auteur findByEmail(String email);
    List<Auteur> findByNomContainingIgnoreCase(String nom);
    List<Auteur> findAllByOrderByIdAsc();
    List<Auteur> findAllByOrderByIdDesc();
    List<Auteur> findAllByOrderByNomAsc();
    List<Auteur> findAllByOrderByNomDesc();
    List<Auteur> findAllByOrderByPseudoAsc();
    List<Auteur> findAllByOrderByPseudoDesc();
}
