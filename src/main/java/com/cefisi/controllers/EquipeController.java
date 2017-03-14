/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.controllers;

import com.cefisi.dao.PersistanceDao;
import com.cefisi.modeles.Equipe;
import com.cefisi.modeles.Personne;
import com.cefisi.modeles.Projet;
import com.cefisi.modeles.UploadFile;
import com.cefisi.service.CustomUser;
import com.cefisi.service.UserService;
import com.cefisi.service.UtiService;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import static jdk.nashorn.internal.objects.NativeArray.map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    
    @Autowired
    UserService userService;
    
    @Autowired
    UtiService service;
    
    @Transactional
    @RequestMapping(value = "/equipe-{idEquipe}", method = RequestMethod.GET)
    public String projet(ModelMap map, @PathVariable(value = "idEquipe") long idEquipe) throws SQLException {
        Equipe equipe = service.findByID(idEquipe);
        List<UploadFile> files = service.findFilesEquipe(idEquipe);
        List<Personne> membreB = service.findPersoSansEquipe(equipe.getProjet().getPromotion().getId(), equipe.getProjet().getId());
        System.out.println("nb equipes : " + membreB.size());
        map.put("files", files);
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
            BindingResult result, ModelMap map, @PathVariable(value = "idProjet") long idProjet//, 
    //HttpSession session
    ) throws SQLException {
        Personne createur2 = userService.getCurrentUser();
        Projet projet = service.findProjectByID(idProjet); 
        equipe.setCreateur(createur2);
        map.put("action", "Créer");
        map.put("titre", "Créer une equipe");
        
        if (result.hasErrors()) {
            System.out.println("erreurs");
        } else {
            Set<Personne> membre = new HashSet<>(0);
            membre.add(createur2);
            equipe.setMembres(membre);
            equipe.setProjet(projet);
            equipe.setDateCreation(new Date(Calendar.getInstance().getTimeInMillis()));
            service.save(equipe);
            System.out.println("ok");
            System.out.println("createur3: " + createur2.getIdPersonne());
            map.put("message", "Equipe enregistré");
        }
        return "redirect:/projet-{idProjet}";
    }

    /*--modifier equipe--*/
    @RequestMapping(value = "/equipe-{idEquipe}-modifier", method = RequestMethod.GET)
    public String askModify(ModelMap map, @PathVariable(value = "idEquipe") long idEquipe) {
        Equipe equipe = service.findByID(idEquipe);
        map.put("equipe", equipe);
        map.put("action", "Modifier");
        map.put("titre", "Modifier Equipe n°" + equipe.getId());
        return "formEquipe";
    }
    
    @Transactional
    @RequestMapping(value = "/equipe-{idEquipe}-modifier", method = RequestMethod.POST)
    public String doModify(@Valid @ModelAttribute("equipe") Equipe equipe,
            BindingResult result, ModelMap map, @PathVariable(value = "idEquipe") long idEquipe,
            @RequestParam("resume") String resume
    ) throws SQLException {
        map.put("action", "Modifier");
        map.put("titre", "Modifier equipe");
        
        if (result.hasErrors()) {
            System.out.println("erreurs");
        } else {
            service.update(resume, idEquipe);
            System.out.println("ok");
            map.put("message", "Equipe a été modifiée");
        }
        return "redirect:/equipe-" + idEquipe; 
    }

    /*-- ajouter des personnes dans l'equipes-- */
    /**
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/equipe-{idEquipe}-new-membre", method = RequestMethod.GET)
    public String askNewMembre(ModelMap map, @PathVariable(value = "idEquipe") long idEquipe) {
        Equipe equipe = service.findByID(idEquipe);        
        Projet projet = equipe.getProjet();
        List<Personne> membreB = service.findPersoSansEquipe(projet.getPromotion().getId(), projet.getId()); 
        System.out.println("membres: " + membreB.size());
        map.put("equipe", equipe);
        map.put("membreB", membreB);
        map.put("action", "Ajouter");
        map.put("titre", "Ajouter un nouveau membre");
        return "formEquipe";
    }
    
    @Transactional
    @RequestMapping(value = "/equipe-{idEquipe}-new-membre", method = RequestMethod.POST)
    public String doNewMembre(@Valid @ModelAttribute("equipe") Equipe equipe, HttpSession session,
            BindingResult result, ModelMap map, @PathVariable(value = "idEquipe") long idEquipe, @RequestParam("createur.idPersonne") long idPersonne
    ) throws SQLException {
        Personne membre = userService.findById(idPersonne); 
        Equipe equipeOld = service.findByID(idEquipe);
        map.put("action", "Ajouter");
        map.put("titre", "Ajouter un nouveau membre");
        
        if (result.hasErrors()) {
            System.out.println("erreurs");
        } else {
            Set<Personne> membres = new HashSet<>(0);
            membres.addAll(equipeOld.getMembres());
            membres.add(membre);
            equipe.setCreateur(equipeOld.getCreateur());
            equipe.setId(idEquipe);
            equipe.setMembres(membres);
            equipe.setProjet(equipeOld.getProjet());
            equipe.setDateCreation(equipeOld.getDateCreation());
            equipe.setResume(equipeOld.getResume());
            service.addNewMember(equipe);
            System.out.println("ok");
            Long idProjet = equipeOld.getProjet().getId();
            map.put("message", "nouveau membre enregistré");
        }
        return "redirect:/projet-" + equipeOld.getProjet().getId();
    }
    
    @RequestMapping(value = "/equipe-{idEquipe}/{idPersonne}-sup-membre", method = RequestMethod.GET)
    public String askSupMembre(ModelMap map, @PathVariable(value = "idEquipe") long idEquipe, @PathVariable(value = "idPersonne") long idPersonne) {
        Equipe equipe = service.findByID(idEquipe);       
        Projet projet = equipe.getProjet();
        Personne membre = userService.findById(idPersonne); 
        System.out.println("membres: " + membre);
        map.put("equipe", equipe);
        map.put("membre", membre);
        map.put("action", "Suprimer");
        map.put("titre", "Suprime un  membre");
        return "formEquipe";
    }
    
    @Transactional
    @RequestMapping(value = "/equipe-{idEquipe}/{idPersonne}-sup-membre", method = RequestMethod.POST)
    public String doSupMembre(@Valid @ModelAttribute("equipe") Equipe equipe,
            BindingResult result, ModelMap map, @PathVariable(value = "idEquipe") long idEquipe, @PathVariable(value = "idPersonne") long idPersonne
    ) throws SQLException {
        Personne membre = userService.findById(idPersonne);
        equipe = service.findByID(idEquipe);
        map.put("action", "Suprimer");
        map.put("titre", "Suprimer un membre");
        
        if (result.hasErrors()) {
            System.out.println("erreurs");
        } else {
            service.deleteMember(membre.getIdPersonne(), idEquipe);
            System.out.println("ok");
            map.put("message", "la personne a été bien suprimée");
        }
        return "redirect:/projet-" + equipe.getProjet().getId();
    }
    
}
