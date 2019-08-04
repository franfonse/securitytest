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
	

}