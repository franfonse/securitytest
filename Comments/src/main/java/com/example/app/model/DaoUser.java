package com.example.app.model;

import org.springframework.data.repository.CrudRepository;

public interface DaoUser extends CrudRepository<User, Long> {
	
	public User findByUsername(String username);

}