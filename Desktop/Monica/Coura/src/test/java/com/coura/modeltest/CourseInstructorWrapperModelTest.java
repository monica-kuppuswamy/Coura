package com.coura.modeltest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.coura.model.CourseInstructor;
import com.coura.model.CourseInstructorWrapper;
import com.coura.model.Instructor;

public class CourseInstructorWrapperModelTest {

	private CourseInstructorWrapper wrapper;
	private Instructor instructor;
	private CourseInstructor courseInstructor;
	
	@Before
	public void setUp() throws Exception {
		wrapper = new CourseInstructorWrapper();
		instructor = mock(Instructor.class);
		courseInstructor = mock(CourseInstructor.class);
	}

	@Test
	public void getInstructorTest() {
		wrapper.setInstructor(instructor);
		assertEquals(instructor,wrapper.getInstructor());
	}
	
	@Test
	public void setInstructorTest() {
		wrapper.setInstructor(instructor);
		assertEquals(instructor,wrapper.getInstructor());
	}
	
	@Test
	public void getCourseInstructorTest() {
		wrapper.setCourseInstructor(courseInstructor);
		assertEquals(courseInstructor,wrapper.getCourseInstructor());
	}
	
	@Test
	public void setCourseInstructorTest() {
		wrapper.setCourseInstructor(courseInstructor);
		assertEquals(courseInstructor,wrapper.getCourseInstructor());
	}
}
