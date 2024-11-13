package com.hasnreziga.admin.categorie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CategoriesControl {
    @Autowired
    private CategorieService categorieService;
}
