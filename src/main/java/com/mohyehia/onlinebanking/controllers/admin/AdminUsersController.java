package com.mohyehia.onlinebanking.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mohyehia.onlinebanking.controllers.BaseController;
import com.mohyehia.onlinebanking.entities.User;

@Controller
@RequestMapping(value = {"/admin/users", "admin/users/"})
public class AdminUsersController extends BaseController {
	@GetMapping
	public String viewUsers(Model model) {
		return "admin/users";
	}
	
	@ModelAttribute("title")
	public String getTitle() {
		return "Users Page";
	}
	
	@ModelAttribute("user")
	public User getUser() {
		return getCurrentUser();
	}
}
