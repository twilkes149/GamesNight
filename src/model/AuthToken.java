package model;

import java.util.UUID;
import java.time.LocalDate;
import java.time.LocalTime;

public class AuthToken {
	private UUID value;//the value of this authToken
	private UUID userLink;//id of a user
	//the time and date the token expires
	private LocalTime exTime;
	private LocalDate exDate;
	
	public AuthToken() {
		exTime = LocalTime.now().plusHours(1);
		exDate = LocalDate.now();
		if (exTime.getHour() == 0)
			exDate.plusDays(1);
		value = UUID.randomUUID();
	}
	
	public AuthToken(UUID id, LocalTime exTime, LocalDate exDate, UUID userLink) {
		value = id;
		this.exTime = exTime;
		this.exDate = date;
		this.userLink = userLink;
	}
	
	public UUID getUserLink() {
		return userLink;
	}
	public void setUserLink(UUID userLink) {
		this.userLink = userLink;
	}
	public UUID getValue() {
		return value;
	}
	public LocalTime getExTime() {
		return exTime;
	}
	public LocalDate getExDate() {
		return exDate;
	}
	
	public void setValue(UUID value) {
		this.value = value;
	}

	public void setExTime(LocalTime exTime) {
		this.exTime = exTime;
	}

	public void setExDate(LocalDate exDate) {
		this.exDate = exDate;
	}

	/**
	 * Returns if this is a valid token
	 * @return true if this token is still valid, otherwise false
	 */
	public boolean isValid() {
		System.out.println(LocalDate.now().isBefore(exDate));
		if (LocalDate.now().isBefore(exDate) || LocalTime.now().isBefore(exTime))
			return true;
		else
			return false;
	}
	
	public static void main(String[] args) {
		AuthToken token = new AuthToken();
		System.out.println(token.isValid());
	}
	
	
}
