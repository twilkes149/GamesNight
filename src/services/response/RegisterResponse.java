package services.response;

/**
 * A class that wraps up all of the response data for a register service
 * @author tucker
 *
 */
public class RegisterResponse extends Response {
	private String authToken;
	private String userName;
	private String userID;
	
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	

}
