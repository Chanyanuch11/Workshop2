package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.User;
import com.example.demo.model.dto.UpdateEmailRequest;
import com.example.demo.repo.UserRepository;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@PostMapping
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}

	@PostMapping("/update-email")
	public String updateEmail(@RequestBody UpdateEmailRequest request) {
	    User user = userRepository.findById(request.getUserId())
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    user.setEmail(request.getNewEmail());
	    userRepository.save(user);

	    return "Email updated successfully!";
	}

}
