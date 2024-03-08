package com.example.vente_miel.services;

import com.example.vente_miel.config.JwtAuthenticationFilter;
import com.example.vente_miel.entities.*;
import com.example.vente_miel.repositories.CommandeRepository;
import com.example.vente_miel.repositories.ProduitRepository;
import com.example.vente_miel.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommandeService {
    private static final String ORDER_PLACED="placée";
    @Autowired
    CommandeRepository commandeRepository;
    @Autowired
    ProduitRepository produitRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    public void addNewCommande(CommandeInput commandeInput){
        List<CommandeQuantiteProduit> productQuantityList=commandeInput.getCommandeQuantiteList();
        for(CommandeQuantiteProduit o :productQuantityList){
            Produit product = produitRepository.findById(o.getProduiId()).get();

            String currentUser= JwtAuthenticationFilter.CURRENT_USER;
            Utilisateur user=utilisateurRepository.findByEmail(currentUser).get();

            Commande commande=new Commande(
                    commandeInput.getFullName(),
                    commandeInput.getFullAddress(),
                    commandeInput.getContactNumber(),
                    ORDER_PLACED,
                    product.getPrix()*o.getQuantite(),
                    product,
                    user
            );
            commandeRepository.save(commande);
            product.setQuantiteRestante(product.getQuantite()-o.getQuantite());
            produitRepository.save(product);
        }

    }
    public List<Commande> getOrderDetails(){
        String currentUser=JwtAuthenticationFilter.CURRENT_USER;
        Utilisateur user=utilisateurRepository.findByEmail(currentUser).get();
        return commandeRepository.findByUser(user);

    }
    public List<Commande> getAllOrderDetails( String status) {
        List<Commande> orderDetails = new ArrayList<>();
        if(status.equals("All")) {
            commandeRepository.findAll().forEach(
                    x -> orderDetails.add(x)
            );
        } else {
            commandeRepository.findByOrderStatus(status).forEach(
                    x -> orderDetails.add(x)
            );
        }

            return orderDetails;
    }

    public void markOrderAsDelivered(Integer orderId) {
        Commande commande = commandeRepository.findById(orderId).get();

        if(commande != null) {
            commande.setOrderStatus("Livrée");
            commandeRepository.save(commande);
        }

    }



}
