package com.hasnreziga.admin.categorie;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {
    @Autowired
    private CategoriesImpl categoriesImpl;

    public List<Categories> categoriesList(){
        return categoriesImpl.findAllByOrderByIdAsc();
    }

    public List<Categories> rechercherCategories(String nom){
        return categoriesImpl.findByNomContainingIgnoreCase(nom);
    }

    public Categories saveCategories(Categories categories){
        return categoriesImpl.save(categories);
    }

    public List<Categories> trieNomAsc(){
        return categoriesImpl.findAllByOrderByNomAsc();
    }

    public List<Categories> trieNomDesc(){
        return categoriesImpl.findAllByOrderByNomDesc();
    }

    public List<Categories> trieDesc(){
        return categoriesImpl.findAllByOrderByIdDesc();
    }

    public List<Categories> trieAsc(){
        return categoriesImpl.findAllByOrderByIdAsc();
    }

    public void supprimer(int id){
        categoriesImpl.deleteById(id);

    }
    public long count(){
        return categoriesImpl.count();
    }
}
