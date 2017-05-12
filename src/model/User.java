package model;

import java.util.UUID;
import java.time.LocalDate;

public class User {
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String email;
	private UUID ID;
	private AuthToken token;
	private LocalDate startDate;
	
	public User() {
		this.firstName = null;
		this.lastName = null;
		this.userName = null;
		this.password = null;
		this.email = null;
		this.ID = null;
		this.token = null;
		this.startDate = null;
	}
	
	public User(String userName) {
		this.userName = userName;
	}
	
	public User(String firstName, String lastName, String userName, String password, String email, LocalDate startDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.startDate = startDate;
	}
	
	public String combineUserInfo(){
		return firstName+lastName+userName+password+email+startDate;
	}
	
	public UUID getID() {
		return ID;
	}
	public void setID(UUID iD) {
		ID = iD;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public AuthToken getToken() {
		return token;
	}
	public void setToken(AuthToken token) {
		this.token = token;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}	
	public LocalDate getStartDate(){
		return startDate;
	}

}
