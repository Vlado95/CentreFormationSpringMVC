package com.cefisi.dao;

import java.util.List;

import com.cefisi.modeles.Personne;


public interface UserDao {

	Personne findById(int idPersonne);
	
	///Personne findBySSO(String sso);
	
	void save(Personne user);
	
	//void deleteBySSO(String sso);
	
	List<Personne> findAllUsers();

}

