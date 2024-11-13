package com.hasnreziga.admin.admin;


import com.hasnreziga.admin.auteur.Auteur;
import com.hasnreziga.admin.auteur.AuteurService;
import com.hasnreziga.admin.categorie.CategorieService;
import com.hasnreziga.admin.categorie.Categories;
import com.hasnreziga.admin.lecteur.Lecteur;
import com.hasnreziga.admin.lecteur.LecteurService;
import com.hasnreziga.admin.livre.Livre;
import com.hasnreziga.admin.livre.LivreService;
import com.hasnreziga.admin.mainService.EmailService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class biblioControl {

    @Autowired
    private AuteurService auteurService;

    @Autowired
    private LivreService livreService;

    @Autowired
    private CategorieService categoriesService;

    @Autowired
    private LecteurService lecteurService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/")
    public String start(){
        return "statistic";
    }

    @GetMapping("/homeadmin")
    public String homeAdmin() {
        return "index";
    }

    @GetMapping("/Home")
    public String Home(Model model){
        model.addAttribute("totalLivres", livreService.count());
        model.addAttribute("totalLivresAttente", livreService.count1());
        model.addAttribute("totalLecteurs", lecteurService.count());
        model.addAttribute("totalAuteurs", auteurService.count());
        model.addAttribute("totalCategories", categoriesService.count());
        return "statistic";
    }


    //AUTEUR
    @GetMapping("/Rechercher")
    public String rechercherAuteur(Model model,@RequestParam("nom") String nom){
        List<Auteur> auteurRechercher = auteurService.auteurRechercher(nom);
        model.addAttribute("auteurs", auteurRechercher);
        return "pageAuteur";
    }

    @GetMapping("/ListAuteurs")
    public String trierAuteurs(@RequestParam(defaultValue = "id_asc") String sort,Model model) {
        List<Auteur> auteursTries;
        switch (sort) {
            case "id_desc":
                auteursTries = auteurService.trieIdDesc();
                break;
            case "nom_asc":
                auteursTries = auteurService.trieNomAsc();
                break;
            case "nom_desc":
                auteursTries = auteurService.trieNomDesc();
                break;
            case "pseudo_asc":
                auteursTries = auteurService.triePseudoAsc();
                break;
            case "pseudo_desc":
                auteursTries = auteurService.triePseudoDesc();
                break;
            case "id_asc":
            default:
                auteursTries = auteurService.auteurList();
                break;
        }

        Map<Integer, Integer> livresCount = new HashMap<>();
        for (Auteur auteur : auteursTries) {
            int count = livreService.LivresByAuteur(auteur.getId());
            livresCount.put(auteur.getId(), count);
        }

        model.addAttribute("auteurs", auteursTries);
        model.addAttribute("livresCount", livresCount);
        return "pageAuteur";
    }


    //CATEGORIES
    @PostMapping("/AjouterCategories")
    public String saveCategory(@ModelAttribute("categories") Categories categories) {
        categoriesService.saveCategories(categories);
        return "pageCategories";
    }

    @GetMapping("/SupprimerCategories/{id}")
    public String supprimer(@PathVariable int id) {
        categoriesService.supprimer(id);
        return "pageCategories";
    }

    @GetMapping("/RechercherCategories")
    public String rechercherCategories(Model model,@RequestParam("nom")  String nom){
        List<Categories> rechercherCategories = categoriesService.rechercherCategories(nom);
        Map<Integer, Long> livresCount = new HashMap<>();
        for (Categories category : rechercherCategories) {
            long count = livreService.countLivresByCategorie(category.getId());
            livresCount.put(category.getId(), count);
        }
        model.addAttribute("categories", rechercherCategories);
        model.addAttribute("livresCount", livresCount);
        return "pageCategories";
    }

    @GetMapping("/listCategories")
    public String getCategories(@RequestParam(defaultValue = "asc") String sort,Model model) {
        List<Categories> categories;
        switch (sort) {
            case "name_asc":
                categories = categoriesService.trieNomAsc();
                break;
            case "name_desc":
                categories = categoriesService.trieNomDesc();
                break;
            case "desc":
                categories = categoriesService.trieDesc();
                break;
            case "asc":
            default:
                categories = categoriesService.trieAsc();
                break;
        }
        Map<Integer, Long> livresCount = new HashMap<>();
        for (Categories category : categories) {
            long count = livreService.countLivresByCategorie(category.getId());
            livresCount.put(category.getId(), count);
        }

        model.addAttribute("categories", categories);
        model.addAttribute("livresCount", livresCount);
        return "pageCategories";
    }


    //LECTEUR
    @GetMapping("/RechercherLecteur")
    public String rechercherLecteur(Model model, @RequestParam("nom")  String nom){
        List<Lecteur> auteurRechercher = lecteurService.rechercherLecteur(nom);
        model.addAttribute("lecteurs", auteurRechercher);
        return "pageLecteur";
    }

    @GetMapping("/listLecteur")
    public String trierLecteurs(@RequestParam(defaultValue = "id_asc") String sort,Model model) {
        List<Lecteur> lecteursTries;
        switch (sort) {
            case "id_desc":
                lecteursTries = lecteurService.trierIdDesc();
                break;
            case "nom_asc":
                lecteursTries = lecteurService.trierNom();
                break;
            case "nom_desc":
                lecteursTries = lecteurService.trierNomDesc();
                break;
            case "id_asc":
            default:
                lecteursTries = lecteurService.trierIdAsc();
                break;
        }

        model.addAttribute("lecteurs", lecteursTries);
        return "pageLecteur";
    }


    //LIVRE

    @GetMapping("/Attente")
    public String LivreAttente(Model model){
        List<Livre> livreList = livreService.livreAttente();
        model.addAttribute("livres", livreList);
        return "livreAttente";
    }

    @GetMapping("/editLivre/{id}")
    public String editLivreForm(@PathVariable int id, Model model) {
        Livre livre = livreService.livreId(id);
        List<Categories> categories = categoriesService.categoriesList();
        model.addAttribute("livre", livre);
        model.addAttribute("categories", categories);
        return "updateLivre";
    }

    @PostMapping("/MettreAJourLivre")
    public String mettreAJourLivre(@RequestParam("id") int id, @ModelAttribute Livre livre) {
        livreService.update(id, livre);
        return "statistic";
    }

    @GetMapping("/accepter/{id}")
    public String accepterLivre(@PathVariable("id") int id) {
        Livre livre = auteurService.livreById(id);
        livreService.accepterLivre(id);
        String email = livre.getAuteur().getEmail();
        String nom = livre.getAuteur().getNom();
        String prenom = livre.getAuteur().getPrenom();
        String titre = livre.getTitre();
        String genre = livre.getAuteur().getGenre();
        emailService.envoyerEmailAccepter(email, livre.getId(), titre, nom, prenom, genre);
        return "statistic";
    }

    @GetMapping("/refuser/{id}")
    public String afficherFormulaire(@PathVariable("id") int id, Model model) {
        model.addAttribute("livreId", id);
        return "remarque";
    }

    @PostMapping("/envoyerRemarque")
    public String envoyerRemarque(@RequestParam("livreId") int livreId, @RequestParam("remarque") String remarque, Model model) {
        livreService.refuserLivre(livreId);
        Livre livre = auteurService.livreById(livreId);
        String email = livre.getAuteur().getEmail();
        String nom = livre.getAuteur().getNom();
        String prenom = livre.getAuteur().getPrenom();
        String titre = livre.getTitre();
        String genre = livre.getAuteur().getGenre();
        emailService.envoyerEmailRefuser(email, livreId, titre, remarque, nom, prenom, genre);
        return "statistic";
    }

    @GetMapping("/SupprimerLivre/{id}")
    public String supprimerLivre(@PathVariable int id) {
        livreService.supprimer(id);
        return "statistic";
    }

    @ModelAttribute("totalLivresAttente")
    public long TotalLivresAttente() {
        return livreService.count1();
    }

    @GetMapping("/RechercherLivre")
    public String rechercher(Model model, @RequestParam("nom") String titre) {
        List<Livre> rechercheLivre = livreService.rechercherLivre(titre);
        model.addAttribute("livres", rechercheLivre);
        return "pageLivre";
    }

    @GetMapping("/listLivre")
    public String trierLivres(@RequestParam(defaultValue = "titre_asc") String sort, Model model) {
        List<Livre> livresTries;
        switch (sort) {
            case "titre_desc":
                livresTries = livreService.triDesc();
                break;
            case "langue_asc":
                livresTries = livreService.triLangueAsc();
                break;
            case "langue_desc":
                livresTries = livreService.triLangueDesc();
                break;
            case "titre_asc":
            default:
                livresTries = livreService.triAsc();
                break;
        }

        model.addAttribute("livres", livresTries);
        return "pageLivre";
    }
}
