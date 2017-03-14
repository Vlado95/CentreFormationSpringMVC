/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.service;

import com.cefisi.modeles.Equipe;
import com.cefisi.modeles.Personne;
import com.cefisi.modeles.Projet;
import com.cefisi.modeles.UploadFile;
import java.util.List;

/**
 *
 * @author Vladimir
 */
public interface UtiService {

    /**
     *
     * @param idEquipe
     * @return Equipe par l'id
     */
    Equipe findByID(long idEquipe);

    /**
     *
     * @param equipe du type Equipe
     * sauvegarder une nouvelle equipe
     */
    void save(Equipe equipe);

    /**
     *<p>update equipe</p>
     * @param resume
     * @param idEquipe
     * 
     */
    void update(String resume, long idEquipe);

    /**
     * ajouter un nouveau dans l'equipe
     * @param equipe
     */
    void addNewMember(Equipe equipe);

    /**
     * Delete a member in a team
     * @param idPersonne
     * @param idEquipe
     */
    void deleteMember(long idPersonne, long idEquipe);
    /**
     *
     * @param idProjet
     * @return projet par l'id
     */
    Projet findProjectByID(long idProjet);

    //void save(Projet projet);
//   void update(Projet projet);

    List<UploadFile> findFilesEquipe(Long idEquipe);

    //List<Personne> findMembreEquipe(Long idEquipe);

    /**
     *
     * @param idPromotion
     * @param idProjet
     * @return liste des personnes qui n'ont pas d'equipe <br/>
     *       ces personnes sont dans la meme promotion que le projet
     */
    List<Personne> findPersoSansEquipe(Long idPromotion, Long idProjet);
}
