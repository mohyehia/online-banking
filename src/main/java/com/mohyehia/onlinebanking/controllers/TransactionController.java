package com.mohyehia.onlinebanking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mohyehia.onlinebanking.entities.User;

@Controller
public class TransactionController extends BaseController{
	@GetMapping("/transactions")
	public String viewLogin(Model model) {
		model.addAttribute("title", "Transactions");
		return "transactions/index";
	}
	
	@ModelAttribute("user")
	public User getPrincipal() {
		return getCurrentUser();
	}
}
