package com.example.vente_miel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private  String orderFullName;
    private  String orderFullAddress;
    private String orderContactNumber;
    private String orderStatus;
    private Double orderAmount;
    @ManyToOne
    private Produit product;
    @ManyToOne
    private Utilisateur user;
    public Commande(String orderFullName, String orderFullAddress, String orderContactNumber, String orderStatus, Double orderAmount, Produit product, Utilisateur user) {
        this.orderFullName = orderFullName;
        this.orderFullAddress = orderFullAddress;
        this.orderContactNumber = orderContactNumber;
        this.orderStatus = orderStatus;
        this.orderAmount = orderAmount;
       this.product = product;
        this.user = user;
    }

   /* public Commande(String orderfullName, String orderfullAddress, String ordercontactNumber, String orderStatus, double orderAmount, Utilisateur user) {
    }*/
}
