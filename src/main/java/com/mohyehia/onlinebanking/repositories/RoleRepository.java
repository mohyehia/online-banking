package com.mohyehia.onlinebanking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohyehia.onlinebanking.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
}
