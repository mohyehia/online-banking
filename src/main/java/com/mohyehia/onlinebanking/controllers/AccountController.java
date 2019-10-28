package com.mohyehia.onlinebanking.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mohyehia.onlinebanking.entities.Account;
import com.mohyehia.onlinebanking.entities.User;

@Controller
@RequestMapping("/accounts")
public class AccountController extends BaseController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);
	
	@GetMapping
	public String viewAccounts(Model model) {
		model.addAttribute("title", "Accounts");
		return "accounts/index";
	}
	
	@GetMapping("/add")
	public String viewAddAccount(Model model) {
		model.addAttribute("title", "Add new account");
		return "accounts/add";
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
}
