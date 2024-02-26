package com.example.vente_miel.controllers;

import com.example.vente_miel.entities.CommandeInput;
import com.example.vente_miel.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/commandes" )
public class CommandeController {

    @Autowired
    CommandeService commandeService;

@PostMapping(value = {"/addNewCommande"})
    public void addNewCommande(@RequestBody CommandeInput commandeInput) {
         this.commandeService.addNewCommande(commandeInput);
    }
    /*
    @GetMapping
    public List<Commande> getAllCommandes() {
        return this.commandeService.getAllCommandes();
    }

    @GetMapping(value = "/{id}")
    public Commande getCommandeByID(@PathVariable("id") int id) {
        return this.commandeService.getCommandeByID(id);
    }

    @PostMapping
    public Commande addNewCommande(@RequestBody Commande commande) {
        return this.commandeService.addNewCommande(commande);
    }

    @PutMapping(value = "/{id}")
    public Commande updateCommande(@PathVariable("id") int id, @RequestBody Commande commande) {
        return commandeService.updateCommandeByID(id, commande);
    }

    @DeleteMapping(value = "/{id}")
    public MessageResponse deleteCommande(@PathVariable("id") int id) {
        return this.commandeService.deleteCommandeById(id);
    }*/


}

