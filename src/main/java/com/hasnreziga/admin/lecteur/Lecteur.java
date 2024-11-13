package com.hasnreziga.admin.lecteur;

import com.hasnreziga.admin.mainEntity.Personne;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Setter
@Getter
@Entity
@Table(name = "lecteurs")
public class Lecteur extends Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "heure_connexion")
    private Date heureConnexion;

    @Column(name = "roles")
    private String role = "lecteur";
}
