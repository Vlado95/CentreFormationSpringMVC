/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.controllers;

import com.cefisi.modeles.Personne;
import com.cefisi.validation.UserValidation;
import java.sql.SQLException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author Vladimir
 */
@Controller
public class RegistrationController {

//    UserValidation userValidator = new UserValidation();
//
//    @Autowired
//    public RegistrationController(UserValidation userValidator) {
//        this.userValidator = userValidator;
//    }
    @RequestMapping(value = "/reg-new-user", method = RequestMethod.GET)
    public String askNewUser(ModelMap map, ServletRequest request) {

        final HttpServletRequest req = (HttpServletRequest) request;
        HttpServletRequest requesti = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Personne user = new Personne();
        //projet.setPromotion(new Promotion());
        // projet.setCreateur(new Personne());
       /* map.put("url2", requesti.getRequestURL());
        map.put("url", req.getServletPath());*/
        map.put("user", user);
        map.put("action", "Enregistre");
        map.put("titre", "Creer un nouveau utilisateur");
        return "registrationForm";
    }

    @Transactional
    @RequestMapping(value = "/reg-new-user", method = RequestMethod.POST)
    public String doNewUser(@Valid @ModelAttribute("user") Personne user,
            BindingResult result, ModelMap map, SessionStatus status
    ) throws SQLException {

        UserValidation userValidator = new UserValidation();
        userValidator.validate(user, result);

        map.put("action", "Enregistre");
        map.put("titre", "Creer un nouveau utilisateur");
        if (result.hasErrors()) {
            //if validator failed
            return "registrationForm";
        } else {
            status.setComplete();
            //form success
            return "registrationSuccess";
        }
        // return "registrationSuccess";
    }

}
