package com.mohyehia.onlinebanking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	@GetMapping("/auth/login")
	public String viewLogin() {
		return "users/login";
	}
}
