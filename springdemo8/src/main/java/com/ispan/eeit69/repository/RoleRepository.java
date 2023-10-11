package com.ispan.eeit69.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ispan.eeit69.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	    Optional<Role> findByName(String name);
	}

