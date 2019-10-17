package com.mohyehia.onlinebanking.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ForgotPasswordController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ForgotPasswordController.class);
	
	@GetMapping("/auth/forgot-password")
	public String viewForgotPassword() {
		return "users/forgot-password";
	}
	
	@PostMapping("/auth/forgot-password")
	public String forgotPassword(Model model) {
		LOG.info("Accessed This method!");
		return "users/forgot-password"; 
	}
}
