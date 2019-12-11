package com.mohyehia.onlinebanking.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController extends AbstractErrorController {

	public CustomErrorController(ErrorAttributes errorAttributes) {
		super(errorAttributes);
	}
	
	@GetMapping("/error")
	public String getErrorPage(HttpServletRequest request) {
		HttpStatus status = getStatus(request);
		if(status.equals(HttpStatus.NOT_FOUND)) {
			return "errors/404";
		}
		return "errors/403";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
