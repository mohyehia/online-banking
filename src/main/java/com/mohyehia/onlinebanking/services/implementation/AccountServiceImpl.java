package com.mohyehia.onlinebanking.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohyehia.onlinebanking.dao.AccountDAO;
import com.mohyehia.onlinebanking.entities.Account;
import com.mohyehia.onlinebanking.services.framework.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

	private final AccountDAO accountDAO;
	
	@Autowired
	public AccountServiceImpl(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	@Override
	public List<Account> findAll() {
		return accountDAO.findAll();
	}

	@Override
	public List<Account> findByUserId(long userId) {
		return accountDAO.findByUserId(userId);
	}

	@Override
	public Account saveAccount(Account account) {
		if(findByUserId(account.getUserId()).size() == 3) {
			return null;
		}
		return accountDAO.save(account);
	}

}
