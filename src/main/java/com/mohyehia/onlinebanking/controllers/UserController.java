package com.mohyehia.onlinebanking.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	public String changePassword(Model model, RedirectAttributes attributes,
			@RequestParam("currentPassword") String currentPassword,
			@RequestParam("newPassword") String newPassword,
			@RequestParam("confirmPassword") String confirmPassword) {
		LOGGER.info(currentPassword + ", " + newPassword + ", " + confirmPassword);
		List<String> errors = new ArrayList<>();
		if(null == currentPassword || currentPassword.trim().isEmpty())
			errors.add("Current password can not be empty!");
		if(null == newPassword || newPassword.trim().isEmpty())
			errors.add("New password can not be empty!");
		if(!newPassword.trim().equals(confirmPassword.trim()))
			errors.add("Confirmation password must equal new password!");
		LOGGER.info("errors size =>" + errors.size());
		if(errors.isEmpty()) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if(!encoder.matches(currentPassword, getCurrentUser().getPassword()))
				errors.add("Current password is not correct!");
		}
		if(!errors.isEmpty()) {
			attributes.addFlashAttribute("errors", errors);
			attributes.addFlashAttribute("title", "Change Password");
			return "redirect:me#password";
		}else {
			// save new password here!
			LOGGER.info("New password =>" + newPassword);
			User user = getCurrentUser();
			user.setPassword(newPassword);
			userService.update(user, user.getId(), user.getEmail());
			attributes.addFlashAttribute("success", "Your password has been changed successfully!");
			return "redirect:me";
		}
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
