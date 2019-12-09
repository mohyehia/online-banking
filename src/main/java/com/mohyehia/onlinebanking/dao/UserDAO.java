package com.mohyehia.onlinebanking.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mohyehia.onlinebanking.entities.User;

public interface UserDAO extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
	Optional<User> findByIdAndEmail(Long userId, String email);
}
