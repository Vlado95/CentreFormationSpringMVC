/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.controllers;

import com.cefisi.modeles.Personne;
import com.cefisi.modeles.Promotion;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Vladimir
 */
@Controller
public class PersonneController{
    
    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/personnes", method = RequestMethod.GET)
     public String doGet(ModelMap map, HttpSession session 
    ) throws SQLException {
        System.out.println("/projet");
        //session.getAttribute("user");
        System.out.println("test" );
       // Personne user = (Personne) session.getAttribute("user");//request.getSession().getAttribute("user");
         //System.out.println("user: " + request.getSession().getAttribute("user"));
       //  System.out.println("userTest: " + user.getEmail());
        List<Personne> personnes = entityManager.createQuery("select p from Personne p").getResultList();
        map.put("personnes", personnes);
        return "personnes";
    }
     
     
     
    @Transactional
    @RequestMapping(value = "/personne-{idPersonne}", method = RequestMethod.GET)
    public String personne(ModelMap map, @PathVariable(value = "idPersonne") long idPersonne) throws SQLException {
        Personne personne = entityManager.find(Personne.class, idPersonne);
        Date today = new Date(Calendar.getInstance().getTimeInMillis());
//        List<Personne> membreB = entityManager.createQuery("select MP from Promotion P join P.etudiants MP where P.id = ?1 and MP.idPersonne not in " + "(select MB.idPersonne from Equipe E join E.membres MB join E.projet EP where EP.id = ?2)")
//                .setParameter(1, projet.getPromotion().getId())
//                .setParameter(2, idProjet)
//                .getResultList();
//        List<Equipe> equipes = projet.getEquipes();
//        LinkedHashSet<Equipe> equipesCl = new LinkedHashSet<Equipe>();
//        equipesCl.addAll(equipes);
//        equipes.clear();
//        equipes.addAll(equipesCl);

        //    System.out.println("nb students without groupe : " + membreB.size());
        map.put("personne", personne);
        //map.put("nb", nb);
        
        return "personne";
    }

  
    /**
	 *  le valeur d'une personne <code>promo</code> dans la JSP
	 *
	 * @return
	 */
//	@ModelAttribute("promo")
//	public Promotion getPromotion() throws SQLException {
//            String sql = "SELECT P FROM promotion P  WHERE P.id =:id_promotion";
//                
//		 Promotion promo = (Promotion) entityManager.createQuery(sql).setParameter("id_promotion", 1).getSingleResult();
//                        //(Promotion) entityManager.createNativeQuery()
//                      //  + "  ?2 BETWEEN P.debutDate AND P.finDate AND MP.idPersonne = ?3")
//                        //.setParameter(1,new Date(Calendar.getInstance().getTimeInMillis()))
//                      //  .setParameter(2,new Date(Calendar.getInstance().getTimeInMillis()))
//                      //  .setParameter(1, 3)
//                      //  .getSingleResult();
//                      return promo;
//        }

  
}
// List<Promotion> promotions = entityManager.createQuery("select p from Promotion p order by p.id").getResultList();
//        return promotions;