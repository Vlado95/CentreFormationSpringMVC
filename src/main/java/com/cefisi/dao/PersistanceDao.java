/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.dao;

import com.cefisi.modeles.Equipe;
import com.cefisi.modeles.Personne;
import com.cefisi.modeles.Projet;
import com.cefisi.modeles.UploadFile;
import java.util.List;

/**
 *
 * @author Vladimir
 */
public interface PersistanceDao {

    Equipe findByID(long idEquipe);

    /**
     *
     * @param idEquipe
     *
     */
    void save(Equipe equipe);

    /**
     *
     * @param resume
     * @param idEquipe
     * <p>update equipe</p>
     */
    void update(String resume, long idEquipe);

    /**
     * ajouter un nouveau membre dans l'equipe
     * @param equipe
     */
    void addNewMember(Equipe equipe);

    /**
     * Delete a member of team
     * @param idPersonne
     * @param idEquipe
     */
    void deleteMember(long idPersonne, long idEquipe);

    Projet findProjectByID(long idProjet);
//   void save(Projet projet); 
//   void update(Projet projet);

    List<UploadFile> findFilesEquipe(Long idEquipe);

    //List<Personne> findMembreEquipe(Long idEquipe);
    List<Personne> findPersoSansEquipe(Long idPromotion, Long idProjet);

}
