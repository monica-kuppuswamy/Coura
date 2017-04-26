package com.coura.modeltest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.coura.model.StudentCourse;

public class StudentCourseModelTest {

	private StudentCourse studentCourse;
	
	@Before
	public void setUp() throws Exception {
		studentCourse = new StudentCourse();
	}

	@Test
	public void getUserEmailIdTest() {
		studentCourse.setUserEmailId("user@uncc.edu");
		assertEquals("user@uncc.edu",studentCourse.getUserEmailId());
	}

	public void setUserEmailIdTest(String userEmailId) {
		studentCourse.setUserEmailId("user@uncc.edu");
		assertEquals("user@uncc.edu",studentCourse.getUserEmailId());
	}

	public void getCourseIdTest() {
		studentCourse.setCourseId(1);
		assertEquals(1,studentCourse.getCourseId());
	}

	public void setCourseIdTest(int courseId) {
		studentCourse.setCourseId(1);
		assertEquals(1,studentCourse.getCourseId());
	}

	public void getInstructorIdTest() {
		studentCourse.setInstructorId(2);;
		assertEquals(2,studentCourse.getInstructorId());
	}

	public void setInstructorIdTest(int instructorId) {
		studentCourse.setInstructorId(2);;
		assertEquals(2,studentCourse.getInstructorId());
	}

}
