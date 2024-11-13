package com.hasnreziga.admin.lecteur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LecteurControl {
    @Autowired
    private LecteurService lecteurService;
}
