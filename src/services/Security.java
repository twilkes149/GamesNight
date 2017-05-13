package services;

import java.security.MessageDigest; //for SHA-256
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets; //for StandardCharsets

public class Security{
	
	public static String salt(String input){
		return input;
	}
	
	public static String hash(String input){
		
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
		
		String hashed = new String(hash);
		return hashed;
	}
	
	public static void main(String[] args) {
		System.out.println("string: " + Security.hash("testing"));
	}
	
}