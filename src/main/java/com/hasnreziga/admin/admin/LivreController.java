package com.hasnreziga.admin.admin;

import com.hasnreziga.admin.livre.Livre;
import com.hasnreziga.admin.livre.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/livre")
public class LivreController {
    @Autowired
    private LivreService livreService;

    @GetMapping("/voir/{id}")
    public ResponseEntity<Resource> afficherPdf(@PathVariable int id) {
        Livre livre = livreService.livreId(id);
        if (livre == null || livre.getFichier() == null) {
            System.out.println("Livre ou fichier introuvable");
            return ResponseEntity.notFound().build();
        }

        Path filePath = Paths.get("/media/pdfs/", livre.getFichier());
        Resource resource;

        try {
            resource = new UrlResource(filePath.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                System.out.println("Le fichier n'existe pas ou est illisible");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (MalformedURLException e) {
            System.out.println("Erreur URL: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}
