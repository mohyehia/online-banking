package com.mohyehia.onlinebanking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mohyehia.onlinebanking.entities.User;

@Controller
public class UserController extends BaseController {
	@GetMapping("/me")
	public String viewProfile() {
		return "users/profile";
	}
	
	@ModelAttribute("user")
	public User getPrincipal() {
		return getCurrentUser();
	}
}
