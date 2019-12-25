package com.mohyehia.onlinebanking.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long userId;
	private String accountType;
	private BigDecimal balance;
	private String creditType;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime created;
	private boolean active;
	
	public Account() {
		this.created = LocalDateTime.now();
		this.active = true;
	}
	
	public Account(long userId, String accountType, BigDecimal balance, String creditType) {
		this();
		this.userId = userId;
		this.accountType = accountType;
		this.balance = balance;
		this.creditType = creditType;
	}

}
