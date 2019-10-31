package com.mohyehia.onlinebanking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.mohyehia.onlinebanking.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final String[] PUBLIC_ENDPOINTS = {"/auth/**"};
	private final String[] USER_ENDPOINTS = {"/accounts/**", "/transactions/**", "/me"};
	private final String[] ADMIN_ENDPOINTS = {"/admin/**"};
	
	private final UserService userService;
	private final LoginSuccessHandler successHandler;
	private final BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public SecurityConfig(UserService userService, LoginSuccessHandler successHandler, BCryptPasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.successHandler = successHandler;
		this.passwordEncoder = passwordEncoder;
	}
		
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(PUBLIC_ENDPOINTS).anonymous()
			.antMatchers(USER_ENDPOINTS).hasRole("USER")
			.antMatchers(ADMIN_ENDPOINTS).hasRole("ADMIN")
			.and()
			.formLogin()
            .loginPage("/auth/login")
            .successHandler(successHandler)
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/auth/login")
			.deleteCookies("JSESSIONID")
			.invalidateHttpSession(true)
			// add exception handling, access denied page & error page
			.and()
			.exceptionHandling().accessDeniedPage("/403")
			.and()
			.cors().disable();
	}
	
}
