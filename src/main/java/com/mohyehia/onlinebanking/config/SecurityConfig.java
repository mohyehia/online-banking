package com.mohyehia.onlinebanking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mohyehia.onlinebanking.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final String[] PUBLIC_ENDPOINTS = {
			"/auth/**"
		};
	
	@Autowired
	private UserService userService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(PUBLIC_ENDPOINTS).permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/**").hasRole("USER")
			.anyRequest().fullyAuthenticated()
			.and()
			.formLogin()
			.loginPage("/auth/login")
//			.failureUrl("/auth/login?error")
//			.loginProcessingUrl("/authentication")
			.defaultSuccessUrl("/accounts", true)
			.and()
			.logout()
			.logoutUrl("/logout")
			.invalidateHttpSession(true)
//			.deleteCookies("JSESSIONID")
			.and()
			.cors().disable()
			.csrf().disable();
	}
	
}
