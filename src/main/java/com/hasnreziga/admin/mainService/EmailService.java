package com.hasnreziga.admin.mainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void envoyerEmailRefuser(String emailAuteur, int livreId, String titreLivre, String remarque, String nomAuteur, String prenomAuteur, String genre) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailAuteur);
        message.setSubject("Remarque sur le livre : " + titreLivre);

        String civilite = genre.equalsIgnoreCase("femme") ? "Madame" : "Monsieur";

        String texteEmail = String.format(
                "Bonjour %s %s %s,\n\n" +
                        "Votre livre intitulé \"%s\" (ID: %d) a été refusé pour la raison suivante :\n\n" +
                        "%s\n\n" +
                        "Merci de votre compréhension.\n\n" +
                        "Cordialement,\n" +
                        "L'équipe de la bibliothèque",
                civilite, prenomAuteur, nomAuteur, titreLivre, livreId, remarque
        );

        message.setText(texteEmail);
        mailSender.send(message);
    }

    public void envoyerEmailAccepter(String emailAuteur, int livreId, String titreLivre, String nomAuteur, String prenomAuteur, String genre) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailAuteur);
        message.setSubject("Acceptation du livre : " + titreLivre);

        String civilite = genre.equalsIgnoreCase("femme") ? "Madame" : "Monsieur";

        String texteEmail = String.format(
                "Bonjour %s %s %s,\n\n" +
                        "Félicitations ! Votre livre intitulé \"%s\" (ID: %d) a été accepté et sera disponible prochainement dans notre bibliothèque numérique.\n\n" +
                        "Merci pour votre contribution.\n\n" +
                        "Cordialement,\n" +
                        "L'équipe de la bibliothèque",
                civilite, prenomAuteur, nomAuteur, titreLivre, livreId
        );

        message.setText(texteEmail);
        mailSender.send(message);
    }

}

