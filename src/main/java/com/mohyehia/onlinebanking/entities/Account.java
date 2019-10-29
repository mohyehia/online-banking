package com.mohyehia.onlinebanking.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private String accountType;
	private BigDecimal balance;
	private String creditType;
	private Date created;
	
	public Account() {
		this.created = new Date();
	}
	
	public Account(Long userId, String accountType, BigDecimal balance, String creditType) {
		this();
		this.userId = userId;
		this.accountType = accountType;
		this.balance = balance;
		this.creditType = creditType;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getCreditType() {
		return creditType;
	}
	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", userId=" + userId + ", accountType=" + accountType + ", balance=" + balance
				+ ", creditType=" + creditType + ", created=" + created + "]";
	}
}
