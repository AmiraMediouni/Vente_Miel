package com.example.vente_miel.entities;

import lombok.Data;

import java.util.List;

@Data
public class CommandeInput {
    private  String fullName;
    private String fullAddress;
    private  String contactNumber;
    private List<CommandeQuantiteProduit> commandeQuantiteList;
}
