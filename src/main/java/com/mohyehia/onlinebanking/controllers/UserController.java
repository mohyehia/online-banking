package com.mohyehia.onlinebanking.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mohyehia.onlinebanking.entities.User;
import com.mohyehia.onlinebanking.services.UserService;

@Controller
public class UserController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/me")
	public String viewProfile() {
		return "users/profile";
	}
	
	@PostMapping("/me")
	public String updateProfile(RedirectAttributes attributes, Model model, @RequestParam("firstName") String firstName, 
			@RequestParam("lastName") String lastName,
			@RequestParam("city") String city,
			@RequestParam("address") String address) {
		
		User user = getCurrentUser();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setCity(city);
		user.setAddress(address);
		
		if(userService.update(user, user.getId(), user.getEmail()) != null) {
			LOGGER.info("user saved!");
			attributes.addFlashAttribute("success", "Your data updated successfully!");
			return "redirect:/me";
		}else {
			model.addAttribute("error", "Error updating user data!");
			return "users/profile";
		}
	}
	
	@PostMapping("/change-password")
	public String changePassword(Model model, RedirectAttributes attributes, @RequestParam("currentPassword") String currentPassword,
			@RequestParam("newPassword") String newPassword,
			@RequestParam("confirmPassword") String confirmPassword) {
		LOGGER.info(currentPassword);
		LOGGER.info(newPassword);
		LOGGER.info(confirmPassword);
		attributes.addFlashAttribute("title", "Change Password");
		attributes.addFlashAttribute("error", "Current password is not correct!");
		return "redirect:me#password";
	}
	
	@ModelAttribute("user")
	public User getPrincipal() {
		return getCurrentUser();
	}
	
	@ModelAttribute("title")
	public String getTitle() {
		return "Profile";
	}
}
