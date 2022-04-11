package com.example.gestiondesetudiants;

import com.example.gestiondesetudiants.entities.Etudiant;
import com.example.gestiondesetudiants.enumerations.Genre;
import com.example.gestiondesetudiants.repositories.EtudiantRepository;
import com.example.gestiondesetudiants.security.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class GestiondesetudiantsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestiondesetudiantsApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner commandLineRunner(EtudiantRepository etudiantRepository) {
        return args -> {
            etudiantRepository.save(new Etudiant(null, "Rabab","ROCHDI",new Date(),"rabab.rochdi@gmail.com", false, Genre.FEMININ  ));
            etudiantRepository.save(new Etudiant(null, "wiswis","SABRI", new Date(),"w.sabri@gmail.com", true,Genre.MASCULIN));

            etudiantRepository.findAll().forEach(e -> {
                System.out.println(e.getNom());
                System.out.println(e.getPrenom());
            });

        };
    }

    @Bean
    CommandLineRunner saveUsers(SecurityService securityService) {
        return args -> {
            securityService.saveNewUser("rabab", "1234", "1234");
            securityService.saveNewUser("yassine", "1234", "1234");
            securityService.saveNewUser("salma", "1234", "1234");

            securityService.saveNewRole("USER", "");
            securityService.saveNewRole("ADMIN", "");

            securityService.addRoleToUser("rabab", "USER");
            securityService.addRoleToUser("rabab", "ADMIN");
            securityService.addRoleToUser("salma", "USER");
            securityService.addRoleToUser("yassine", "USER");


        };
    }

}
