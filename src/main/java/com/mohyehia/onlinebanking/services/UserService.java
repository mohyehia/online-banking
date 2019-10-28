package com.mohyehia.onlinebanking.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mohyehia.onlinebanking.entities.Role;
import com.mohyehia.onlinebanking.entities.User;
import com.mohyehia.onlinebanking.repositories.UserRepository;

@Service
@Transactional
public class UserService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
		new AccountStatusUserDetailsChecker().check(user);
		return user;
	}
	
	public User save(User user) {
		if(exists(user.getEmail())) return null;
		user.setPassword(passwordEncoder().encode(user.getPassword()));
		if(null == user.getRoles() || user.getRoles().isEmpty()) {
			Set<Role> roles = new HashSet<>(Arrays.asList(new Role(2L)));
			user.setRoles(roles);
		}
		return userRepository.save(user);
	}
	
	public User update(User user, Long userId, String email) {
		if(userRepository.findByIdAndEmail(userId, email).isPresent()) {
			return userRepository.save(user);
		}else
			return null;
	}
	
	public boolean exists(String email) {
		return userRepository.findByEmail(email).isPresent();
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
}
