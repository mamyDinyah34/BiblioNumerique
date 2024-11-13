package com.hasnreziga.admin.lecteur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LecteurImpl extends JpaRepository<Lecteur, Integer> {
    Lecteur findByEmail(String email);
    List<Lecteur> findByNomContainingIgnoreCase(String nom);
    List<Lecteur> findAllByOrderByIdAsc();
    List<Lecteur> findAllByOrderByIdDesc();
    List<Lecteur> findAllByOrderByNomAsc();
    List<Lecteur> findAllByOrderByNomDesc();

}
