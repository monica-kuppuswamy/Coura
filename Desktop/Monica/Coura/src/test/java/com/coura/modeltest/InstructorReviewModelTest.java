package com.coura.modeltest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.coura.model.InstructorReview;

public class InstructorReviewModelTest {

	private InstructorReview instructorReview;
	
	@Before
	public void setUp() throws Exception {
		instructorReview = new InstructorReview();
	}

	@Test
	public void getUserEmailIdTest() {
		instructorReview.setUserEmailId("user@uncc.edu");
		assertEquals("user@uncc.edu",instructorReview.getUserEmailId());
	}

	@Test
	public void setUserEmailIdTest() {
		instructorReview.setUserEmailId("user@uncc.edu");
		assertEquals("user@uncc.edu",instructorReview.getUserEmailId());
	}

	@Test
	public void getCourseIdTest() {
		instructorReview.setInstructorId(1);
		assertEquals(1,instructorReview.getInstructorId());
	}

	@Test
	public void setCourseIdTest() {
		instructorReview.setInstructorId(1);
		assertEquals(1,instructorReview.getInstructorId());
	}

	@Test
	public void getReviewTest() {
		instructorReview.setReview("Good course");
		assertEquals("Good course",instructorReview.getReview());
	}

	@Test
	public void setReviewTest() {
		instructorReview.setReview("Good course");
		assertEquals("Good course",instructorReview.getReview());
	}

}
