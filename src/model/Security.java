package model;

import java.security.MessageDigest; //for SHA-256
import java.nio.charset.StandardCharsets; //for StandardCharsets

public class Security{
	
	public static String salt(String input){
		return input;
	}
	
	public static String hash(String input){
		
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
		
		String hashed = new String(hash);
		return hashed;
	}
	
}