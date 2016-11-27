/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.controllers;

import com.cefisi.modeles.Personne;
import com.cefisi.modeles.Promotion;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Vladimir
 */
@Controller
public class PersonneController{
    
    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/personnes", method = RequestMethod.GET)
     public String doGet(ModelMap map, HttpSession session 
    ) throws SQLException {
        System.out.println("/projet");
        System.out.println("test" );
        List<Personne> personnes = entityManager.createQuery("select p from Personne p").getResultList();
        map.put("personnes", personnes);
        return "personnes";
    }
     
     
     
    @Transactional
    @RequestMapping(value = "/personne-{idPersonne}", method = RequestMethod.GET)
    public String personne(ModelMap map, @PathVariable(value = "idPersonne") long idPersonne) throws SQLException {
        Personne personne = entityManager.find(Personne.class, idPersonne);
        Date today = new Date(Calendar.getInstance().getTimeInMillis());
        map.put("personne", personne);
        return "personne";
    }

}
