package com.coura.modeltest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.coura.model.CourseRating;

public class CourseRatingModelTest {

	private CourseRating courseRating;
	
	@Before
	public void setUp() throws Exception {
		courseRating = new CourseRating();
	}

	@Test
	public void getUserEmailIdTest() {
		courseRating.setUserEmailId("user@uncc.edu");
		assertEquals("user@uncc.edu",courseRating.getUserEmailId());
	}

	@Test
	public void setUserEmailIdTest() {
		courseRating.setUserEmailId("user@uncc.edu");
		assertEquals("user@uncc.edu",courseRating.getUserEmailId());
	}

	@Test
	public void getCourseIdTest() {
		courseRating.setCourseId(1);
		assertEquals(1,courseRating.getCourseId());
	}

	@Test
	public void setCourseIdTest() {
		courseRating.setCourseId(1);
		assertEquals(1,courseRating.getCourseId());
	}

	@Test
	public void getDifficultyRatingTest() {
		courseRating.setDifficultyRating(2);
		assertEquals(2,courseRating.getDifficultyRating().intValue());
	}

	@Test
	public void setDifficultyRatingTest() {
		courseRating.setDifficultyRating(2);
		assertEquals(2,courseRating.getDifficultyRating().intValue());
	}

	@Test
	public void getUsefulnessRatingTest() {
		courseRating.setUsefulnessRating(4);
		assertEquals(4,courseRating.getUsefulnessRating().intValue());
	}

	@Test
	public void setUsefulnessRatingTest() {
		courseRating.setUsefulnessRating(4);
		assertEquals(4,courseRating.getUsefulnessRating().intValue());
	}

}
