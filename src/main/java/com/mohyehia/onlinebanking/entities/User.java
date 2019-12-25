package com.mohyehia.onlinebanking.entities;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Entity
@Data
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message = "First name can't be empty.")
	@Size(min = 3, max = 10, message = "First name must be between 3 and 10 characters inclusive.")
	private String firstName;
	
	@NotNull(message = "Last name can't be empty.")
	@Size(min = 3, max = 10, message = "Last name must be between 3 and 10 characters inclusive.")
	private String lastName;
	
	@NotBlank(message = "You must specify your city.")
	private String city;
	
	@NotBlank(message = "Please provide your current address.")
	private String address;
	
	@NotBlank(message = "Email address can not be empty.")
	@Email(message = "Please provide a valid email address.")
	private String email;
	
	@Pattern(regexp = "^.*(?=.{6,15})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "Password must be between 6 and 15 characters long and contain at least one upper case letter, one lower case letter, and one digit.")
	private String password;
	
	@Transient
	private String confirmPassword;
	private boolean enabled;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime created;
	private boolean accountExpired;

	private boolean credentialsExpired;

	private boolean accountLocked;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private Set<Role> roles;

	public User() {
		this.created = LocalDateTime.now();
		this.enabled = false;
		this.accountExpired = false;
		this.accountLocked = false;
		this.credentialsExpired = false;
	}

	public User(String firstName, String lastName, String city, String address, String email, String password) {
		this();
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.address = address;
		this.email = email;
		this.password = password;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !accountExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !accountLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}

	@Override
	public String getUsername() {
		return email;
	}
}
