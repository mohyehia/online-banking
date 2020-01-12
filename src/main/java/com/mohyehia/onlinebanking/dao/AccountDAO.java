package com.mohyehia.onlinebanking.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mohyehia.onlinebanking.entities.Account;

public interface AccountDAO extends JpaRepository<Account, Long> {
	List<Account> findByUserId(long userId);
	Optional<Account> findByIdAndUserId(long id, long userId);
}
