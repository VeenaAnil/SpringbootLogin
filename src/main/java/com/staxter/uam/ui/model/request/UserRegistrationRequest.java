package com.staxter.uam.ui.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegistrationRequest {

	
	private String id;
	@NotNull(message="First name cannot be null")
	@Size(min=3, message= "First name must not be leass than 3 characters")
	private String firstName;
	
	@NotNull(message="Last name cannot be null")
	@Size(min=3, message= "Last name must not be leass than 3 characters")
	private String lastName;
	
	@NotNull(message="Username cannot be null")
	@Size(min=3, message= "Username must not be leass than 3 characters")
	private String userName;
	
	@NotNull(message="Password cannot be null")
	@Size(min=8, max=15,  message= "Password must be equal to or greater than 8 and less than 16")
	private String password;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}