/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.dao;

import com.cefisi.modeles.Adresse;
import com.cefisi.modeles.Personne;
//import org.springframework.data.jpa.repository.JpaRepository;


public interface AdresseRepository /*extends JpaRepository<Adresse, Long> */{
    
    Adresse findByPersonnes(Personne user);
    
    Adresse save(Adresse adresse);

}

