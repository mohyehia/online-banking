package com.mohyehia.onlinebanking.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mohyehia.onlinebanking.entities.User;

@Controller
public class UserController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	@GetMapping("/me")
	public String viewProfile(Model model) {
		model.addAttribute("title", "Profile");
		return "users/profile";
	}
	
	@PostMapping("/me")
	public String updateProfile() {
		LOGGER.info("Method called here!");;
		return "users/profile";
	}
	
	@ModelAttribute("user")
	public User getPrincipal() {
		return getCurrentUser();
	}
}
