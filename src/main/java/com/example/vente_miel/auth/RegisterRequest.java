package com.example.vente_miel.auth;

import com.example.vente_miel.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private  String nom;
    private  String prenom;
    private  String email;
    private  String password;
    private int telephone;
    private  String addresse;

}
