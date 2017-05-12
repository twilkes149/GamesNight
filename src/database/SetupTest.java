package database;

import static org.junit.Assert.*;

import org.junit.Test;

public class SetupTest {

	@Test
	public void testInit() {
		assertTrue(Setup.init("test"));
	}
	
	@Test
	public void testClear() {
		assertTrue(Setup.clear());
	}

}
