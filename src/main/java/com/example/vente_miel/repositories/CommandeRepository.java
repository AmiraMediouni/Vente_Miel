package com.example.vente_miel.repositories;

import com.example.vente_miel.entities.Commande;
import com.example.vente_miel.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande,Integer> {
    public List<Commande> findByUser(Utilisateur user);
    public List<Commande> findByOrderStatus(String status);
}
