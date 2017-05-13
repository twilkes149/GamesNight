package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class EmailTest {

	@Test
	public void testValid() {
		try {
			Email e = new Email("twilkes@gmail.com");
		}
		catch (Exception e) {
			fail("passed in a valid email");
		}
		assertTrue(true);
	}
	
	@Test
	public void testInvalid() {
		try {
			Email e = new Email("email");
			fail("invalid email");
		}
		catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testPartial() {
		try {
			Email e = new Email("twilkes@gmail");
			fail("invalid email");
		}
		catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testPartial1() {
		try {
			Email e = new Email("twilkes@gmail.");
			fail("invalid email");
		}
		catch (Exception e) {
			assertTrue(true);
		}
	}

}
