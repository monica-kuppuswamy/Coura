package com.coura.modeltest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.coura.model.InstructorRating;

public class InstructorRatingModelTest {

	private InstructorRating instructorRating;
	
	@Before
	public void setUp() throws Exception {
		instructorRating = new InstructorRating();
	}

	@Test
	public void getUserEmailIdTest() {
		instructorRating.setUserEmailId("user@uncc.edu");
		assertEquals("user@uncc.edu",instructorRating.getUserEmailId());
	}

	@Test
	public void setUserEmailIdTest() {
		instructorRating.setUserEmailId("user@uncc.edu");
		assertEquals("user@uncc.edu",instructorRating.getUserEmailId());
	}

	@Test
	public void getInstructorIdTest() {
		instructorRating.setInstructorId(2);;
		assertEquals(2,instructorRating.getInstructorId());
	}

	@Test
	public void setInstructorIdTest() {
		instructorRating.setInstructorId(2);;
		assertEquals(2,instructorRating.getInstructorId());
	}
	
	@Test
	public void getCourseIdTest() {
		instructorRating.setCourseId(1);
		assertEquals(1,instructorRating.getCourseId());
	}

	@Test
	public void setCourseIdTest() {
		instructorRating.setCourseId(1);
		assertEquals(1,instructorRating.getCourseId());
	}

	@Test
	public void getQualityOfTeachingRatingTest() {
		instructorRating.setQualityOfTeachingRating(3);
		assertEquals(3,instructorRating.getQualityOfTeachingRating().intValue());
	}

	@Test
	public void setQualityOfTeachingRatingTest() {
		instructorRating.setQualityOfTeachingRating(3);
		assertEquals(3,instructorRating.getQualityOfTeachingRating().intValue());
	}

	@Test
	public void getGradingStyleRatingTest() {
		instructorRating.setGradingStyleRating(4);
		assertEquals(4,instructorRating.getGradingStyleRating().intValue());
	}

	@Test
	public void setGradingStyleRatingTest() {
		instructorRating.setGradingStyleRating(4);
		assertEquals(4,instructorRating.getGradingStyleRating().intValue());
	}
	
	@Test
	public void getLeniencyRatingTest() {
		instructorRating.setGradingStyleRating(4);
		assertEquals(4,instructorRating.getGradingStyleRating().intValue());
	}

	@Test
	public void setLeniencyRatingTest() {
		instructorRating.setLeniencyRating(5);
		assertEquals(5,instructorRating.getLeniencyRating().intValue());
	}
}
