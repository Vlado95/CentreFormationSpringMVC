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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vladimir
 */
@Repository
public class PersistanceImpl implements PersistanceDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Equipe findByID(long idEquipe) {
        Equipe equipe = entityManager.find(Equipe.class, idEquipe);
        return equipe;
    }

    @Override
    public List<UploadFile> findFilesEquipe(Long idEquipe) {
        List<UploadFile> files = entityManager.createQuery("select F from UploadFile F JOIN F.equipe FE where FE.id =?1")
                .setParameter(1, idEquipe)
                .getResultList();
        return files;
    }

    @Override
    public List<Personne> findPersoSansEquipe(Long idPromotion, Long idProjet) {
        List<Personne> personSansEqp = entityManager.createQuery("select MP from Promotion P join P.etudiants MP where P.id = ?1 and MP.idPersonne not in " + "(select MB.idPersonne from Equipe E join E.membres MB join E.projet EP where EP.id = ?2)")
                .setParameter(1, idPromotion)
                .setParameter(2, idProjet)
                .getResultList();
        return personSansEqp;
    }

    @Override
    public Projet findProjectByID(long idProjet) {
        Projet projet = entityManager.find(Projet.class, idProjet);
        return projet;
    }

    @Override
    public void save(Equipe equipe) {
        entityManager.persist(equipe);
        entityManager.flush();
    }

    @Override
    public void update(String resume, long idEquipe) {
        String sql = "UPDATE equipe SET resume=:resume WHERE id_equipe=:id_equipe";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("resume", resume)
                .setParameter("id_equipe", idEquipe);
        query.executeUpdate();
    }

    @Override
    public void addNewMember(Equipe equipe) {
        entityManager.merge(equipe);
        entityManager.flush();
    }

    @Override
    public void deleteMember(long idPersonne, long idEquipe) {
        String sql = "Delete FROM membre_equipe WHERE id_equipe=:id_equipe AND id_personne=:id_personne";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("id_personne", idPersonne)
                .setParameter("id_equipe", idEquipe);
        query.executeUpdate();
    }

}
