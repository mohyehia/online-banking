package com.mohyehia.onlinebanking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignupController {
	@GetMapping("/auth/signup")
	public String viewSignup() {
		return "users/signup";
	}
}
