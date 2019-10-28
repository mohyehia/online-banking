package com.mohyehia.onlinebanking.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mohyehia.onlinebanking.entities.Role;
import com.mohyehia.onlinebanking.entities.User;
import com.mohyehia.onlinebanking.services.UserService;

@Component
public class FirstTimeInitializer implements CommandLineRunner{
	private static final Logger LOG = LoggerFactory.getLogger(FirstTimeInitializer.class);
	private final UserService userService;
	
	@Autowired
	public FirstTimeInitializer(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public void run(String... args) throws Exception {
		if(userService.findAll().isEmpty()) {
			LOG.info("No users found in database. Creating some users ....");
			User user = new User("admin", "admin", "test", "test", "admin@admin.com", "admin");
			Set<Role> roles = new HashSet<>(Arrays.asList(new Role(1L)));
			user.setRoles(roles);
			userService.save(user);
			LOG.info("New user with admin role created successfully!");
		}else
			LOG.info("Found some users in database. Skip creating users.");
	}

}
