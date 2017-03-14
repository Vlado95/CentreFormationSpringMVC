/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.service;

import com.cefisi.dao.PersistanceDao;
import com.cefisi.modeles.Equipe;
import com.cefisi.modeles.Personne;
import com.cefisi.modeles.Projet;
import com.cefisi.modeles.UploadFile;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vladimir
 */
@Service
@Transactional
public class UtiServiceImpl implements UtiService {

    @Autowired
    PersistanceDao dao;

    @Override
    public Equipe findByID(long idEquipe) {
        return dao.findByID(idEquipe);
    }

    @Override
    public List<UploadFile> findFilesEquipe(Long idEquipe) {
        return dao.findFilesEquipe(idEquipe);
    }

    @Override
    public List<Personne> findPersoSansEquipe(Long idPromotion, Long idProjet) {
        return dao.findPersoSansEquipe(idPromotion, idProjet);
    }

    @Override
    public Projet findProjectByID(long idProjet) {
        return dao.findProjectByID(idProjet);
    }

    /**
     *
     * @param equipe sauvegarder une nouvelle equipe
     */
    @Override
    public void save(Equipe equipe) {
        dao.save(equipe);
    }

    @Override
    public void update(String resume, long idEquipe) {
        dao.update(resume, idEquipe);
    }

    @Override
    public void addNewMember(Equipe equipe) {
        dao.addNewMember(equipe);
    }

    @Override
    public void deleteMember(long idPersonne, long idEquipe) {
        dao.deleteMember(idPersonne, idEquipe);
    }

}
