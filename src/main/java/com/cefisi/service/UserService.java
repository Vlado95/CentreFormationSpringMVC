/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.service;

import com.cefisi.modeles.Personne;
import java.util.List;

/**
 *
 * @author Vladimir
 */
public interface UserService {
    
    /**
     *
     * @param id
     * @return une personne par son identifiant
     */
    Personne findById(long id);
	
	//User findBySSO(String sso);
	
	void saveUser(Personne user);
	
	void updateUser(Personne user);
	
	//void deleteUserBySSO(String sso);

	List<Personne> findAllUsers(); 
	
	//boolean isUserSSOUnique(Integer id, String sso);
       
        Personne getCurrentUser(); 
}
