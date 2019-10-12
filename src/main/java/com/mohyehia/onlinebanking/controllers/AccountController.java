package com.mohyehia.onlinebanking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mohyehia.onlinebanking.entities.User;

@Controller
public class AccountController extends BaseController {
	
	@GetMapping("/accounts")
	public String viewAccounts(Model model) {
		model.addAttribute("title", "Accounts");
		return "accounts/index";
	}
	
	@ModelAttribute("user")
	public User getPrincipal() {
		return getCurrentUser();
	}
}
