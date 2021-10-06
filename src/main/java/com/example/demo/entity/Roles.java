package com.example.demo.entity;

import javax.persistence.*;


@Entity
public class Roles {
	

	public Roles() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private MRoles name;

	public Roles(MRoles name) {
		this.name = name;
	}

	public MRoles getName() {
		return name;
	}

	public void setName(MRoles name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

}
