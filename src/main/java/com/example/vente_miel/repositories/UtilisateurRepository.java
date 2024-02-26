package com.example.vente_miel.repositories;

import com.example.vente_miel.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
    Optional<Utilisateur> findByEmail(String email);
    @Override
    List<Utilisateur> findAll();
    boolean existsByEmail(String email);
}
