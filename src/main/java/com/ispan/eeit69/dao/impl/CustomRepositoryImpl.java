package com.ispan.eeit69.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ispan.eeit69.dao.CustomRepository;
import com.ispan.eeit69.model.User;

@Repository
public class CustomRepositoryImpl implements CustomRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	public void detach(User user) {
		em.detach(user);

	}
}
