/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.controllers;

import com.cefisi.modeles.Equipe;
import com.cefisi.modeles.Personne;
import com.cefisi.modeles.Projet;
import com.cefisi.modeles.Promotion;
import com.cefisi.service.UserService;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author john
 */
@Controller
public class ProjetController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UserService userService;
    
    
    
    @RequestMapping(value = "/projet", method = RequestMethod.GET)
    public String doGet(ModelMap map
    ) throws SQLException {
        System.out.println("/projet");
        System.out.println("test" );
        List<Projet> projets = entityManager.createQuery("select p from Projet p").getResultList();
        map.put("projets", projets);
        return "projets";
    }

    
    @Transactional
    @RequestMapping(value = "/projet-{idProjet}", method = RequestMethod.GET)
    public String projet(ModelMap map, @PathVariable(value = "idProjet") long idProjet) throws SQLException {
        Projet projet = entityManager.find(Projet.class, idProjet);  List<Personne> membreB = entityManager.createQuery("select MP from Promotion P join P.etudiants MP where P.id = ?1 and MP.idPersonne not in " + "(select MB.idPersonne from Equipe E join E.membres MB join E.projet EP where EP.id = ?2)")
                .setParameter(1, projet.getPromotion().getId())
                .setParameter(2, idProjet)
                .getResultList();
        List<Equipe> equipes = projet.getEquipes();
        LinkedHashSet<Equipe> equipesCl = new LinkedHashSet<Equipe>();
        equipesCl.addAll(equipes);
        equipes.clear();
        equipes.addAll(equipesCl);

        System.out.println("equipes: " + projet.getEquipes().size());
        //    System.out.println("membres id: " + membres.size());
        System.out.println("createur: " + projet.getCreateur().getIdPersonne());

        //    System.out.println("nb students without groupe : " + membreB.size());
        map.put("projet", projet);
        //map.put("nb", nb);
        map.put("equipes", equipes);
        //   map.put("membres", membres);
        map.put("membreB", membreB);
        return "projet";
    }

    @RequestMapping(value = "/new-projet", method = RequestMethod.GET)
    public String askNew(ModelMap map) {
        Projet projet = new Projet();
        projet.setPromotion(new Promotion());
        projet.setCreateur(new Personne());
        map.put("projet", new Projet());
        map.put("action", "Créer");
        map.put("titre", "Creer Projet");
        return "formProjet";
    }

    @Transactional
    @RequestMapping(value = "/new-projet", method = RequestMethod.POST)
    public String doNew(@Valid @ModelAttribute("projet") Projet projet,
            BindingResult result, ModelMap map, HttpSession session,
            @RequestParam("promotion.id") long id 
    ) throws SQLException {
        Promotion promotion = entityManager.find(Promotion.class, id);
        
        Personne createur =  (Personne) userService.getCurrentUser(); //session.getAttribute("user");
        projet.setCreateur(createur);
        projet.setPromotion(promotion);
        projet.setDateCreation(new Date(Calendar.getInstance().getTimeInMillis()));
        map.put("action", "Créer");
        map.put("titre", "Créer une projet");
        // System.out.println("idcreateur " + projet.getIdCreateur());

        if (result.hasErrors()) {
            System.out.println("erreurs");
        } else {

            entityManager.persist(projet);
            entityManager.flush();
            System.out.println("ok");
            map.put("message", "Projet enregistré");
        }
        return "redirect:/projet";
    }

    @RequestMapping(value = "/projet-{idProjet}-modifier",
            method = RequestMethod.GET)
    public String askModify(ModelMap map,
            @PathVariable(value = "idProjet") long idProjet) throws SQLException {
        Projet projet = entityManager.find(Projet.class, idProjet);
        map.put("projet", projet);
        map.put("action", "Modifier");
        map.put("titre", "Modifier le projet n° " + projet.getId());
        return "formProjet";
    }

    @Transactional
    @RequestMapping(value = "/projet-{idProjet}-modifier", method = RequestMethod.POST)
    public String doModify(
            @Valid Projet projet, // Injection + vérification des annotations du bean
            BindingResult result, // Les erreurs suite à l'injection sont ici
            ModelMap map,
            @PathVariable(value = "idProjet") long idProjet) throws SQLException {
        projet.setId(idProjet);
        System.out.println(projet);
        System.out.println(" this is idProjet" + idProjet);
        if (result.hasErrors()) {
            System.out.println("erreurs");
        } else {
 String sql = "UPDATE projet SET titre=:titre, sujet=:sujet, date_limite=:date_limite WHERE id_projet=:id_projet";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("titre", projet.getTitre())
                    .setParameter("sujet", projet.getSujet())
                    .setParameter("date_limite", projet.getDateLimite())
                    .setParameter("id_projet", projet.getId());
            query.executeUpdate();
        }
        return "redirect:/projet";
    }

    @ModelAttribute("promotions")
    public List<Promotion> GetPromotions() throws SQLException {
        List<Promotion> promotions = entityManager.createQuery("select p from Promotion p order by p.id").getResultList();
        return promotions;
    }
}
