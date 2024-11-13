package com.hasnreziga.admin.livre;

import com.hasnreziga.admin.auteur.Auteur;
import com.hasnreziga.admin.categorie.Categories;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Setter
@Getter
@Entity
@Table(name = "livres")
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "titre")
    private String titre;

    @ManyToOne
    @JoinColumn(name = "auteur_id", referencedColumnName = "id")
    private Auteur auteur;

    @Column(name = "editeur")
    private String editeur;

    @Column(name = "edition")
    private String edition;

    @Column(name = "date_edition")
    private Date dateEdition;

    @ManyToOne
    @JoinColumn(name = "categorie_id", referencedColumnName = "id")
    private Categories categorie;

    @Column(name = "date_publication")
    private Date datePublication;

    @Column(name = "date_sortie")
    private Date dateSortie;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "langue")
    private String langue;


    @Column(name ="fichier", columnDefinition = "TEXT")
    private String fichier;

    @Column(name = "status")
    private String status = "en attente";
}
