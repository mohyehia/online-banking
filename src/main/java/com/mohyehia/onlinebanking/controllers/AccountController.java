package com.mohyehia.onlinebanking.controllers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mohyehia.onlinebanking.entities.Account;
import com.mohyehia.onlinebanking.entities.User;
import com.mohyehia.onlinebanking.services.AccountService;

@Controller
@RequestMapping("/accounts")
public class AccountController extends BaseController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);
	private final AccountService accountService;
	
	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@GetMapping
	public String viewAccounts(Model model) {
		model.addAttribute("title", "Accounts");
		List<Account> accounts = getUserAccounts(getCurrentUser().getId());
		LOG.info("Accounts size =>" + accounts.size());
		model.addAttribute("accountsSize", accounts.size());
		model.addAttribute("accounts", accounts);
		return "accounts/index";
	}
	
	@GetMapping("/add")
	public String viewAddAccount(Model model) {
		List<Account> accounts = getUserAccounts(getCurrentUser().getId());
		if(accounts.size() == 3) {
			LOG.info("Found 3 accounts in db. redirecting to the error page");
			return "redirect:/error";
		}else {
			model.addAttribute("title", "Add new account");
			Set<String> accountTypes = new HashSet<>(Arrays.asList("AC", "AT", "AS"));
			accounts.forEach(account -> accountTypes.remove(account.getAccountType()));
			LOG.info("Available account types =>" + accountTypes.size());
			model.addAttribute("accountTypes", accountTypes);
			return "accounts/add";
		}	
	}
	
	@PostMapping("/add")
	public String saveAccount(Model model, @ModelAttribute("account") Account account) {
		account.setUserId(getCurrentUser().getId());
		LOG.info(account.toString());
		return "accounts/add";
	}
	
	@GetMapping("/{accountId}/edit")
	public String viewEditAccount(@PathVariable Long accountId) {
		return "accounts/edit";
	}
	
	@GetMapping("/{accountId}/delete")
	public String viewDeleteAccount(@PathVariable Long accountId) {
		return "accounts/delete";
	}
	
	@ModelAttribute("user")
	public User getPrincipal() {
		return getCurrentUser();
	}
	
	@ModelAttribute("account")
	public Account getAccount() {
		return new Account();
	}
	
	@ModelAttribute("accounts")
	public List<Account> getUserAccounts(Long userId){
		return accountService.findByUserId(userId);
	}
}
