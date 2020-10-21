package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.User;
import com.revature.services.AccountService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/user")
public class Login {
	AccountService acs;
	
	@Autowired
	public Login(AccountService acs) {
		this.acs = acs;
	}
	
	//Return all Users
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(acs.findAll());
	}
	
	//Login Method (Verification)
	@PostMapping(value = "/login")
	public ResponseEntity<User> login(@RequestBody User u){
		User nu = acs.validLogin(u);
		if(nu == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nu);
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(nu);
		}
	}
	
	//New User Method
	@PostMapping(value = "/new")
	public ResponseEntity<User> register(@RequestBody User u){
		User nu = acs.register(u);
		if(nu == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nu);
		}else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(nu);
		}
	}
	
	//Update User Method
//	@PutMapping(value = "/update")
//	public ResponseEntity<User> update(@RequestBody User u){
//		User nu = acs.update(u);
//		if(nu == null) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nu);
//		}else {
//			return ResponseEntity.status(HttpStatus.ACCEPTED).body(nu);
//		}
//	}
	
}
