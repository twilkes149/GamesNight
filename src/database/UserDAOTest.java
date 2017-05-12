package database;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import model.AuthToken;
import model.User;

public class UserDAOTest{
	private static UserDAO userDriver;
	private static UUID id;
	
	@BeforeClass
	public static void setup() {
		userDriver = new UserDAO("test");
		id = UUID.randomUUID();
	}

	@Test
	public void testSaveNullUser() {
		assertFalse(userDriver.saveUser(null));
	}
	
	@Test
	public void testSaveUser() {
		User user = new User("tucker", "wilkes", "twilkes", "password", "email");
		user.setID(id);
		assertTrue(userDriver.saveUser(user));
	}
	
	@Test
	public void testGetNullUser() {
		assertEquals(null, userDriver.getUser(""));
	}
	
	@Test
	public void testAuthToken() {
		AuthToken token = new AuthToken();
		token.setUserLink(id);
		assertTrue(userDriver.getUser(token) != null);
	}
	
	@Test
	public void testUserName() {
		User user = new User("tucker", "wilkes", "twilkes", "password", "email");
		user.setID(id);
		assertTrue(userDriver.getUser(user.getUserName()) != null);
	}

}
