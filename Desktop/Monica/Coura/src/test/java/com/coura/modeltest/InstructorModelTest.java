package com.coura.modeltest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.coura.model.Instructor;

public class InstructorModelTest {
	
	private Instructor instructor;
	
	@Before
	public void setUp() {
		instructor = new Instructor();
		instructor.setEmailId("gwilliam@uncc.edu");
		instructor.setFirstName("Goerge");
		instructor.setLastName("William");
		instructor.setId(101);
		instructor.setResearchInterest("Embedded Systems");
	}
	
	@Test
	public void getEmailIdTest() {
		assertEquals("getter Test", "gwilliam@uncc.edu", instructor.getEmailId());
	}
	
	@Test
	public void getFirstNameTest() {
		assertEquals("getter Test", "Goerge", instructor.getFirstName());
	}

	@Test
	public void getLastTest() {
		assertEquals("getter Test", "William", instructor.getLastName());
	}

	@Test
	public void getIdTest() {
		assertEquals("getter Test", 101, instructor.getId().intValue());
	}

	@Test
	public void getResearchAreaTest() {
		assertEquals("getter Test", "Embedded Systems", instructor.getResearchInterest());
	}


}
