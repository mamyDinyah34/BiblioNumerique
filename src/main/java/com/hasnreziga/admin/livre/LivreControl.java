package com.hasnreziga.admin.livre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LivreControl {

    @Autowired
    private LivreService livreService;
}
