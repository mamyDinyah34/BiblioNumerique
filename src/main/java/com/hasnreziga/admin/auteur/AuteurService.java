package com.hasnreziga.admin.auteur;

import com.hasnreziga.admin.livre.Livre;
import com.hasnreziga.admin.livre.LivreImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuteurService {
    @Autowired
    private AuteurImpl auteurImpl;

    @Autowired
    private LivreImpl livreImpl;

    public List<Auteur> auteurList(){
        return auteurImpl.findAllByOrderByIdAsc();
    }

    public List<Auteur> auteurRechercher(String nom){
        return auteurImpl.findByNomContainingIgnoreCase(nom);
    }

    public List<Auteur> trieIdDesc(){
        return auteurImpl.findAllByOrderByIdDesc();
    }

    public List<Auteur> trieNomAsc(){
        return auteurImpl.findAllByOrderByNomAsc();
    }

    public List<Auteur> trieNomDesc(){
        return auteurImpl.findAllByOrderByNomDesc();
    }

    public List<Auteur> triePseudoAsc(){
        return auteurImpl.findAllByOrderByPseudoAsc();
    }

    public List<Auteur> triePseudoDesc(){
        return auteurImpl.findAllByOrderByPseudoDesc();
    }

    public long count(){
        return auteurImpl.count();
    }

    public Livre livreById(int livreId) {
        return livreImpl.findById(livreId).orElse(null);
    }
}
