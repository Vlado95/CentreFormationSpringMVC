/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.dao;

import com.cefisi.modeles.UploadFile;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vladimir
 */
@Repository
public class FileUploadDAOImpl implements FileUploadDAO {
	
    @PersistenceContext
    private EntityManager sessionFactory;
	
//	public FileUploadDAOImpl() {
//	}
//
//	public FileUploadDAOImpl(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
//
//	@Override
//	@Transactional
//	public void save(UploadFile uploadFile) {
//		sessionFactory.getCurrentSession().save(uploadFile);
//	}

    @Override
    public void save(UploadFile uploadFile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
