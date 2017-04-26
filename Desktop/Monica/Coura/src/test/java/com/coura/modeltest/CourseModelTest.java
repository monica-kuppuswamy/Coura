package com.coura.modeltest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.coura.model.Course;

public class CourseModelTest {
	
	private Course course;
	
	@Before
	public void setUp() {
		course = new Course(1, "ITCS6162", "Database Systems", "Instructor Approval", "Introduction TO DBMS");
	}
	
	@Test
	public void getCourseIdTest() {
		assertEquals("getter test", 1, course.getId().intValue());
	}
	
	@Test
	public void setCourseIdTest() {
		course.setId(101);
		assertEquals("setter test", 101, course.getId().intValue());
	}
	
	@Test
	public void getCourseNumberTest() {
		assertEquals("getter test", "ITCS6162", course.getCourseNumber());
	}
	
	@Test
	public void setCourseNumberTest() {
		course.setCourseNumber("ITCS5180");
		assertEquals("setter test", "ITCS5180", course.getCourseNumber());
	}
	
	@Test
	public void getCourseNameTest() {
		assertEquals("getter test", "Database Systems", course.getCourseName());
	}
	
	@Test
	public void setCourseNameTest() {
		course.setCourseName("Mobile App Developement");
		assertEquals("setter test", "Mobile App Developement", course.getCourseName());
	}
	
	@Test
	public void getPrerequisiteTest() {
		assertEquals("getter test", "Instructor Approval", course.getPrerequisite());
	}
	
	@Test
	public void setPrerequisiteTest() {
		course.setPrerequisite("CCI Graduate");
		assertEquals("setter test", "CCI Graduate", course.getPrerequisite());
	}
}
