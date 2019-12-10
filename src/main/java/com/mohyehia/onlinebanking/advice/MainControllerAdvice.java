package com.mohyehia.onlinebanking.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mohyehia.onlinebanking.exceptions.NotFoundException;

@ControllerAdvice
public class MainControllerAdvice {
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleNotFoundException(NotFoundException ex) {
		ex.printStackTrace();
		return "errors/403";
	}
}
