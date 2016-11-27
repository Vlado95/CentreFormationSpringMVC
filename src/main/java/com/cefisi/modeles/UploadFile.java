/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.modeles;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Vladimir
 */
@Entity
@Table(name = "FILES_UPLOAD")
public class UploadFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "upload_id")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_persoAjour")
    private Personne persoAjour;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_auteur")
    private Personne auteur;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_equipe")
    private Equipe equipe;
    
    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "type", length = 100, nullable = false)
    private String type;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "FILE_DATA", nullable = false)
    private byte[] data;
    
    
    @Column(name = "date_ajout")
    Date dateAjout;
    
    @Column(name = "date_mise_jr")
    Date dateMisaJr;
    
    @Column(name = "description")
    String description;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

//    public long getIdPersoAjour() {
//        return idPersoAjour;
//    }
//
//    public void setIdPersoAjour(long idPersoAjour) {
//        this.idPersoAjour = idPersoAjour;
//    }

    public Date getDateMisaJr() {
        return dateMisaJr;
    }

    public void setDateMisaJr(Date dateMisaJr) {
        this.dateMisaJr = dateMisaJr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public long getIdAuteur() {
//        return idAuteur;
//    }
//
//    public void setIdAuteur(long idAuteur) {
//        this.idAuteur = idAuteur;
//    }

    public Date getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }

//    public long getIdEquipe() {
//        return idEquipe;
//    }
//
//    public void setIdEquipe(long idEquipe) {
//        this.idEquipe = idEquipe;
//    }

    public Personne getPersoAjour() {
        return persoAjour;
    }

    public void setPersoAjour(Personne persoAjour) {
        this.persoAjour = persoAjour;
    }

    public Personne getAuteur() {
        return auteur;
    }

    public void setAuteur(Personne auteur) {
        this.auteur = auteur;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    
    
    
}
