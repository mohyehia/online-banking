package com.mohyehia.onlinebanking.controllers.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mohyehia.onlinebanking.controllers.BaseController;
import com.mohyehia.onlinebanking.entities.User;

@Controller
@RequestMapping(value = {"/admin", "/admin/"})
public class AdminHomeController extends BaseController{
	private static final Logger LOG = LoggerFactory.getLogger(AdminHomeController.class);
	@GetMapping
	public String viewAdminHome() {
		LOG.info("Calling Admin home page");
		return "admin/index";
	}
	
	@ModelAttribute("title")
	public String getTitle() {
		return "Admin Page";
	}
	
	@ModelAttribute("user")
	public User getUser() {
		return getCurrentUser();
	}
}
