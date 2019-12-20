package com.mohyehia.onlinebanking.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mohyehia.onlinebanking.entities.User;
import com.mohyehia.onlinebanking.services.framework.UserService;

@Controller
public class SignupController {
	
	private static final Logger LOG = LoggerFactory.getLogger(SignupController.class);
	
	private final UserService userService;
	private final MessageSource messageSource;
	
	@Autowired
	public SignupController(UserService userService, MessageSource messageSource) {
		this.userService = userService;
		this.messageSource = messageSource;
	}
	
	@GetMapping("/auth/signup")
	public String viewSignup() {
		return "users/signup";
	}
	
	@PostMapping("/auth/signup")
	public String saveUser(@Valid @ModelAttribute("user") User user, Errors errors, RedirectAttributes attributes, Model model) {
		LOG.info(user.toString());
		if(errors.hasErrors()) {
			return "users/signup";
		}
		if(!user.getPassword().trim().equals(user.getConfirmPassword().trim())) {
			errors.rejectValue("confirmPassword", "SIGNUP.CONFIRM_PASSWORD");
		}
		if(errors.hasErrors()) {
			return "users/signup";
		}else {
			LOG.info("Request passed!");
			if(userService.save(user) != null) {
				attributes.addFlashAttribute("success", messageSource.getMessage("SIGNUP.ACCOUNT_CREATED_SUCCESSFULLY", new Object[] {}, Locale.ENGLISH));
				return "redirect:/auth/login";
			}else {
				model.addAttribute("err", messageSource.getMessage("SIGNUP.EMAIL_ALREADY_EXISTS", new Object[] {}, Locale.ENGLISH));
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
	
	@ModelAttribute("cities")
	public List<String> populateCities(){
		return Arrays.asList("Cairo", "Alex", "Giza", "Tanta");
	}
}
