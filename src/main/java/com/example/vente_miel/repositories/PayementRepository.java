package com.example.vente_miel.repositories;

import com.example.vente_miel.entities.Payement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayementRepository extends JpaRepository<Payement,Long> {
}
