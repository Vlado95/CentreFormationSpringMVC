/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.service;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Vladimir
 */
public class CustomUser extends User
{
    private final long idPersonne; 
    private final String nom;
    private final String prenom;
    private final String promotion;
    private final String role;
    
    public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired, 
            boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
            long idPersonne, String nom, String prenom, String promotion, String role) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.idPersonne = idPersonne;
        this.nom = nom;
        this.prenom = prenom;
        this.promotion = promotion;
        this.role = role;
    }

    public long getIdPersonne() {
        return idPersonne;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPromotion() {
        return promotion;
    }

    public String getRole() {
        return role;
    }
    
    
    
}
