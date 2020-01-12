package com.mohyehia.onlinebanking.services.framework;

import java.util.List;

import com.mohyehia.onlinebanking.entities.Account;

public interface AccountService {
	List<Account> findAll();
	List<Account> findByUserId(long userId);
	Account saveAccount(Account account);
	Account findByIdAndUserId(long id, long userId);
}
