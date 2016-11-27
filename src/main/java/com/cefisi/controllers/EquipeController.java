/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.controllers;

import com.cefisi.modeles.Equipe;
import com.cefisi.modeles.Personne;
import com.cefisi.modeles.Projet;
import com.cefisi.modeles.UploadFile;
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
        List<UploadFile> files = entityManager.createQuery("select F from UploadFile F JOIN F.equipe FE where FE.id =?1")
                .setParameter(1, idEquipe)
                .getResultList();
        List<Personne> membreB = entityManager.createQuery("select MP from Promotion P join P.etudiants MP where P.id = ?1 and MP.idPersonne not in " + "(select MB.idPersonne from Equipe E join E.membres MB join E.projet EP where EP.id = ?2)")
                .setParameter(1, equipe.getProjet().getPromotion().getId())
                .setParameter(2, equipe.getProjet().getId())
                .getResultList();
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
            BindingResult result, ModelMap map, @PathVariable(value = "idProjet") long idProjet, 
            HttpSession session
    ) throws SQLException {
        Personne createur2 =  (Personne) session.getAttribute("user");
        Projet projet = entityManager.find(Projet.class, idProjet);
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
            entityManager.persist(equipe);
            entityManager.flush();
            System.out.println("ok");
             System.out.println("createur3: " + createur2.getIdPersonne());
            map.put("message", "Equipe enregistré");
        }
        return "redirect:/projet-{idProjet}";
    }
    
    
    
      /*--modifier equipe--*/
    @RequestMapping(value = "/equipe-{idEquipe}-modifier", method = RequestMethod.GET)
    public String askModify(ModelMap map, @PathVariable(value = "idEquipe") long idEquipe) {
        Equipe equipe = entityManager.find(Equipe.class, idEquipe);
        map.put("equipe", equipe);
        map.put("action", "Modifier");
        map.put("titre", "Modifier Equipe n°" +equipe.getId());
        return "formEquipe";
    }

    @Transactional
    @RequestMapping(value = "/equipe-{idEquipe}-modifier", method = RequestMethod.POST)
    public String doModify(@Valid  @ModelAttribute("equipe") Equipe equipe,
            BindingResult result, ModelMap map , @PathVariable(value = "idEquipe") long idEquipe,
            @RequestParam("resume") String resume
          //  HttpSession session
    ) throws SQLException {
       // Personne createur2 =  (Personne) session.getAttribute("user");
        //equipe.setId(idEquipe);
        map.put("action", "Modifier");
        map.put("titre", "Modifier equipe");

        if (result.hasErrors()) {
            System.out.println("erreurs");
        } else {
            String sql = "UPDATE equipe SET resume=:resume WHERE id_equipe=:id_equipe";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("resume",resume)
                    .setParameter("id_equipe", idEquipe);
            query.executeUpdate();
            System.out.println("ok");
            map.put("message", "Equipe a été modifiée");
        }
        return "redirect:/equipe-"+idEquipe; ///projet-"+equipe.getProjet().getId();
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
  
    
    @Transactional
    @RequestMapping(value = "/equipe-{idEquipe}-new-membre", method = RequestMethod.POST)
    public String doNewMembre(@Valid @ModelAttribute("equipe") Equipe equipe,  HttpSession session,
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
            Long idProjet = equipeOld.getProjet().getId();
            map.put("message", "nouveau membre enregistré");
        }
        return "redirect:/projet-"+equipeOld.getProjet().getId();
    }
    
    @RequestMapping(value = "/equipe-{idEquipe}/{idPersonne}-sup-membre", method = RequestMethod.GET)
    public String askSupMembre(ModelMap map, @PathVariable(value = "idEquipe") long idEquipe, @PathVariable(value = "idPersonne") long idPersonne) {
      Equipe equipe = entityManager.find(Equipe.class, idEquipe); 
      Projet projet = equipe.getProjet();
        Personne membre = entityManager.find(Personne.class, idPersonne);
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
        Personne membre = entityManager.find(Personne.class, idPersonne);
        equipe = entityManager.find(Equipe.class, idEquipe);
        map.put("action", "Suprimer");
        map.put("titre", "Suprimer un membre");

        if (result.hasErrors()) {
            System.out.println("erreurs");
        } else {
//            Set<Personne> membres = new HashSet<>(0);
//            membres.addAll(equipeOld.getMembres());
//            membres.add(membre);
//            equipe.setCreateur(equipeOld.getCreateur());
//            equipe.setId(idEquipe);
//            equipe.setMembres(membres);
//            equipe.setProjet(equipeOld.getProjet());
//            equipe.setDateCreation(equipeOld.getDateCreation());
//            equipe.setResume(equipeOld.getResume());
//            entityManager.merge(equipe);
//            entityManager.flush();
            String sql = "Delete FROM membre_equipe WHERE id_equipe=:id_equipe AND id_personne=:id_personne";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("id_personne", membre.getIdPersonne())
                 .setParameter("id_equipe", idEquipe);
            query.executeUpdate();
            System.out.println("ok");
            map.put("message", "la personne a été bien suprimée");
        }
        return "redirect:/projet-"+equipe.getProjet().getId();
    }


}
