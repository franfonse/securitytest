package com.example.app.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import com.example.app.model.DaoPost;
import com.example.app.model.DaoUser;
import com.example.app.model.Post;
import com.example.app.model.User;

@org.springframework.stereotype.Controller
@SessionAttributes("idUser")
public class Controller {
	
	private DaoUser daoUser;
	private DaoPost daoPost;
	
	@GetMapping("/")
	public String home() {
		
		return "homepage";
	}
	
	@GetMapping("/login")
	public String login(ModelMap model, @RequestParam String username, @RequestParam String password) {
		
		User user = daoUser.findByUsername(username);
		
		if (user == null || !password.equals(user.getPassword())) {
			
			model.addAttribute("failLogin", "Invalid username or password. Please, try again.");
			
			return "homepage";
			
		} else {
			
			model.put("idUser", user.getIdUser());
			
			return "posts";
		}
		
	}
	
	@GetMapping("/goRegister")
	public String goRegister() {
		
		return "register";
	}
	
	@PostMapping("/register")
	public String register(ModelMap model, @RequestParam String username, @RequestParam String password) {
		
		User user = new User(username, password);
		
		if (username.equalsIgnoreCase(daoUser.findByUsername(username).getUsername())) {
			
			model.addAttribute("takenUsername", "Username already taken. Please use another one.");
			
			return "register";
			
		} else if (username == null || password == null || username == "" || password == "" || username.length() < 5 || password.length() < 7) {
			
			model.addAttribute("noNull", "Email and password must satisfy the site's requirement for registering.");
			
			return "register";
			
		} else {
			
			daoUser.save(user);
			
			return "homepage";
			
		}
		
	}
	
	@GetMapping("/goPost")
	public String goPost() {
		
		return "comment";
	}
	
	@PostMapping("/post")
	public String post(@SessionAttribute("idUser") long idUser, ModelMap model, @RequestParam String comment) {
		
		Post post = new Post();
		User user = daoUser.findById(idUser).get();
		
		if (comment == null || comment == "") {
			
			model.addAttribute("noNull", "The post's comment must not be empty.");
			
			return "comment";
			
		} else {
			
			if (user.getListPosts() == null) {
				
				List<Post> listPosts = new ArrayList<Post>();
				
				user.setListPosts(listPosts);
				user.getListPosts().add(post);
				
				model.addAttribute("posts", daoPost.findAll());
				
				return "posts";
				
			} else {
				
				user.getListPosts().add(post);
				
				model.addAttribute("posts", daoPost.findAll());
				
				return "posts";
			}
			
		}
		
	}
	
	
	@GetMapping("/logoff")
	public String logoff(SessionStatus status) {
		
		status.setComplete();
		
		return "homepage";
	}
	
}