package com.hasnreziga.admin.auteur;

import com.hasnreziga.admin.mainEntity.Personne;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@Entity
@Table(name = "auteurs")
public class Auteur extends Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "pseudo")
    private String pseudo;

    @Column(name = "roles")
    private String role = "auteur";
}

