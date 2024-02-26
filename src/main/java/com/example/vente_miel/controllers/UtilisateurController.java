package com.example.vente_miel.controllers;

import com.example.vente_miel.entities.Utilisateur;
import com.example.vente_miel.services.UtilisateurService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value ="/utilisateurs" )
public class UtilisateurController {

    @Autowired
    UtilisateurService utilisateurService;

    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return this.utilisateurService.getAllUtilisateurs();
    }

    @GetMapping(value = "/{id}")
    public Utilisateur getUtilisateurByID(@PathVariable("id") long id) {
        return this.utilisateurService.getUtilisateurByID(id);
    }

    @PostMapping
    public Utilisateur addNewUtilisateur(@RequestBody Utilisateur utilisateur) {
        return this.utilisateurService.addNewUtilisateur(utilisateur);
    }

    @PutMapping(value = "/{id}")
    public Utilisateur updateUtilisateur(@PathVariable("id") long id, @RequestBody Utilisateur utilisateur) {
        return utilisateurService.updateUtilisateurByID(id, utilisateur);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUtilisateur(@PathVariable("id") long id) {
        this.utilisateurService.deleteUtilisateurById(id);
    }

    /*@PostConstruct
    public void initRoleAndUser() {
        utilisateurService.initRoleAndUser();
    }*/
    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('ADMIN')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('CLIENT')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }

}
