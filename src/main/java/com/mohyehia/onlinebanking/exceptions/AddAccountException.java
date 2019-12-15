package com.mohyehia.onlinebanking.exceptions;

public class AddAccountException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public AddAccountException(String message) {
		super(message);
	}
}
