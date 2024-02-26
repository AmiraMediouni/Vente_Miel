package com.example.vente_miel.services;

import com.example.vente_miel.auth.AuthenticationRequest;
import com.example.vente_miel.auth.RegisterRequest;
import com.example.vente_miel.entities.Utilisateur;
import com.example.vente_miel.exceptions.EmailAlreadyUsedException;
import com.example.vente_miel.repositories.UtilisateurRepository;
import com.example.vente_miel.responses.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UtilisateurRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public MessageResponse register(RegisterRequest request) throws EmailAlreadyUsedException {
        if (this.repository.existsByEmail(request.getEmail())) {
           throw new EmailAlreadyUsedException("Erreur: Email déja utilisé!");
                   }
        var utilisateur= Utilisateur.builder()
               .nom(request.getNom())
                .prenom(request.getPrenom())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .addresse(request.getAddresse())
                .telephone(request.getTelephone())
              //  .role(request.getRole())
                .build();
        repository.save(utilisateur);
        var jwtToken=jwtService.generateToken(utilisateur);
            return MessageResponse.builder()
                .token(jwtToken)
                .build();
    }
    private Set getAuthority(Utilisateur utilisateur) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        utilisateur.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }

    public MessageResponse authenticate(AuthenticationRequest request) {
        var user =repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken=jwtService.generateToken(user);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword(),
                        getAuthority(user)
                )
        );
        return new MessageResponse(jwtToken,user);
      /*  return MessageResponse.builder()
                .token(jwtToken)
                .build();*/
    }


}

