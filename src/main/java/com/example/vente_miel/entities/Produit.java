package com.example.vente_miel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String nom;
    private  String description;
    private  Integer quantite;
    private Integer quantiteRestante;
    private double prix;
    private String date_ajout;
    private String type;
    private Texture texture;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "produit_images",
            joinColumns = {
            @JoinColumn(name = "PRODUIT_ID")
    },
            inverseJoinColumns = {
                    @JoinColumn(name = "IMAGE_ID")

            }
            )
    private Set<ImageModel> imageProduit;
}
