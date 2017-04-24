package com.coura.daotest;

import static org.junit.Assert.assertEquals;
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
import com.coura.dao.RegistrationDao;
import com.coura.dao.UsersDao;
import com.coura.model.Course;
import com.coura.model.Instructor;
import com.coura.model.StudentCourse;
import com.coura.model.Users;

@ContextConfiguration(locations = "classpath:Coura-servlet-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RegistrationDaoTest {

	@Autowired
	RegistrationDao registrationDao;
	
	@Autowired
	InstructorDao instructorDao;
	
	@Autowired
	CourseDao courseDao;
	
	@Autowired
	UsersDao userDao;
	
	@Test
	@Rollback(true)
	public void testInsertRegistrationDetails() {
		
		List<Instructor> instructorList = new ArrayList<Instructor>();
		List<Users> userList = new ArrayList<Users>();
		List<Course> courseList = new ArrayList<Course>();
		instructorList = instructorDao.listAllInstructors();
		courseList = courseDao.listAllCourses();
		userList = userDao.listAllUsers();
		
		StudentCourse sc = new StudentCourse();
		sc.setInstructorId(instructorList.get(0).getId());
		sc.setCourseId(courseList.get(0).getId());
		sc.setUserEmailId(userList.get(0).getEmailId());
		
		registrationDao.insertRegistrationDetails(sc);
		
		List<Course> courses = registrationDao.getEnrolledCourses(userList.get(0).getEmailId());
		assertEquals(Integer.toString(courses.get(0).getId()), Integer.toString(sc.getCourseId()));
	}
	
	@Test
	@Rollback(true)
	public void testGetEnrolledCourses() {
		
		List<Instructor> instructorList = new ArrayList<Instructor>();
		List<Users> userList = new ArrayList<Users>();
		List<Course> courseList = new ArrayList<Course>();
		instructorList = instructorDao.listAllInstructors();
		courseList = courseDao.listAllCourses();
		userList = userDao.listAllUsers();
		
		StudentCourse sc = new StudentCourse();
		sc.setInstructorId(instructorList.get(0).getId());
		sc.setCourseId(courseList.get(0).getId());
		sc.setUserEmailId(userList.get(0).getEmailId());
		
		registrationDao.insertRegistrationDetails(sc);
		
		StudentCourse sc1 = new StudentCourse();
		sc1.setInstructorId(instructorList.get(1).getId());
		sc1.setCourseId(courseList.get(1).getId());
		sc1.setUserEmailId(userList.get(0).getEmailId());
		
		registrationDao.insertRegistrationDetails(sc1);
		
		List<Course> courses = registrationDao.getEnrolledCourses(userList.get(0).getEmailId());
		assertEquals(Integer.toString(courses.get(0).getId()), Integer.toString(sc.getCourseId()));
		assertEquals(Integer.toString(courses.get(1).getId()), Integer.toString(sc1.getCourseId()));
		
	}
	
	@Test
	@Rollback(true)
	public void testUnEnroll() {
		
		List<Instructor> instructorList = new ArrayList<Instructor>();
		List<Users> userList = new ArrayList<Users>();
		List<Course> courseList = new ArrayList<Course>();
		instructorList = instructorDao.listAllInstructors();
		courseList = courseDao.listAllCourses();
		userList = userDao.listAllUsers();
		
		StudentCourse sc = new StudentCourse();
		sc.setInstructorId(instructorList.get(0).getId());
		sc.setCourseId(courseList.get(0).getId());
		sc.setUserEmailId(userList.get(0).getEmailId());
		
		registrationDao.insertRegistrationDetails(sc);
		registrationDao.unEnrollCourse(userList.get(0).getEmailId(), courseList.get(0).getId());
		
		List<Course> courses = registrationDao.getEnrolledCourses(userList.get(0).getEmailId());
		assertTrue(courses.isEmpty());
	}

}
