package model;

public class Email {
	private String email;
	
	public Email() {
		email = null;
	}
	
	public Email(String email) throws IllegalArgumentException {
		if (isValidEmail(email))
			this.email = email;
		else
			throw new IllegalArgumentException();
	}
	
	public String toString() {
		return email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) throws IllegalArgumentException {
		if (isValidEmail(email))
			this.email = email;
		else
			throw new IllegalArgumentException();
		
	}
	
	/**
	 * Checks if email is a valid formatted email
	 * @param email the email candidate
	 * @return true if email is a valid formatted email, false otherwise
	 */
	private boolean isValidEmail(String email) {
		if (email.matches("[\\w]*@[\\w]*\\.\\w[\\w]*"))
			return true;
		else
			return false;
	}

}
