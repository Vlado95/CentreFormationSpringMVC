/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.controllers;

import com.cefisi.modeles.Equipe;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
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


/**
 *
 * @author Vladimir
 */
@Controller
public class EquipeController {
    
    
   @PersistenceContext
   private EntityManager entityManager;

  @RequestMapping(value = "/projet-{idProjet}-new-equipe", method = RequestMethod.GET)
  public String askNew(ModelMap map) {
    map.put("equipe", new Equipe());
    map.put("action", "Créer");
    map.put("titre", "Creer Equipe");
    return "formEquipe";
  }
  
  @Transactional
   @RequestMapping(value = "/projet-{idProjet}-new-equipe", method = RequestMethod.POST)
  public String doNew(@Valid @ModelAttribute("equipe") Equipe equipe,
      BindingResult result, ModelMap map, @PathVariable(value = "idProjet") long idProjet
  ) throws SQLException {
    map.put("action", "Créer");
    map.put("titre", "Créer une equipe");
    
    if (result.hasErrors()) {
      System.out.println("erreurs");
    }
    else {
        equipe.setIdProjet(idProjet);
      // Long idCreateur = null;
       // equipe.setIdCreateur( idCreateur);
        equipe.setDateCreation( new Date(Calendar.getInstance().getTimeInMillis()));
       //String resume = "lololo";
        //equipe.setResume(resume);
        
        entityManager.persist(equipe);
        entityManager.flush();
    //  equipe.insert();
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
    