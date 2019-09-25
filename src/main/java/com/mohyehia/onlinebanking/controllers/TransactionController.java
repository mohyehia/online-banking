package com.mohyehia.onlinebanking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransactionController {
	@GetMapping("/transactions")
	public String viewLogin() {
		return "transactions/index";
	}
}
