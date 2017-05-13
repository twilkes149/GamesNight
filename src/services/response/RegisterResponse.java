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
	private boolean error;
	private String errorMessage;
	
	public RegisterResponse(String message) {
		error = true;
		errorMessage = message;
	}
	
	public RegisterResponse(String userName, String authToken, String userID) {
		this.userName = userName;
		this.authToken = authToken;
		this.userID = userID;
	}
	
	public boolean getError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

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
	
	public String toString() {
		return userName + ": " + userID + ", " + authToken;
	}

}
