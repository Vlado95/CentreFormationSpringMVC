/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.modeles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Vladimir
 */
@Entity
public class Adresse {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adresse")
    private Long idAdresse;

    @Column(name = "rue")
    private String rue;
    
    @Column(name = "ville")
    private String ville;

    @Column(name = "code_postal")
    private String codePostal;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "pays")
    private String pays;
    
    @Column(name = "fixe")
    private String fixe;
    

    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "personne_adresse",
            joinColumns = @JoinColumn(name = "id_adresse"),
            inverseJoinColumns = @JoinColumn(name = "id_personne")
    )
    private Set<Personne> personnes;

    public Adresse() {
    }

    public Adresse(String rue, String ville, String codePostal, String mobile, String pays, String fixe) {
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
        this.mobile = mobile;
        this.pays = pays;
        this.fixe = fixe;
    }
 
    
    
    
    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Long getIdAdresse() {
        return idAdresse;
    }

    public void setIdAdresse(Long idAdresse) {
        this.idAdresse = idAdresse;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFixe() {
        return fixe;
    }

    public void setFixe(String fixe) {
        this.fixe = fixe;
    }

    public Set<Personne> getPersonnes() {
        return personnes;
    }

    public void setPersonnes(Set<Personne> personnes) {
        this.personnes = personnes;
    }
    
    
    
    
    
}
