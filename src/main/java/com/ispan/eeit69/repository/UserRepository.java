package com.ispan.eeit69.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ispan.eeit69.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	
	User findByUsername(String username);
	
	User findByEmail(String email);
	
	
	long count();
	
	long countByUsernameContaining(String keyword);
	
	Page<User> findByUsernameContaining(String keyword, Pageable pageable);
	
	

}