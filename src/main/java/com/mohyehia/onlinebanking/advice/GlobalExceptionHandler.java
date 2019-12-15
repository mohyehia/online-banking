package com.mohyehia.onlinebanking.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mohyehia.onlinebanking.exceptions.AddAccountException;
import com.mohyehia.onlinebanking.exceptions.NotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle(NotFoundException ex) {
		System.out.println(ex.getMessage());
		return "errors/404";
	}
	
	@ExceptionHandler(AddAccountException.class)
	public String handle(AddAccountException ex) {
		System.out.println(ex.getMessage());
		return "errors/addAccountError";
	}
}
