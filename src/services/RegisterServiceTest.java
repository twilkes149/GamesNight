package services;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import database.Setup;
import services.input.RegisterInput;
import services.response.RegisterResponse;

public class RegisterServiceTest {
	private static RegisterService service;
	
	@BeforeClass
	public static void setup() {
		Setup.init("test");
		service = new RegisterService();
	}
	
	@Before
	public void clear() {
		Setup.clear();
	}
	
	@Test
	public void testGoodInput() {
		RegisterInput input = new RegisterInput("tucker", "wilkes", "twilkes", "password", "email@email.com");
		RegisterService service = new RegisterService();
		
		RegisterResponse response = (RegisterResponse) service.fillService(input);
		
		assertTrue(response.getAuthToken() != null);
		assertTrue(response.getUserName() != null);
		assertTrue(response.getUserID() != null);
		assertFalse(response.getError());
	}

}
