package com.example.app.model;

import org.springframework.data.repository.CrudRepository;

public interface DaoPersonal extends CrudRepository<Personal, Long> {
	
	public Personal findByUsername(String username);

}