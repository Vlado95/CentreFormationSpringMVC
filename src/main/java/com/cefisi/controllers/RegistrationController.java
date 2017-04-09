/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.controllers;

import com.cefisi.modeles.Adresse;
import com.cefisi.modeles.Personne;
import com.cefisi.registration.OnRegistrationCompleteEvent;
import com.cefisi.service.IUserService;
import com.cefisi.service.UserService;
import com.cefisi.validation.UserValidation;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

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
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private IUserService userService;
    
    @Autowired
    private UserService personService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

//    @RequestMapping(value = "/reg-new-user", method = RequestMethod.GET)
//    public String askNewUser(ModelMap map, ServletRequest request) {
//
//        final HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletRequest requesti = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        Personne user = new Personne();
//
//        /* map.put("url2", requesti.getRequestURL());
//        map.put("url", req.getServletPath());*/
//        map.put("user", user);
//        map.put("action", "Enregistre");
//        map.put("titre", "Creer un nouveau utilisateur");
//        return "registrationForm";
//    }
    @RequestMapping(value = "/reg-new-user", method = RequestMethod.GET)
    public String showRegistrationForm(final HttpServletRequest request, final Model model) {
        //LOGGER.debug("Rendering registration page.");
        final Personne accountDto = new Personne();
        model.addAttribute("user", accountDto);
        model.addAttribute("action", "Enregistre");
        model.addAttribute("titre", "Creer un nouveau utilisateur");
        return "registrationForm";
    }

//    @Transactional
//    @RequestMapping(value = "/reg-new-user", method = RequestMethod.POST)
//    public String doNewUser(@Valid @ModelAttribute("user") Personne user,
//            BindingResult result, ModelMap map, SessionStatus status
//    ) throws SQLException {
//
//        UserValidation userValidator = new UserValidation();
//        userValidator.validate(user, result);
//
//        Adresse adresse = new Adresse();
//        adresse.setRue(user.getRue());
//        adresse.setVille(user.getVille());
//        adresse.setCodePostal(user.getCodePostal());
//        adresse.setMobile(user.getMobile());
//        adresse.setFixe(user.getFixe());
//        adresse.setPays(user.getPays());
//        user.setProfil(Boolean.FALSE);
//        Set<Personne> newUsers = new HashSet<Personne>();
//        newUsers.add(user);
//        adresse.setPersonnes(newUsers);
//
//        map.put("action", "Enregistre");
//        map.put("titre", "Creer un nouveau utilisateur");
//        map.put("newpersonne", user);
//        if (result.hasErrors()) {
//            //if validator failed
//            return "registrationForm";
//        } else {
//            status.setComplete();
//            System.out.println("userId " + user.getIdPersonne());
//            System.out.println("user " + user.getNom());
//            System.out.println("user " + user.getRue());
//            System.out.println("adresse " + adresse.getVille());
//            entityManager.persist(adresse);
//            entityManager.flush();
//            System.out.println("ok");
//            //form success
//            return "registrationSuccess";
//        }
//        // return "registrationSuccess";
//    }
    @Transactional
    @RequestMapping(value = "/reg-new-user", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(@Valid @ModelAttribute("user") final Personne userDto,
            final HttpServletRequest request, final Errors errors, BindingResult result)throws SQLException  {
        // LOGGER.debug("Registering user account with information: {}", userDto);
        UserValidation userValidator = new UserValidation();
        System.err.println("com.cefisi.controllers.RegistrationController.registerUserAccount() Publish event 0");
        userValidator.validate(userDto, result);
//
System.err.println("com.cefisi.controllers.RegistrationController.registerUserAccount() Publish event 1");
        final Adresse registered = userService.registerNewUserAccount(userDto);
        System.err.println("com.cefisi.controllers.RegistrationController.registerUserAccount() Publish event 2");
//        if (registered == null) {
        if (result.hasErrors()) {
            // result.rejectValue("email", "message.regError");
            return new ModelAndView("registrationForm", "user", userDto);
        }else{
        //try {
        Personne user = personService.findByEmail(userDto.getEmail());
            System.out.println("com.cefisi.controllers.RegistrationController.registerUserAccount() user email"+user.getIdPersonne());
            final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            System.err.println("com.cefisi.controllers.RegistrationController.registerUserAccount() Publish event 3");
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl, user));
      /*  } catch (final Exception ex) {
            // LOGGER.warn("Unable to register user", ex);
            return new ModelAndView("registrationForm", "user", userDto);
        }*/
        return new ModelAndView("registrationSuccess", "user", userDto);
    }
    }

}
