package com.hasnreziga.admin.lecteur;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LecteurService {
    @Autowired
    private LecteurImpl lecteurImpl;

    public List<Lecteur> trierIdAsc() {
        return lecteurImpl.findAllByOrderByIdAsc();
    }

    public List<Lecteur> trierIdDesc() {
        return lecteurImpl.findAllByOrderByIdDesc();
    }

    public List<Lecteur> trierNom() {
        return lecteurImpl.findAllByOrderByNomAsc();
    }

    public List<Lecteur> trierNomDesc() {
        return lecteurImpl.findAllByOrderByNomDesc();
    }

    public List<Lecteur> rechercherLecteur(String nom){
        return lecteurImpl.findByNomContainingIgnoreCase(nom);
    }

    public long count(){
        return lecteurImpl.count();
    }

}
