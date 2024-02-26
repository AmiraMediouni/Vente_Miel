package com.example.vente_miel.entities;

import lombok.Data;

@Data
public class CommandeQuantiteProduit {
    private Long produiId;
    private Integer quantite;
}
