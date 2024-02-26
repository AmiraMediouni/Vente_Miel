package com.example.vente_miel.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    private String roleName;
    private String roleDescription;

    @Override
    public String getAuthority() {
        return this.getRoleName();
    }
}