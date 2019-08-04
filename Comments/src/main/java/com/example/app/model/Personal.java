package com.example.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "personal")
public class Personal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idPersonal;
	private String username;
	private String password;
	
	
	public Personal() {
	}

	public Personal(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public long getIdPersonal() {
		return idPersonal;
	}

	public void setIdPersonal(long idPersonal) {
		this.idPersonal = idPersonal;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}