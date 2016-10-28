/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.controllers;

import com.cefisi.modeles.Equipe;
import com.cefisi.modeles.Personne;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
      /*  List<Equipe> equipes = entityManager.createQuery("select E from Equipe E where E.idProjet = ?1 order by E.id")
                .setParameter(1, idProjet)
                .getResultList();
        System.out.println("nb membres : " + equipe.getMembres().getIdPersonne());*/
        map.put("equipe", equipe);
        //map.put("equipes", equipes);
        return "equipe";
    }
    
    
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
            BindingResult result, ModelMap map, @PathVariable(value = "idProjet") long idProjet ,@RequestParam("createur.idPersonne") long idPersonne
    ) throws SQLException {
        Personne createur = entityManager.find(Personne.class, idPersonne);
        equipe.setCreateur(createur);
        map.put("action", "Créer");
        map.put("titre", "Créer une equipe");

        if (result.hasErrors()) {
            System.out.println("erreurs");
        } else {
            Set<Personne> membre = new HashSet<>(0);
            membre.add(createur);
            equipe.setMembres(membre);
            equipe.setIdProjet(idProjet);
            equipe.setDateCreation(new Date(Calendar.getInstance().getTimeInMillis()));
            entityManager.persist(equipe);
            entityManager.flush();
            System.out.println("ok");
            map.put("message", "Equipe enregistré");
        }
        return "formEquipe";
    }

}

//Add new Employee object
/*       



stmt.setLong(1, 1);
      stmt.setLong(2, idCreateur);
      stmt.setDate(3, (java.sql.Date) new Date(Calendar.getInstance().getTimeInMillis()));
      stmt.setString(4, resume);








EmployeeEntity emp = new EmployeeEntity();
        emp.setEmail("lokesh@mail.com");
        emp.setFirstName("lokesh");
        emp.setLastName("gupta");
         
        //Save the employee in database
        session.save(emp);
 
        //Commit the transaction
        session.getTransaction().commit();
        HibernateUtil.shutdown();*/
