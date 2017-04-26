package com.coura.modeltest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.coura.model.CourseReview;

public class CourseReviewModelTest {

	private CourseReview courseReview;
	
	@Before
	public void setUp() throws Exception {
		courseReview = new CourseReview();
	}

	@Test
	public void getUserEmailIdTest() {
		courseReview.setUserEmailId("user@uncc.edu");
		assertEquals("user@uncc.edu",courseReview.getUserEmailId());
	}

	@Test
	public void setUserEmailIdTest() {
		courseReview.setUserEmailId("user@uncc.edu");
		assertEquals("user@uncc.edu",courseReview.getUserEmailId());
	}

	@Test
	public void getCourseIdTest() {
		courseReview.setCourseId(1);
		assertEquals(1,courseReview.getCourseId());
	}

	@Test
	public void setCourseIdTest() {
		courseReview.setCourseId(1);
		assertEquals(1,courseReview.getCourseId());
	}

	@Test
	public void getReviewTest() {
		courseReview.setReview("Good course");
		assertEquals("Good course",courseReview.getReview());
	}

	@Test
	public void setReviewTest() {
		courseReview.setReview("Good course");
		assertEquals("Good course",courseReview.getReview());
	}

}
