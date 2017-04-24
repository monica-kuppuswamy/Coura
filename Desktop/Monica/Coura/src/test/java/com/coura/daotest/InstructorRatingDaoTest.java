package com.coura.daotest;

import static org.junit.Assert.assertEquals;

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
import com.coura.dao.InstructorRatingDao;
import com.coura.dao.UsersDao;
import com.coura.model.Course;
import com.coura.model.Instructor;
import com.coura.model.InstructorRating;
import com.coura.model.Users;

@ContextConfiguration(locations = "classpath:Coura-servlet-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class InstructorRatingDaoTest {
	
	@Autowired
	InstructorRatingDao instructorRatingDao;
	
	@Autowired
	InstructorDao instructorDao;
	
	@Autowired
	CourseDao courseDao;
	
	@Autowired
	UsersDao userDao;
	
	@Test
	@Rollback(true)
	public void insertRatingTest() {
		List<Instructor> instructorList = new ArrayList<Instructor>();
		List<Users> userList = new ArrayList<Users>();
		List<Course> courseList = new ArrayList<Course>();
		instructorList = instructorDao.listAllInstructors();
		courseList = courseDao.listAllCourses();
		userList = userDao.listAllUsers();
		InstructorRating ir = new InstructorRating();
		ir.setInstructorId(instructorList.get(0).getId());
		ir.setCourseId(courseList.get(0).getId());
		ir.setUserEmailId(userList.get(0).getEmailId());
		ir.setQualityOfTeachingRating(4);
		ir.setGradingStyleRating(3);
		ir.setLeniencyRating(2);
		instructorRatingDao.insertInstructorRating(ir);
		
		List<InstructorRating> instructorRating = instructorRatingDao.getInstructorRating(ir.getInstructorId());
		assertEquals(instructorRating.get(0).getQualityOfTeachingRating(), ir.getQualityOfTeachingRating());
		assertEquals(instructorRating.get(0).getGradingStyleRating(), ir.getGradingStyleRating());
		assertEquals(instructorRating.get(0).getLeniencyRating(), ir.getLeniencyRating());
	}
	
	@Test
	@Rollback(true)
	public void instructorRatedByUserTest() {
		
		List<Instructor> instructorList = new ArrayList<Instructor>();
		List<Course> courseList = new ArrayList<Course>();
		List<Users> userList = new ArrayList<Users>();
		instructorList = instructorDao.listAllInstructors();
		courseList = courseDao.listAllCourses();
		userList = userDao.listAllUsers();
		InstructorRating ir = new InstructorRating();
		ir.setInstructorId(instructorList.get(0).getId());
		ir.setCourseId(courseList.get(0).getId());
		ir.setUserEmailId(userList.get(0).getEmailId());
		ir.setQualityOfTeachingRating(4);
		ir.setGradingStyleRating(3);
		ir.setLeniencyRating(2);
		instructorRatingDao.insertInstructorRating(ir);
		
		InstructorRating ir1 = new InstructorRating();
		ir1.setInstructorId(instructorList.get(1).getId());
		ir1.setCourseId(courseList.get(1).getId());
		ir1.setUserEmailId(userList.get(0).getEmailId());
		ir1.setQualityOfTeachingRating(1);
		ir1.setGradingStyleRating(2);
		ir1.setLeniencyRating(3);
		instructorRatingDao.insertInstructorRating(ir1);
		
		List<Integer> ids = instructorRatingDao.instructorRatedByUser(userList.get(0).getEmailId());
		assertEquals(Integer.toString(ids.get(0)), Integer.toString(ir.getInstructorId()));
		assertEquals(Integer.toString(ids.get(1)), Integer.toString(ir1.getInstructorId()));
	}
	
	@Test
	@Rollback(true)
	public void courseRatedByUserTest() {
		
		List<Instructor> instructorList = new ArrayList<Instructor>();
		List<Course> courseList = new ArrayList<Course>();
		List<Users> userList = new ArrayList<Users>();
		instructorList = instructorDao.listAllInstructors();
		courseList = courseDao.listAllCourses();
		userList = userDao.listAllUsers();
		InstructorRating ir = new InstructorRating();
		ir.setInstructorId(instructorList.get(0).getId());
		ir.setCourseId(courseList.get(0).getId());
		ir.setUserEmailId(userList.get(0).getEmailId());
		ir.setQualityOfTeachingRating(4);
		ir.setGradingStyleRating(3);
		ir.setLeniencyRating(2);
		instructorRatingDao.insertInstructorRating(ir);
		
		InstructorRating ir1 = new InstructorRating();
		ir1.setInstructorId(instructorList.get(1).getId());
		ir1.setCourseId(courseList.get(1).getId());
		ir1.setUserEmailId(userList.get(0).getEmailId());
		ir1.setQualityOfTeachingRating(1);
		ir1.setGradingStyleRating(2);
		ir1.setLeniencyRating(3);
		instructorRatingDao.insertInstructorRating(ir1);
		
		List<Integer> ids = instructorRatingDao.courseRatedByUser(userList.get(0).getEmailId());
		assertEquals(Integer.toString(ids.get(0)), Integer.toString(ir.getCourseId()));
		assertEquals(Integer.toString(ids.get(1)), Integer.toString(ir1.getCourseId()));
	}

}
