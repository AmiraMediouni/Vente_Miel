package com.example.vente_miel.services;

import com.example.vente_miel.entities.Produit;
import com.example.vente_miel.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class ProduitService {
    @Autowired
    ProduitRepository produitRepository;
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
}
