package com.example.demo.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;


@EqualsAndHashCode

public class UserDetailsImpl implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long userId;
	
	private String firstName;
	private String lastName;
	private String nameToBeAppeared;
	
	private String email;
	
	@JsonIgnore
	private String password;
	
	private String contactNo;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
	
	
	
	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());

		return new UserDetailsImpl(user.getUserId(),user.getEmail(), user.getPassword(), authorities,user.getNameToBeAppeared());
	}
	
	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}



	public UserDetailsImpl(Long userId, String email, String password, Collection<? extends GrantedAuthority> authorities, String nameToBeAppeared) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.nameToBeAppeared = nameToBeAppeared;
	}



	public Long getUserId() {
		return userId;
	}



	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getNameToBeAppeared() {
		return nameToBeAppeared;
	}



	public void setNameToBeAppeared(String nameToBeAppeared) {
		this.nameToBeAppeared = nameToBeAppeared;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getContactNo() {
		return contactNo;
	}



	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	

}