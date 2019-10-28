package com.mohyehia.onlinebanking.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mohyehia.onlinebanking.controllers.BaseController;
import com.mohyehia.onlinebanking.entities.User;

@Controller
@RequestMapping(value = {"/admin/accounts", "admin/accounts/"})
public class AdminAccountsController extends BaseController{
	@GetMapping
	public String viewAccounts(Model model) {
		return "admin/accounts";
	}
	
	@ModelAttribute("title")
	public String getTitle() {
		return "Accounts Page";
	}
	
	@ModelAttribute("user")
	public User getUser() {
		return getCurrentUser();
	}
}
