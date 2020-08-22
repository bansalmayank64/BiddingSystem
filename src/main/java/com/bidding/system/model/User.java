package com.bidding.system.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = -7701472919551240347L;

	@Id
	@Column(length = 15)
	private String userId;

	@Column(length = 50)
	private String email;
	@Column(length = 50)
	private String firstName;
	@Column(length = 50)
	private String lastName;

	public User() {
		super();
	}

	public User(String userId, String email, String firstName, String lastName) {
		super();
		this.userId = userId;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [userId=").append(userId).append(", email=").append(email).append(", firstName=")
				.append(firstName).append(", lastName=").append(lastName).append("]");
		return builder.toString();
	}
}
