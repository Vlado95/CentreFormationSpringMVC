package com.cefisi.dao;

import com.cefisi.modeles.Personne;
//import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository /*extends JpaRepository<Personne, Long> */{
    
    Personne findByEmail(String email);

//    @Override
//    void delete(Personne user);
    
    void save(Personne user);

}
