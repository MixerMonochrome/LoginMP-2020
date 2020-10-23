package com.revature.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.User;
import com.revature.repositories.UserIDAO;

@Service
@Transactional
public class AccountService {
	
	UserIDAO udao;
	
	@Autowired
	public AccountService(UserIDAO udao) {
		this.udao = udao;
	}
	
	public User validLogin(User u) {
		User nu = udao.findByUsername(u.getUsername());
		//Possibly find better Hash Method?
		String hashed = ((Integer)(u.getUsername().hashCode() * u.getPassword().hashCode())).toString();
		if (nu != null && nu.getPassword() == hashed) {
			return nu;
		}
		else {
			return null;
		}
	}
	
	public User persist(User u) {
		return(udao.save(u));	
	}
	
	public List<User> findAll(){
		return(udao.findAll());
	}

}
