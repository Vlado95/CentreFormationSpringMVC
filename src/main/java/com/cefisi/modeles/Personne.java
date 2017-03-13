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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.springframework.web.bind.annotation.ModelAttribute;

@Entity
public class Personne {

//    @PersistenceContext
//    private EntityManager entityManager;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personne")
    private Long idPersonne;

    @ManyToMany(mappedBy = "etudiants", fetch = FetchType.EAGER)
    private Set<Promotion> promotions = new HashSet<>();
    //  private List<Promotion> promotions = new ArrayList<>();

    @ManyToMany(mappedBy = "membres")
    private List<Equipe> equipes = new ArrayList<>();

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;

    @Column(name = "pwd")
    private String password;

    @Column(name = "profil")
    private Boolean profil;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "personne_adresse",
//            joinColumns = @JoinColumn(name = "id_adresse"),
//            inverseJoinColumns = @JoinColumn(name = "id_personne")
//    )
//    private Adresse adresse;
    @Transient
    private String confirmEmail;

    @Transient
    private String confirmPassword;

    @Transient
    private String rue;

    @Transient
    private String codePostal;

    @Transient
    private String ville;

    @Transient
    private String pays;

    @Transient
    private String mobile;

    @Transient
    private String fixe;

    @Transient
    private String role;

    @Transient
    private String promo;

    public Personne() {

    }

    public Personne(String nom, String prenom, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }

    public Personne(String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
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

    public Boolean getProfil() {
        return profil;
    }

    public void setProfil(Boolean profil) {
        this.profil = profil;
    }

//    @ModelAttribute("promotion")
//    public Promotion getProm(Date dateActu, long idPersonne)throws SQLException {
//        Promotion result = null;
//        String sql = "SELECT P FROM promotion P WHERE dateActu=:dateactu BETWEEN debut_date=:debut_date AND fin_date=:fin_date AND P.id_promotion = (i_";
//        Query query = entityManager.createQuery(sql);
//        query.setParameter("debut_date", debutActu)
//                .setParameter("id_personne", idPersonne);
//        query.getSingleResult();
//        return result;
//    }
    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
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

    public String getRole() {
        if (Objects.equals(getProfil(), Boolean.TRUE)) {
            role = "Professeur";
        } else {
            role = "Etudiant";
        };
        return role;
    }

    public String getPromo() {
        Iterator prom = promotions.iterator();
        while (prom.hasNext()) {
            Promotion popo = (Promotion) prom.next();
            if (popo.getFinDate().getTime() == getmax()) {
                promo= popo.getName();

            }

        }
        return promo;
    }

    public Long getmax(){
        Iterator prom = promotions.iterator();
        Set<Long> findates = new HashSet<>();
        while (prom.hasNext()) {
            Promotion po = (Promotion) prom.next();
            findates.add(po.getFinDate().getTime());
        }
        Long max = Collections.max(findates);
        return max;
    }

}
