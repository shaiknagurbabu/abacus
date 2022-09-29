package com.abacus.portel.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abacus.portel.exception.UserNotFoundException;
import com.abacus.portel.model.User;
import com.abacus.portel.repository.UserRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
	 @Autowired
	    private UserRepository userRepository;

	    @PostMapping("/user")
	    User newUser(@RequestBody User newUser) {
	        return userRepository.save(newUser);
	    }

	    @GetMapping("/users")
	    List<User> getAllUsers() {
	        return userRepository.findAll();
	    }

	    @GetMapping("/user/{id}")
	    Optional<User> getUserById(@PathVariable Long id) {
	        return userRepository.findById(id);
//	                .orElseThrow(() -> new UserNotFoundException(id));
	    }

	    @PutMapping("/user/{id}")
	    User updateUser(@RequestBody User newUser, @PathVariable Long id) {	        return userRepository.findById(id)
	                .map(user -> {
	                    user.setFirstname(newUser.getFirstname());
	                    user.setLastname(newUser.getLastname());
	                    user.setEmail(newUser.getEmail());
	                    user.setCity(newUser.getCity());
	                    return userRepository.save(user);
	                }).orElseThrow(() -> new UserNotFoundException(id));
	    }

	    @DeleteMapping("/user/{id}")
	    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
	            throw new UserNotFoundException(id);
	        }
        userRepository.deleteById(id);
	        return  "User with id "+id+" has been deleted success.";
	    }



}