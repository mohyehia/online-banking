package com.mohyehia.onlinebanking.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mohyehia.onlinebanking.entities.Account;
import com.mohyehia.onlinebanking.repositories.AccountRepository;

@Service
public class AccountService {
	private final AccountRepository accountRepository;
	private final Logger LOG = LoggerFactory.getLogger(AccountService.class);
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
		if(findByUserId(account.getUserId()).size() == 3) {
			LOG.error("This user has 3 accounts and cannot add new account!");
			return null;
		}
		return accountRepository.save(account);
	}
}
