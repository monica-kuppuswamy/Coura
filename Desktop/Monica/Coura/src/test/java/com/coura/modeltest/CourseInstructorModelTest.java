package com.coura.modeltest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.coura.model.CourseInstructor;

public class CourseInstructorModelTest {
	
	private CourseInstructor ci;
	
	@Before
	public void setup() {
		ci = new CourseInstructor();
		ci.setCourseId(1);
		ci.setInstructorId(2);
	}
	
	@Test
	public void getCourseIdTest() {
		assertEquals("Course Id", 1, ci.getCourseId());
	}
	
	@Test
	public void getInstructorIdTest() {
		assertEquals("Instructor Id", 2, ci.getInstructorId());
	}

}
