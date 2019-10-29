package com.mohyehia.onlinebanking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohyehia.onlinebanking.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	List<Account> findByUserId(Long userId);
}
