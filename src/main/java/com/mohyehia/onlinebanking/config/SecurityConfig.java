package com.mohyehia.onlinebanking.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	private final String[] PUBLIC_ENDPOINTS = {
			"/auth/**"
		};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(PUBLIC_ENDPOINTS).permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().fullyAuthenticated()
			.and()
			.formLogin()
//			.loginPage("")
//			.loginProcessingUrl("")
//			.defaultSuccessUrl("")
//			.failureUrl("")
			.and()
			.logout()
//			.logoutUrl("/logout")
			.deleteCookies("JSESSIONID");
	}
}
