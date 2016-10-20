/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.modeles;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_projet")
    private Long id;

    /*@Column(name = "id_promotion")
    private Long idPromotion;*/

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_promotion")
    private Promotion promotion;
    
   /* @Column(name = "id_createur")
    private Long idCreateur;*/
    
    @ManyToOne
    @JoinColumn(name = "id_createur")
    private Personne createur;

    @Column(name = "sujet")
    @Type(type = "text")
    private String sujet;

    @Column(name = "titre")
    private String titre;

    @Column(name = "date_creation")
    private Date dateCreation;

    @Column(name = "date_limite")
    private Date dateLimite;

    public Projet() {
    }

    public Projet(Long id, Promotion promotion, Personne createur, String sujet, String titre, Date dateCreation, Date dateLimite) {
        this.id = id;
        this.promotion = promotion;
        this.createur = createur;
        this.sujet = sujet;
        this.titre = titre;
        this.dateCreation = dateCreation;
        this.dateLimite = dateLimite;
    }
/*
    public static List<Projet> getProjets() throws SQLException {
        List<Projet> result = new ArrayList<Projet>();
        Connection connection = Database.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM projet");
        while (rs.next()) {
            result.add(new Projet(rs.getLong("id_projet"), rs.getLong("id_promotion"), rs.getLong("id_createur"), rs.getString("sujet"), rs.getString("titre"), rs.getDate("date_creation"), rs.getDate("date_limite")));
        }
        rs.close();
        stmt.close();
        connection.close();
        return result;
    }

    public static Projet getById(Long id) throws SQLException {
        Projet result = null;
        Connection connection = Database.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM projet WHERE id_projet=" + id);
        if (rs.next()) {
            result = new Projet(id, rs.getLong("id_promotion"), rs.getLong("id_createur"), rs.getString("sujet"), rs.getString("titre"), rs.getDate("date_creation"), rs.getDate("date_limite"));
        }
        rs.close();
        stmt.close();
        connection.close();
        return result;
    }

    public static List<Equipe> getEquipe(Long id) throws SQLException {
        List<Equipe> result = new ArrayList<Equipe>();
        Connection connection = Database.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM equipe WHERE id_projet=" + id);
        while (rs.next()) {
            result.add(new Equipe(id, rs.getLong("idProjet"), rs.getLong("idCreateur"), rs.getDate("dateCreation"),rs.getString("resume")));
        }
        rs.close();
        stmt.close();
        connection.close();
        return result;
    }
*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this. promotion = promotion;
    }

    public Personne getCreateur() {
        return createur;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(Date dateLimite) {
        this.dateLimite = dateLimite;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.sujet);
        hash = 97 * hash + Objects.hashCode(this.titre);
        hash = 97 * hash + Objects.hashCode(this.dateCreation);
        hash = 97 * hash + Objects.hashCode(this.dateLimite);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Projet other = (Projet) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.promotion != other.promotion) {
            return false;
        }
        if (this.createur != other.createur) {
            return false;
        }
        if (!Objects.equals(this.sujet, other.sujet)) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.dateCreation, other.dateCreation)) {
            return false;
        }
        if (!Objects.equals(this.dateLimite, other.dateLimite)) {
            return false;
        }
        return true;
    }
}
