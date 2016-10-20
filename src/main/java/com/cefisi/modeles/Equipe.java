/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.modeles;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 *
 * @author user
 */
@Entity
public class Equipe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipe")
    Long id;
    
    @Column(name = "id_projet")
    Long idProjet;
    
    @Column(name = "id_createur")
    Long idCreateur;
    
    @Column(name = "date_creation")
    Date dateCreation;
    
    @Column(name = "resume")
    String resume;

    public Equipe(Long id, Long idProjet, Long idCreateur, Date dateCreation, String resume) {
        this.id = id;
        this.idProjet = idProjet;
        this.idCreateur = idCreateur;
        this.dateCreation = dateCreation;
        this.resume = resume;
    }

    public Equipe() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(Long idProjet) {
        this.idProjet = idProjet;
    }

    public Long getIdCreateur() {
        return idCreateur;
    }

    public void setIdCreateur(Long idCreateur) {
        this.idCreateur = idCreateur;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
    
    
  /*  public void insert() throws SQLException {
    assert idCreateur != null;
    assert resume != null && !resume.matches("/^ \t\n\r$");
    Connection connection = Database.getConnection();
    // Commencer une transaction
    connection.setAutoCommit(false);
    try {
      
      // Inserer le produit
      String sql = "INSERT INTO equipe(id_projet,id_createur, date_creation, resume) VALUES(?, ?, ?,?)";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setLong(1, 1);
      stmt.setLong(2, idCreateur);
      stmt.setDate(3, (java.sql.Date) new Date(Calendar.getInstance().getTimeInMillis()));
      stmt.setString(4, resume);
      stmt.executeUpdate();
      stmt.close();
      
      
      // Recuperer le id
      Statement maxStmt = connection.createStatement();
      ResultSet rs = maxStmt.executeQuery("SELECT MAX(id_projet) AS id FROM equipe");
      rs.next();
      id = rs.getLong("id");
      rs.close();
      maxStmt.close();
      // Valider
      connection.commit();
    }
    catch (SQLException exc) {
      connection.rollback();
      exc.printStackTrace();
      throw exc;
    } finally {
      connection.close();
    }
  }*/


}
