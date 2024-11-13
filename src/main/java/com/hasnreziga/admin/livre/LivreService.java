package com.hasnreziga.admin.livre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreService {

    @Autowired
    private LivreImpl livreImpl;

    public Livre livreId(int id){
        return livreImpl.findLivreById(id);
    }

    public Integer LivresByAuteur(Integer auteurId) {
        return livreImpl.countByAuteurId(auteurId);
    }

    public List<Livre> rechercherLivre(String titre) {
        return livreImpl.findByTitreContainingIgnoreCase(titre);
    }

    public List<Livre> triAsc(){
        return livreImpl.findAllByStatusIgnoreCaseOrderByTitreAsc("accepter");
    }
    public List<Livre> triDesc(){
        return livreImpl.findAllByStatusIgnoreCaseOrderByTitreDesc("accepter");
    }
    public List<Livre> triLangueAsc(){
        return livreImpl.findAllByStatusIgnoreCaseOrderByLangueAsc("accepter");
    }
    public List<Livre> triLangueDesc(){
        return livreImpl.findAllByStatusIgnoreCaseOrderByLangueDesc("accepter");
    }

    public void supprimer(int id){
        livreImpl.deleteById(id);
    }

    public Livre update(int id, Livre nouvLivre) {
        Livre livre = livreImpl.findById(id).orElse(null);
        if (livre != null) {
            livre.setTitre(nouvLivre.getTitre());
            livre.setCategorie(nouvLivre.getCategorie());
            livre.setLangue(nouvLivre.getLangue());
            return livreImpl.save(livre);
        }
        return null;
    }

    public long count(){
        return livreImpl.countByStatus("accepter");
    }

    public long count1(){
        return livreImpl.countByStatus("en attente");
    }

    public List<Livre> livreAttente() {
        return livreImpl.findByStatus("en attente");
    }

    public List<Livre> livreAccepte() {
        return livreImpl.findByStatus("accepter");
    }

    public Livre accepterLivre(int id) {
        Livre livre = livreImpl.findById(id).orElse(null);
        if (livre != null) {
            livre.setStatus("accepter");
            return livreImpl.save(livre);
        }
        return null;
    }

    public Livre refuserLivre(int id) {
        Livre livre = livreImpl.findById(id).orElse(null);
        if (livre != null) {
            livre.setStatus("refuser");
            return livreImpl.save(livre);
        }
        return null;
    }

    public Livre getLivreById(int id) {
        return livreImpl.findById(id).orElse(null);
    }

    public long countLivresByCategorie(int categorieId) {
        return livreImpl.countByCategorieId(categorieId);
    }
}
