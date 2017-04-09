/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.dao;

import com.cefisi.modeles.VerificationToken;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vladimir
 */
@Repository
public class VerificationTokenRepositoryImpl implements VerificationTokenRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public VerificationToken findByToken(String token) {
        VerificationToken myToken = new VerificationToken();
        Query query = entityManager.createQuery("select T from VerificationToken T where T.token = ?1");
        query.setParameter(1, token);
        myToken = ( VerificationToken) query.getSingleResult();
        return myToken;
    }

    @Override
    public VerificationToken save(VerificationToken myToken) {
        if (myToken.getId() == null) {
            System.out.println("com.cefisi.dao.VerificationTokenRepositoryImpl.save() persist strat");
            entityManager.persist(myToken);
            System.out.println("com.cefisi.dao.VerificationTokenRepositoryImpl.save() persist end");
            return myToken;

        } else {
                 System.out.println("com.cefisi.dao.VerificationTokenRepositoryImpl.save() merge");
            return entityManager.merge(myToken);
            // return adresse;
        }
    }

}
