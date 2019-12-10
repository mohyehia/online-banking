package com.mohyehia.onlinebanking.services.framework;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mohyehia.onlinebanking.entities.User;

public interface UserService extends UserDetailsService{
	UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
	User save(User user);
	User update(User user, Long userId, String email);
	boolean exists(String email);
	List<User> findAll();
}
