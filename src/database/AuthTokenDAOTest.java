package database;

import static org.junit.Assert.*;

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
	}
	
	@Test
	public void testSaveToken() {
		assertTrue(authTokenDriver.saveAuthToken(new AuthToken()));
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
