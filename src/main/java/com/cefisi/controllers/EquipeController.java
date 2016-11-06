/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.controllers;

import com.cefisi.modeles.Equipe;
import com.cefisi.modeles.Personne;
import com.cefisi.modeles.Projet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;
import static jdk.nashorn.internal.objects.NativeArray.map;
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
 * @author Vladimir
 */
@Controller
public class EquipeController {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @RequestMapping(value = "/equipe-{idEquipe}", method = RequestMethod.GET)
    public String projet(ModelMap map, @PathVariable(value = "idEquipe") long idEquipe) throws SQLException {
        Equipe equipe = entityManager.find(Equipe.class, idEquipe);
        List<Personne> membreB = entityManager.createQuery("select P from Personne P where P.idPersonne not in " + "(select MB.idPersonne from Equipe E join E.membres MB where E.id = ?1)")
                .setParameter(1, idEquipe)
                .getResultList();

        System.out.println("nb equipes : " + membreB.size());
        map.put("equipe", equipe);
        map.put("membreB", membreB);
        return "equipe";
    }

      /*--creation de nouveau equipe--*/
    @RequestMapping(value = "/projet-{idProjet}-new-equipe", method = RequestMethod.GET)
    public String askNew(ModelMap map) {
        Equipe equipe = new Equipe();
        equipe.setCreateur(new Personne());
        map.put("equipe", new Equipe());
        map.put("action", "Créer");
        map.put("titre", "Creer Equipe");
        return "formEquipe";
    }

    @Transactional
    @RequestMapping(value = "/projet-{idProjet}-new-equipe", method = RequestMethod.POST)
    public String doNew(@Valid @ModelAttribute("equipe") Equipe equipe,
            BindingResult result, ModelMap map, @PathVariable(value = "idProjet") long idProjet, @RequestParam("createur.idPersonne") long idPersonne
    ) throws SQLException {
        Personne createur = entityManager.find(Personne.class, idPersonne);
        Projet projet = entityManager.find(Projet.class, idProjet);
        equipe.setCreateur(createur);
        map.put("action", "Créer");
        map.put("titre", "Créer une equipe");

        if (result.hasErrors()) {
            System.out.println("erreurs");
        } else {
            Set<Personne> membre = new HashSet<>(0);
            membre.add(createur);
            equipe.setMembres(membre);
            equipe.setProjet(projet);
            equipe.setDateCreation(new Date(Calendar.getInstance().getTimeInMillis()));
            entityManager.persist(equipe);
            entityManager.flush();
            System.out.println("ok");
            map.put("message", "Equipe enregistré");
        }
        return "formEquipe";
    }
    
    
    /*-- ajouter des personnes dans l'equipes-- */

    /**
     *
     * @param map
     * @return
     */

    @RequestMapping(value = "/equipe-{idEquipe}-new-membre", method = RequestMethod.GET)
    public String askNewMembre(ModelMap map, @PathVariable(value = "idEquipe") long idEquipe) {
      Equipe equipe = entityManager.find(Equipe.class, idEquipe); 
      Projet projet = equipe.getProjet();
              List<Personne> membreB = entityManager.createQuery("select MP from Promotion P join P.etudiants MP where P.id = ?1 and MP.idPersonne not in " + "(select MB.idPersonne from Equipe E join E.membres MB join E.projet EP where EP.id = ?2)")
                 .setParameter(1, projet.getPromotion().getId())
                 .setParameter(2,projet.getId())
                 .getResultList();
      
       System.out.println("membres: " + membreB.size());
        map.put("equipe", equipe);
        map.put("membreB", membreB);
        map.put("action", "Ajouter");
        map.put("titre", "Ajouter un nouveau membre");
        return "formEquipe";
    }
    
  /*  @RequestMapping(value = "/projet-{idProjet}-modifier",
            method = RequestMethod.GET)
    public String askModify(ModelMap map,
            @PathVariable(value = "idProjet") long idProjet) throws SQLException {
        Projet projet = entityManager.find(Projet.class, idProjet); 
        map.put("projet", projet);
        map.put("action", "Modifier");
        map.put("titre", "Modifier le projet n° " + projet.getId());
        return "formProjet";
    }*/
    
    @Transactional
    @RequestMapping(value = "/equipe-{idEquipe}-new-membre", method = RequestMethod.POST)
    public String doNewMembre(@Valid @ModelAttribute("equipe") Equipe equipe,
            BindingResult result, ModelMap map, @PathVariable(value = "idEquipe") long idEquipe, @RequestParam("createur.idPersonne") long idPersonne
    ) throws SQLException {
        Personne membre = entityManager.find(Personne.class, idPersonne);
        Equipe equipeOld = entityManager.find(Equipe.class, idEquipe);
        map.put("action", "Ajouter");
        map.put("titre", "Ajouter un nouveau membre");

        if (result.hasErrors()) {
            System.out.println("erreurs");
        } else {
            Set<Personne> membres = new HashSet<>(0);
            membres.addAll(equipeOld.getMembres());
            membres.add(membre);
           // equipe.setMembres((Set<Personne>) membre);
           
            equipe.setCreateur(equipeOld.getCreateur());
            equipe.setId(idEquipe);
            equipe.setMembres(membres);
            equipe.setProjet(equipeOld.getProjet());
            equipe.setDateCreation(equipeOld.getDateCreation());
            equipe.setResume(equipeOld.getResume());
            entityManager.merge(equipe);
            entityManager.flush();
            System.out.println("ok");
            map.put("message", "nouveau membre enregistré");
        }
        return "formEquipe";
    }

}
