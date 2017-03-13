package com.cefisi.dao;

import com.cefisi.modeles.Personne;
import com.cefisi.service.CustomUser;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.security.core.context.SecurityContextHolder;

/*import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;*/
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vladimir
 */
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    /*public Personne findById(int id) {
		Personne user = getByKey(id);
		return user;
	}*/

 /*public User findBySSO(String sso) {
		System.out.println("SSO : "+sso);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User)crit.uniqueResult();
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<User> users = (List<User>) criteria.list();
		
		return users;
	}

	public void save(User user) {
		persist(user);
	}

	public void deleteBySSO(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User)crit.uniqueResult();
		delete(user);
	}*/
    @Override
    public Personne findById(int idPersonne) {
        Personne personne = entityManager.find(Personne.class, idPersonne);
        return personne;
    }

    @Override
    public void save(Personne user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Personne> findAllUsers() {
        List<Personne> personnes = entityManager.createQuery("select p from Personne p").getResultList();
        return personnes;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Personne findByUserName(String username) {

        Personne user = new Personne();
        Query query = entityManager.createQuery("select P from Personne P where P.email = ?1");
        query.setParameter(1, username);
        user = (Personne) query.getSingleResult();
        return user;

    }

    @Override
    public Personne getCurrentUser() {
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getIdPersonne();
        return entityManager.find(Personne.class,id);
    }

}
