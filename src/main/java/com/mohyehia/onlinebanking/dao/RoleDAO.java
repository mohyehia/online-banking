package com.mohyehia.onlinebanking.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mohyehia.onlinebanking.entities.Role;

public interface RoleDAO extends JpaRepository<Role, Long> {

}
