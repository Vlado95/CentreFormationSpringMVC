/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.controllers;

import com.cefisi.modeles.Personne;
import com.cefisi.modeles.Projet;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author john
 */
@Controller
public class ProjetsController {

    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/projet", method = RequestMethod.GET)
    public String doGet(ModelMap map//, HttpSession session //, 
            //HttpServletRequest request
    ) throws SQLException {
        System.out.println("/projet");
        //session.getAttribute("user");
        System.out.println("test" );
       // Personne user = (Personne) session.getAttribute("user");//request.getSession().getAttribute("user");
         //System.out.println("user: " + request.getSession().getAttribute("user"));
       //  System.out.println("userTest: " + user.getEmail());
        List<Projet> projets = entityManager.createQuery("select p from Projet p").getResultList();
        map.put("projets", projets);
        return "projets";
    }

}
