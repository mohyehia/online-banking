package com.mohyehia.onlinebanking.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	
	public Role() {}
	
	public Role(long id) {
		this.id = id;
	}
	
	public Role(String name) {
		this.name = name;
	}
	
}
