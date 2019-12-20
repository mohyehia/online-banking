package com.mohyehia.onlinebanking.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	private Date created;
	
	public Account() {
		this.created = new Date();
	}
	
	public Account(long userId, String accountType, BigDecimal balance, String creditType) {
		this();
		this.userId = userId;
		this.accountType = accountType;
		this.balance = balance;
		this.creditType = creditType;
	}

}
