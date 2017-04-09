/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.dao;

import com.cefisi.modeles.Adresse;
import com.cefisi.modeles.Personne;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vladimir
 */
@Repository
public class AdresseRepositoryImpl implements AdresseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Adresse findByPersonnes(Personne user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Adresse save(Adresse adresse) {
       // if (adresse.getPersonnes()== null) {
        //    entityManager.persist(adresse);
         //   return adresse;
            
      //  } else {
          //return  
              System.out.println("start persitannce");
                  entityManager.merge(adresse);
                  entityManager.flush();
                          System.out.println("fin persitannce");
            return adresse;
       // }
    }

}
