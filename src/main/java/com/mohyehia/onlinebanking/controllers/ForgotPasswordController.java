package com.mohyehia.onlinebanking.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mohyehia.onlinebanking.services.UserService;

@Controller
public class ForgotPasswordController {
	
	@Autowired
	private UserService userService;
	
	private static final Logger LOG = LoggerFactory.getLogger(ForgotPasswordController.class);
	
	@GetMapping("/auth/forgot-password")
	public String viewForgotPassword() {
		return "users/forgot-password";
	}
	
	@PostMapping("/auth/forgot-password")
	public String forgotPassword(Model model, @RequestParam("email") String email) {
		LOG.info("Email Address => " + email);
		if(email.trim().isEmpty()) {
			model.addAttribute("error", "Please provide a valid email address.");
			return "users/forgot-password";
		}
		//check if submitted email address exists in database
		if(userService.exists(email)) {
			LOG.info("email address exists!");
			// Send email address for user with link to reset his password
		} else {
			model.addAttribute("error", "This email address does not exist in database.");
			return "users/forgot-password";
		}
		return "users/forgot-password"; 
	}
}
