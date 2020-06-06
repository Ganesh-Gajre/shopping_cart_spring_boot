package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Comparable<User> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false)
	private int userId;

	@Column(name = "user_name", nullable = false)
	private String userName;

	@Column(name = "age", nullable = false)
	private int age;

	@OneToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int compareTo(User user) {
		if (age == user.age)
			return 0;
		else if (age > user.age)
			return 1;
		else
			return -1;
	}

	@Override
	public int hashCode() {
		return this.userId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) 
            return true; 
		return false;  
	}
}
