package com.elevysi.site.auth.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.elevysi.site.auth.entity.User;


@Repository
@Transactional
public class UserDAOImplement implements UserDAO{
	
	@PersistenceContext
	private EntityManager em;
	
//	public UserDAOImplement(EntityManager em){
//		this.em = em;
//	}
	
	public User save(User user){
		em.persist(user);
		em.flush();
		return user;
	}

}
