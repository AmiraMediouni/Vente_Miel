package com.example.vente_miel.services;

import com.example.vente_miel.config.JwtAuthenticationFilter;
import com.example.vente_miel.entities.*;
import com.example.vente_miel.repositories.CommandeRepository;
import com.example.vente_miel.repositories.ProduitRepository;
import com.example.vente_miel.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
        }

    }

   /* public List<Produit> produitList=new CopyOnWriteArrayList<>();
    private int produitIdCount=1;
    public List<Produit> getAllProduits(){
        return produitRepository.findAll();
    }

    public Produit getProduitByID(long id){
        return produitRepository.findById(id).get();
    }


    public Produit updateProduitByID(long id, Produit produit){
        produit.setId(id);
        return produitRepository.save(produit);

    }
    public void deleteProduitById(long id ){
         produitRepository.deleteById(id);

    }*/
}
