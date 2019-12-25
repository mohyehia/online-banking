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
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long userId;
	private long fromAccountId;
	private long toAccountId;
	private BigDecimal amount;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime created;
	
	public Transaction() {
		this.created = LocalDateTime.now();
	}

	public Transaction(long userId, long fromAccountId, long toAccountId, BigDecimal amount) {
		this();
		this.userId = userId;
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
		this.amount = amount;
	}
	
}
