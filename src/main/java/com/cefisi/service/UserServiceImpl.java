/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.service;

import com.cefisi.dao.UserDao;
import com.cefisi.modeles.Personne;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vladimir
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao dao;

    @Override
    public Personne findById(long idPersonne) {
        return dao.findById(idPersonne);
    }

    @Override
    public void saveUser(Personne user) {
        dao.save(user);
    }

    @Override
    public void updateUser(Personne user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Personne> findAllUsers() {
        return dao.findAllUsers();
    }

    @Override
    public Personne getCurrentUser() {
        return dao.getCurrentUser();
    }

    @Override
    public Personne findByEmail(String email) {
        return dao.findByUserName(email);
    }

}
