package com.coura.modeltest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.coura.model.Users;

public class UserModelTest {
	
	private Users user;
	
	@Before
	public void setUp() {
		user = new Users("mpadmana@uncc.edu", "Monica", "Kuppuswamy", "1234567");
	}

	@Test
	public void getFirstNameTest() {
		assertEquals("getter test for first name", "Monica", user.getFirstName());
	}
	
	@Test
	public void setFirstNameTest() {
		user.setFirstName("Goerge");
		assertEquals("setter test for first name", "Goerge", user.getFirstName());
	}
	
	@Test
	public void getLastNameTest() {
		assertEquals("getter test for last name", "Kuppuswamy", user.getLastName());
	}
	
	@Test
	public void setLastNameTest() {
		user.setLastName("Williams");
		assertEquals("setter test for last name", "Williams", user.getLastName());
	}
	
	@Test
	public void getEmailIdTest() {
		assertEquals("getter test for email id", "mpadmana@uncc.edu", user.getEmailId());
	}
	
	@Test
	public void setEmailIdTest() {
		user.setEmailId("gwilliams@uncc.edu");
		assertEquals("setter test for email id", "gwilliams@uncc.edu", user.getEmailId());
	}
	
	@Test
	public void getPasswordTest() {
		assertEquals("getter test for password", "1234567", user.getPassword());
	}
	
	@Test
	public void setPasswordTest() {
		user.setPassword("zyxvu");
		assertEquals("setter test for password", "zyxvu", user.getPassword());
	}
}
