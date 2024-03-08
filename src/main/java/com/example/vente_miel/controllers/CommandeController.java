package com.example.vente_miel.controllers;

import com.example.vente_miel.entities.Commande;
import com.example.vente_miel.entities.CommandeInput;
import com.example.vente_miel.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/orders" )
public class CommandeController {

    @Autowired
    CommandeService commandeService;

@PostMapping({"/placeOrder"})
    public void addNewCommande(@RequestBody CommandeInput commandeInput) {
         this.commandeService.addNewCommande(commandeInput);
    }

    @GetMapping({"/getOrderDetails"})
    public List<Commande> getOrderDetails() {
        return this.commandeService.getOrderDetails();
    }

    @GetMapping({"/getAllOrderDetails/{status}"})
    public List<Commande> getAllOrderDetails(@PathVariable(name = "status") String status) {
        return commandeService.getAllOrderDetails(status);
    }

    @GetMapping({"/markOrderAsDelivered/{orderId}"})
    public void markOrderAsDelivered(@PathVariable(name = "orderId") Integer orderId) {
        commandeService.markOrderAsDelivered(orderId);
    }

}

