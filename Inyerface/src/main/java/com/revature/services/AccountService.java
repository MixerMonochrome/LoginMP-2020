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
		// Possibly find better Hash Method?
		String hashed = ((Long)((long)u.getUsername().hashCode() * (long)u.getPassword().hashCode())).toString();
		System.out.println(hashed);
		if (nu != null && nu.getPassword().equals(hashed)) {
			return nu;
		} else {
			return null;
		}
	}

	public User persist(User u) {
		if (u.getUserId() == 0) {
			String hashed = ((Long)((long)u.getUsername().hashCode() * (long)u.getPassword().hashCode())).toString();
			u.setPassword(hashed);
		} else {
			User oldUser = udao.findById(u.getUserId()).get();
			//if Password is changed, straightforward hash
			if (!u.getPassword().equals(oldUser.getPassword())) {
				String hashed = ((Long)((long)u.getUsername().hashCode() * (long)u.getPassword().hashCode())).toString();
				u.setPassword(hashed);
			}
			//if Password is the same
			else {
				//if Username is changed, extract hash of old username & rehash
				if(!u.getUsername().equals(oldUser.getUsername())) {
					Long passHash = Long.parseLong(oldUser.getPassword())/oldUser.getUsername().hashCode();
					String hashed = ((Long)((long)u.getUsername().hashCode() * passHash)).toString();
					u.setPassword(hashed);
				}//else if username & password unchanged, ignore
				else {
				}
			}
		}
		return (udao.save(u));
	}

	public List<User> findAll() {
		return (udao.findAll());
	}

}
