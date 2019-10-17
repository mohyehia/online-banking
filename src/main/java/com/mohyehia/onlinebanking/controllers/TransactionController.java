package com.mohyehia.onlinebanking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mohyehia.onlinebanking.entities.User;

@Controller
@RequestMapping("/transactions")
public class TransactionController extends BaseController{
	@GetMapping
	public String viewLogin(Model model) {
		model.addAttribute("title", "Transactions");
		return "transactions/index";
	}
	
	@GetMapping("/add")
	public String addTransaction() {
		return "transactions/add";
	}
	
	@ModelAttribute("user")
	public User getPrincipal() {
		return getCurrentUser();
	}
}
