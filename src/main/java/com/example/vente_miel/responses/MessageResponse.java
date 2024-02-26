package com.example.vente_miel.responses;

import com.example.vente_miel.entities.Utilisateur;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {
    private  String token;
    private Utilisateur utilisateur;

}
