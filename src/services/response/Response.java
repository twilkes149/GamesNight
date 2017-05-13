package services.response;

import model.AuthToken;

public class Response {
	private AuthToken token;
	private String userName;
	private boolean error;
	private String message;
	
	
	public AuthToken getToken() {
		return token;
	}
	public void setToken(AuthToken token) {
		this.token = token;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
