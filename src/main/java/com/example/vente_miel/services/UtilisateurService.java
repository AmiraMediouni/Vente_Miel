package com.example.vente_miel.services;

import com.example.vente_miel.entities.Role;
import com.example.vente_miel.entities.Utilisateur;
import com.example.vente_miel.repositories.RoleRepository;
import com.example.vente_miel.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class UtilisateurService {
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public List<Utilisateur> utilisateurList=new CopyOnWriteArrayList<>();
    private int UtilisateurIdCount=1;
    public List<Utilisateur> getAllUtilisateurs(){
        return utilisateurRepository.findAll();
    }

    public Utilisateur getUtilisateurByID(long id){
        return utilisateurRepository.findById(id).get();
    }

    public Utilisateur addNewUtilisateur(Utilisateur utilisateur){
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur updateUtilisateurByID(long id, Utilisateur utilisateur){
        utilisateur.setId(id);
        return utilisateurRepository.save(utilisateur);

    }
    public void deleteUtilisateurById(long id ){
         utilisateurRepository.deleteById(id);

    }

   /* public void initRoleAndUser() {


        Role adminRole = new Role();
        adminRole.setRoleName("ADMIN");
        adminRole.setRoleDescription("Role administrateur");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("CLIENT");
        userRole.setRoleDescription("Role pour client ");
        roleRepository.save(userRole);

        Utilisateur adminUser = new Utilisateur();
        adminUser.setEmail("amira.mediouni90@gmail.com");
        adminUser.setNom("AMIRA_ADMIN");
        adminUser.setPassword(getEncodedPassword("1234"));
        adminUser.setPrenom("mediouni");
        adminUser.setAddresse("mahdia");
        adminUser.setTelephone(216);
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        utilisateurRepository.save(adminUser);


    Utilisateur user = new Utilisateur();
        user.setEmail("ali.said@gmail.com");
        user.setNom("Premier_Client");
        user.setPassword(getEncodedPassword("12345"));
        user.setPrenom("said");
        user.setAddresse("mahdia");
        user.setTelephone(216);
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
        utilisateurRepository.save(user);
    }
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }*/
}
