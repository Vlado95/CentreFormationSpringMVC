/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.controllers;

import com.cefisi.modeles.Personne;
import com.cefisi.modeles.Promotion;
import com.cefisi.service.UserService;
import com.cefisi.validation.EnvoiMail;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



//import java.util.Properties;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;

/**
 *
 * @author Vladimir
 */
@Controller
public class PersonneController{
    
    @Autowired
    UserService userService;
    
    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/personnes", method = RequestMethod.GET)
     public String doGet(ModelMap map, HttpSession session 
    ) throws SQLException {
        System.out.println("/projet");
        System.out.println("test" );
        List<Personne> personnes = userService.findAllUsers();
        //List<Personne> personnes = entityManager.createQuery("select p from Personne p").getResultList();
        map.put("personnes", personnes);
        return "personnes";
    }
     
     
     
     @RequestMapping(value = "/email", method = RequestMethod.GET)
     public String validate(ModelMap map
    ) throws SQLException, MessagingException/*,AddressException, MessagingException */{
         
         String adresse = "tvradmir@yahoo.fr";
         String object ="Validation d'inscription";
         String message = "Veuillez valider votre inscription en cliquant sur ce lien \n" + "\n<a href='http://localhost:8084/CentreFormationSpringMVC'>Test.com</a>";
         EnvoiMail.envoyer(adresse, object, message);
         return "index";
    }
     
     
     
    @Transactional
    @RequestMapping(value = "/personne-{idPersonne}", method = RequestMethod.GET)
    public String personne(ModelMap map, @PathVariable(value = "idPersonne") long idPersonne) throws SQLException {
        Personne personne = userService.findById(idPersonne);//entityManager.find(Personne.class, idPersonne);
        Date today = new Date(Calendar.getInstance().getTimeInMillis());
        map.put("personne", personne);
        return "personne";
    }

}
