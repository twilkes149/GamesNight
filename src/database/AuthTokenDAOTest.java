package database;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import model.AuthToken;

public class AuthTokenDAOTest {
	private static AuthTokenDAO authTokenDriver;
	private static AuthToken token;
	
	@BeforeClass
	public static void init() {
		authTokenDriver = new AuthTokenDAO("test");
		token = new AuthToken();
		
		token.setValue(UUID.randomUUID());
		token.setUserLink(UUID.randomUUID());
		token.setExDate(LocalDate.now());
		token.setExTime(LocalTime.now().plusHours(1));
		
		authTokenDriver.saveAuthToken(token);
		
	}
	
	@Test
	public void testSaveToken() {
		assertTrue(authTokenDriver.saveAuthToken(token));
	}
	
	@Test
	public void testNullSaveToken() {
		assertFalse(authTokenDriver.saveAuthToken(null));
	}
	
	@Test
	public void testGetAuthToken() {
		assertTrue(authTokenDriver.getAuthToken(token.getValue().toString()) != null);
	}
	
	@Test
	public void testGettingBlankToken() {
		assertEquals(null, authTokenDriver.getAuthToken(""));
		assertEquals(null, authTokenDriver.getAuthToken(null));
		assertEquals(null, authTokenDriver.getAuthToken("token not here"));
	}

}
