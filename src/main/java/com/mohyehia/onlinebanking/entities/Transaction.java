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
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long userId;
	private long fromAccountId;
	private long toAccountId;
	private BigDecimal amount;
	private Date created;
	
	public Transaction() {
		this.created = new Date();
	}

	public Transaction(long userId, long fromAccountId, long toAccountId, BigDecimal amount) {
		this();
		this.userId = userId;
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
		this.amount = amount;
	}
	
}
