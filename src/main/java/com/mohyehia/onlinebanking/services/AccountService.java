package com.mohyehia.onlinebanking.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mohyehia.onlinebanking.entities.Account;
import com.mohyehia.onlinebanking.repositories.AccountRepository;

@Service
public class AccountService {
	private final AccountRepository accountRepository;
	
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	public List<Account> findAll(){
		return accountRepository.findAll();
	}
	
	public List<Account> findByUserId(Long userId){
		return accountRepository.findByUserId(userId);
	}
	
	public Account saveAccount(Account account) {
		return accountRepository.save(account);
	}
}
