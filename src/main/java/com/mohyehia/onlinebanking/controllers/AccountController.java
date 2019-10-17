package com.mohyehia.onlinebanking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mohyehia.onlinebanking.entities.User;

@Controller
@RequestMapping("/accounts")
public class AccountController extends BaseController {
	
	@GetMapping
	public String viewAccounts(Model model) {
		model.addAttribute("title", "Accounts");
		return "accounts/index";
	}
	
	@GetMapping("/add")
	public String viewAddAccount() {
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
}
