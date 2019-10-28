package com.mohyehia.onlinebanking.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String roles = authentication.getAuthorities().toString();
		
		if(roles.contains("ROLE_USER"))
			response.sendRedirect(request.getContextPath() + "/accounts");
		else if(roles.contains("ROLE_ADMIN"))
			response.sendRedirect(request.getContextPath() + "/admin");
		else response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/auth/login"));
	}

}
