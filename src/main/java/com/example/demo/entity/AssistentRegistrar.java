package com.example.demo.entity;

import javax.persistence.Entity;



@Entity
public class AssistentRegistrar extends User {
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
