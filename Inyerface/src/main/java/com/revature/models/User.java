package com.revature.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="users")
@Component
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode @ToString
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userId")
	private int userId;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phoneNum")
	private String phoneNum;
	
	@Column(name="secQuest")
	private int secQuest;
	
	@Column(name="secAnsw")
	private String secAnsw;
	
	@Column(name="favColor")
	private String favColor;
	
	@Autowired
	public User(String username, String password, String email, String phoneNum, 
			int secQuest, String secAnsw, String favColor) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNum = phoneNum;
		this.secQuest = secQuest;
		this.secAnsw = secAnsw;
		this.favColor = favColor;
	}
}
