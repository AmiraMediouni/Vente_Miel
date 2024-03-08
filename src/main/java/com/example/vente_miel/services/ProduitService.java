package com.example.vente_miel.services;

import com.example.vente_miel.config.JwtAuthenticationFilter;
import com.example.vente_miel.entities.Cart;
import com.example.vente_miel.entities.Produit;
import com.example.vente_miel.entities.Utilisateur;
import com.example.vente_miel.repositories.CartRepository;
import com.example.vente_miel.repositories.ProduitRepository;
import com.example.vente_miel.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
public class ProduitService {
    @Autowired
    ProduitRepository produitRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    CartRepository cartRepository;
    public List<Produit> produitList=new CopyOnWriteArrayList<>();
    private int produitIdCount=1;
    public List<Produit> getAllProduits(){
        return produitRepository.findAll();
    }

    public Produit getProduitByID(long id){
        return produitRepository.findById(id).get();
    }

    public Produit addNewProduit(Produit produit){
        return produitRepository.save(produit);
    }

    public Produit updateProduitByID(long id, Produit produit){
        produit.setId(id);
        return produitRepository.save(produit);

    }
    public void deleteProduitById(long id ){
         produitRepository.deleteById(id);

    }
    public List<Produit> getProduitDetails(boolean isSingleProductCheckout, long id ){
        if (isSingleProductCheckout && id!=0){
            List <Produit> list=new ArrayList<>();
            Produit produit=produitRepository.findById(id).get();
            list.add(produit);
            return list;
        }else{
            String username = JwtAuthenticationFilter.CURRENT_USER;
            Utilisateur user = utilisateurRepository.findByEmail(username).get();
            List<Cart> carts = cartRepository.findByUser(user);

            return carts.stream().map(x -> x.getProduct()).collect(Collectors.toList());

        }

    }
}
