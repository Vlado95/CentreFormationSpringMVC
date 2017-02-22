/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cefisi.modeles.Personne;
import org.springframework.validation.ValidationUtils;

/**
 *
 * @author Vladimir
 */
public class UserValidation implements Validator {

    @Override
    public boolean supports(Class clazz) {
//		//just validate the Personne instances
        return Personne.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nom",
                "required.nom", "Field name is required.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prenom",
                "required.prenom", "Field name is required.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
                "required.email", "Field name is required.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmEmail",
                "required.confirmEmail", "Field name is required.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
                "required.password", "Field name is required.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
                "required.confirmPassword", "Field name is required.");

        Personne cust = (Personne) target;

        if (!(cust.getEmail().equals(cust.getConfirmEmail()))) {
            errors.rejectValue("email", "notmatch.email");
        }
        
        if (!(cust.getPassword().equals(cust.getConfirmPassword()))) {
            errors.rejectValue("password", "notmatch.password");
        }

    }

}
