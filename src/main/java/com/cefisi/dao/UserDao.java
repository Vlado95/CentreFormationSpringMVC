package com.cefisi.dao;

import java.util.List;

import com.cefisi.modeles.Personne;


public interface UserDao {

	Personne findById(long idPersonne);
	
	Personne findByUserName(String user);
	
	void save(Personne user);
	
	//void deleteBySSO(String sso);
	
	List<Personne> findAllUsers();
        
        
        Personne getCurrentUser();

}

