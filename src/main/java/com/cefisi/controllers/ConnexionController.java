package com.cefisi.controllers;

import com.cefisi.modeles.Personne;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Connexion/déconnexion
 *
 * @author plasse
 */
@Controller
public class ConnexionController {

    @PersistenceContext
    private EntityManager entityManager;
    
    
        @RequestMapping(value = "/connexion", method = RequestMethod.GET)
    public String askConnexionForm(ModelMap map) {

        return "formConnexion";
    }
    
    
    

    @RequestMapping(value = "/connexion", method = RequestMethod.POST)
    public String connecter(HttpSession session,
            @RequestParam(value = "login", required = true) String login,
            @RequestParam(value = "password", required = true) String password,
            ModelMap map) throws SQLException {
         Personne user = new Personne();      
        Query query = entityManager.createQuery("select P from Personne P where P.email = ?1 and  P.password = ?2");
                query.setParameter(1, login);
                query.setParameter(2, password);
         user = (Personne) query.getSingleResult();

        //  Personne user = Personne.getByLoginPassword(login, password);
        if (user == null) {
            map.addAttribute("login", login);
            map.addAttribute("msgConnexion", "Utilisateur inconnu");
        } else {
            session.setAttribute("user", user);
        }
        return "index";
    }

    @RequestMapping(value = "/deconnexion", method = RequestMethod.POST)
    // Spring injecte la session Web dans session
    public String deconnecter(HttpSession session) {
//    session.removeAttribute("user");
        // Termine la session
        session.invalidate();
        return "formConnexion";
    }
}
