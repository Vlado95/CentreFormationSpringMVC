/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.controllers;

import com.cefisi.modeles.Equipe;
import com.cefisi.modeles.Projet;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author john
 */
@Controller
public class ProjetController {
    
    
    
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @RequestMapping(value = "/projet-{idProjet}", method = RequestMethod.GET)
    public String projet(ModelMap map, @PathVariable(value = "idProjet") long idProjet) throws SQLException {
        Projet projet = entityManager.find(Projet.class, idProjet);
        map.put("projet", projet);

        return "projet";
    }
    
    

 /*   @RequestMapping(value = "/projet-{idProjet}", method = RequestMethod.GET)
    public String projet(ModelMap map, @PathVariable(value = "idProjet") Long idProjet) throws SQLException {
        System.out.println("/projet");
        Projet projet = Projet.getById(idProjet);
        map.put("projet", projet);
        return "projet";
    }
    
    public String equipe(ModelMap map, @PathVariable(value = "idProjet") Long idProjet) throws SQLException {
        System.out.println("Equipes");
        List<Equipe> equipes = Projet.getEquipe(idProjet);
        map.put("equipes", equipes);
        return "projet";
    }*/
}
