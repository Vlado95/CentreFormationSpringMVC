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
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
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

    @Transactional
    @RequestMapping(value = "/projet-{idProjet}", method = RequestMethod.GET)
    public String projet(ModelMap map, @PathVariable(value = "idProjet") long idProjet) throws SQLException {
        Projet projet = entityManager.find(Projet.class, idProjet);
        List<Equipe> equipes = entityManager.createQuery("select E from Equipe E where E.idProjet = ?1 order by E.id")
                .setParameter(1, idProjet)
                .getResultList();
        System.out.println("nb equipes : " + equipes.size());
        map.put("projet", projet);
        map.put("equipes", equipes);
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
            BindingResult result, ModelMap map, @RequestParam("promotion.id") long id, @RequestParam("createur.idPersonne") long idPersonne
    ) throws SQLException {
        Promotion promotion = entityManager.find(Promotion.class, id);
        Personne createur = entityManager.find(Personne.class, idPersonne);
        projet.setCreateur(createur);
        projet.setPromotion(promotion);
        projet.setDateCreation(new Date());
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
        return "projets";
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
    /**
     * Peuple les valeurs de la liste déroulante <code>categories</code> dans la
     * JSP
     *
     * @return
     */
    @ModelAttribute("promotions")
    public List<Promotion> GetPromotions() throws SQLException {
        List<Promotion> promotions = entityManager.createQuery("select p from Promotion p order by p.id").getResultList();
        return promotions;
    }

    /* @ModelAttribute("createur")
  public Personne GetCreateur() throws SQLException {
    Personne createur = (Personne) entityManager.createQuery("select p from Personne p where p.id = 1");
    return createur;
  }*/
 /*
  
  
  
  
  public static Map<Integer, String> getCategories() throws SQLException {
    HashMap<Integer, String> result = new HashMap();
    String sql = "SELECT id_categorie, libelle FROM categorie";
    Connection connection = Database.getConnection();
    Statement stmt = connection.createStatement();
    ResultSet rs = stmt.executeQuery(sql);
    while (rs.next()) {
      result.put(rs.getInt("id_categorie"), rs.getString("libelle"));
    }
    return result;
  }
     */
}
