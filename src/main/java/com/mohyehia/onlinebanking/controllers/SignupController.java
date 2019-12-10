package com.mohyehia.onlinebanking.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mohyehia.onlinebanking.entities.User;
import com.mohyehia.onlinebanking.services.framework.UserService;

@Controller
public class SignupController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SignupController.class);
	
	private final UserService userService;
	
	@Autowired
	public SignupController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/auth/signup")
	public String viewSignup() {
		return "users/signup";
	}
	
	@PostMapping("/auth/signup")
	public String saveUser(@Valid @ModelAttribute User user, Errors errors, RedirectAttributes attributes, Model model) {
		if(!user.getPassword().trim().equals(user.getConfirmPassword().trim()))
			errors.rejectValue("confirmPassword", "user.confirmPassword", "Passwords must match.");
		if(errors.hasErrors()) {
			List<FieldError> fieldErrors = errors.getFieldErrors();
			for (FieldError fieldError : fieldErrors)
				LOGGER.error(fieldError.getDefaultMessage());
			
			model.addAttribute("errors", fieldErrors);
			return "users/signup";
		}else {
			LOGGER.info("Request passed!");
			if(userService.save(user) != null) {
				attributes.addFlashAttribute("success", "Your account has been created successfully, you can now  login with your credentials.");
				return "redirect:/auth/login";
			}else {
				model.addAttribute("err", "Email address already exists, please try again using a different email address.");
				return "users/signup";
			}
		}
	}
	
	@ModelAttribute("title")
	public String title() {
		return "Create New Account";
	}
	
	@ModelAttribute("user")
	public User getUser() {
		return new User();
	}
}
