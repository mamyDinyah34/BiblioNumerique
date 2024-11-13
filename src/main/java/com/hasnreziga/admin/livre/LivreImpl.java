package com.hasnreziga.admin.livre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivreImpl extends JpaRepository<Livre, Integer> {
    List<Livre> findByTitreContainingIgnoreCase(String titre);
    List<Livre> findAllByStatusIgnoreCaseOrderByTitreAsc(String status);
    List<Livre> findAllByStatusIgnoreCaseOrderByTitreDesc(String status);
    List<Livre> findAllByStatusIgnoreCaseOrderByLangueAsc(String status);
    List<Livre> findAllByStatusIgnoreCaseOrderByLangueDesc(String status);
    Livre findLivreById(int id);
    List<Livre> findByStatus(String status);
    long countByStatus(String status);
    long countByCategorieId(int categoriesId);
    int countByAuteurId(int auteurId);
}
