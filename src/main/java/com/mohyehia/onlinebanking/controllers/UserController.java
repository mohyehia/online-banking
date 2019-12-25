package com.mohyehia.onlinebanking.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mohyehia.onlinebanking.entities.User;
import com.mohyehia.onlinebanking.services.framework.UserService;

@Controller
public class UserController extends BaseController {
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final MessageSource messageSource;
	
	@Autowired
	public UserController(UserService userService, BCryptPasswordEncoder passwordEncoder, MessageSource messageSource) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.messageSource = messageSource;
	}
	
	
	@GetMapping("/me")
	public String viewProfile() {
		return "users/profile";
	}
	
	@PostMapping("/me")
	public String updateProfile(RedirectAttributes attributes, Model model,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("city") String city,
			@RequestParam("address") String address) {
		
		// Validate user data
		List<String> errors = validateUserData(firstName, lastName, city, address);
		LOG.info("errors size =>" + errors.size());
		if(errors.size() > 0) {
			model.addAttribute("errors", errors);
			return "users/profile";
		}else {
			User user = getCurrentUser();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setCity(city);
			user.setAddress(address);
			
			if(userService.update(user, user.getId(), user.getEmail()) != null) {
				LOG.info("user saved!");
				attributes.addFlashAttribute("success", messageSource.getMessage("PROFILE_UPDATED", new Object[] {}, Locale.ENGLISH));
				return "redirect:/me";
			}else {
				model.addAttribute("error", messageSource.getMessage("ERROR_UPDATING_PROFILE", new Object[] {}, Locale.ENGLISH));
				return "users/profile";
			}
		}
	}
	
	@PostMapping("/change-password")
	public String changePassword(Model model, RedirectAttributes attributes,
			@RequestParam("currentPassword") String currentPassword,
			@RequestParam("newPassword") String newPassword,
			@RequestParam("confirmPassword") String confirmPassword) {
		LOG.info(currentPassword + ", " + newPassword + ", " + confirmPassword);
		
		List<String> errors = validateChangePassword(currentPassword, newPassword, confirmPassword);
		LOG.info("errors size =>" + errors.size());
		if(errors.isEmpty()) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if(!encoder.matches(currentPassword, getCurrentUser().getPassword()))
				errors.add(messageSource.getMessage("CURRENT_PASSWORD_NOT_CORRECT", new Object[] {}, Locale.ENGLISH));
		}
		if(!errors.isEmpty()) {
			attributes.addFlashAttribute("passwordErrors", errors);
			attributes.addFlashAttribute("title", "Change Password");
			return "redirect:me#password";
		}else {
			// save new password here!
			LOG.info("New password =>" + newPassword);
			User user = getCurrentUser();
			user.setPassword(passwordEncoder.encode(newPassword));
			LOG.info("New encoded password =>" + user.getPassword());
			userService.update(user, user.getId(), user.getEmail());
			// send an email to the user notifying him that his password has changed
			attributes.addFlashAttribute("success", messageSource.getMessage("PASSWORD_CHANGED_SUCCESSFULLY", new Object[] {}, Locale.ENGLISH));
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
	
	private List<String> validateUserData(String firstName, String lastName, String city, String address){
		List<String> errors = new ArrayList<>();
		if(null == firstName || firstName.trim().isEmpty())
			errors.add("First name cannot be empty!");
		else if(firstName.length() < 3 || firstName.length() > 10)
			errors.add("First name must be between 3 and 10 characters inclusive!");
		
		if(null == lastName || lastName.trim().isEmpty())
			errors.add("Last name cannot be empty!");
		else if(lastName.length() < 3 || lastName.length() > 10)
			errors.add("Last name must be between 3 and 10 characters inclusive!");
		
		if(null == city || city.trim().isEmpty())
			errors.add("City cannot be empty!");
		
		if(null == address || address.trim().isEmpty())
			errors.add("Address cannot be empty!");
		return errors;
	}
	
	private List<String> validateChangePassword(String currentPassword, String newPassword, String confirmPassword){
		List<String> errors = new ArrayList<>();
		if(null == currentPassword || currentPassword.trim().isEmpty())
			errors.add("Current password can not be empty!");
		if(null == newPassword || newPassword.trim().isEmpty())
			errors.add("New password can not be empty!");
		if(!newPassword.trim().equals(confirmPassword.trim()))
			errors.add("Confirmation password must equal new password!");
		return errors;
	}
}
