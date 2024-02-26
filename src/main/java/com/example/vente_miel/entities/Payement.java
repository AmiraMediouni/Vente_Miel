package com.example.vente_miel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;
    private  Integer quantite;
    private double prix;
    private Date date;
    private Mode mode;
    private String typeCarte;
    private String numeroCarte;
    //@OneToOne
   // private Commande commande;
}
