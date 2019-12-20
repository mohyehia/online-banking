package com.mohyehia.onlinebanking.services.implementation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mohyehia.onlinebanking.dao.UserDAO;
import com.mohyehia.onlinebanking.entities.Role;
import com.mohyehia.onlinebanking.entities.User;
import com.mohyehia.onlinebanking.services.framework.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	private UserDAO userDAO;
	
	@Autowired
	public UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDAO.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
		new AccountStatusUserDetailsChecker().check(user);
		return user;
	}
	
	@Override
	public User save(User user) {
		if(exists(user.getEmail())) return null;
		user.setPassword(passwordEncoder().encode(user.getPassword()));
		if(null == user.getRoles() || user.getRoles().isEmpty()) {
			Set<Role> roles = new HashSet<>(Arrays.asList(new Role(2)));
			user.setRoles(roles);
		}
		return userDAO.save(user);
	}
	
	@Override
	public User update(User user, Long userId, String email) {
		if(userDAO.findByIdAndEmail(userId, email).isPresent()) {
			return userDAO.save(user);
		}else
			return null;
	}
	
	@Override
	public boolean exists(String email) {
		return userDAO.findByEmail(email).isPresent();
	}
	
	@Override
	public List<User> findAll(){
		return userDAO.findAll();
	}

}
