/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.modeles;

/**
 *
 * @author Vladimir
 */
import java.util.ArrayList;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personne")
    private Long idPersonne;

    
    @ManyToMany(mappedBy = "etudiants")
    private Set<Promotion> promotions = new HashSet<>();
    
    
    @ManyToMany(mappedBy = "membres")
    private List<Equipe> equipes = new ArrayList<>();

    @Column(name = "nom")
    private String nom;

    
    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;

    

    public  Personne(){
        
    }

 

   public Long getIdPersonne() {
        return idPersonne;
    }
/*
    public void setId(Long id) {
        this.id = id;
    }*/

    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Set<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(Set<Promotion> promotions) {
        this.promotions = promotions;
    }
    
    public List<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = equipes;
    }

}

