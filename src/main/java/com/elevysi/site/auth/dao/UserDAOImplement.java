package com.elevysi.site.auth.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import com.elevysi.site.auth.entity.User;
import com.elevysi.site.auth.entity.User_;


@Repository
@Transactional
public class UserDAOImplement implements UserDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	public User save(User user){
		em.persist(user);
		em.flush();
		return user;
	}
	
	public User loadByUsername(String username){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> criteria = cb.createQuery(User.class);
		Root<User> queryRoot = criteria.from(User.class);
		Predicate condition = cb.equal(queryRoot.get(User_.username), username);
		criteria.where(condition);
		TypedQuery<User> query = em.createQuery(criteria);
		User user = query.getSingleResult();
		
		return user;
	}

}
