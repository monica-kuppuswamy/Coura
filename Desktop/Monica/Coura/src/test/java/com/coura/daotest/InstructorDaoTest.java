package com.coura.daotest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.coura.dao.CourseDao;
import com.coura.dao.InstructorDao;
import com.coura.model.Course;
import com.coura.model.CourseInstructor;
import com.coura.model.CourseInstructorWrapper;
import com.coura.model.Instructor;
import com.coura.model.Users;

@ContextConfiguration(locations = "classpath:Coura-servlet-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class InstructorDaoTest {
	
	@Autowired
	InstructorDao instructorDao;
	
	@Autowired
	CourseDao courseDao;
	
	@Test
	@Rollback(true)
	public void addInstructorTest() {
		Instructor i = new Instructor();
		CourseInstructor ci = new CourseInstructor();
		CourseInstructorWrapper wrapper = new CourseInstructorWrapper();
		List<Course> courseList = new ArrayList<Course>();
		courseList = courseDao.listAllCourses();
		i.setId(101);
		i.setFirstName("Instructor Fname");
		i.setLastName("Instructor Lname");
		i.setEmailId("flname@uncc.edu");
		i.setResearchInterest("Research Interest");
		ci.setCourseId(courseList.get(0).getId());
		ci.setInstructorId(101);
		wrapper.setCourseInstructor(ci);
		wrapper.setInstructor(i);
		
		assertTrue(instructorDao.addInstructor(wrapper));
	}
	
	@Test
	@Rollback(true)
	public void listAllInstructorsTest() {
		List<Instructor> instructorList = instructorDao.listAllInstructors();
		assertNotNull(instructorList);
	}
	
	

}
