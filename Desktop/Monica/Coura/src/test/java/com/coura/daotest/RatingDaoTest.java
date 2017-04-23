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
import com.coura.dao.RatingDao;
import com.coura.dao.UsersDao;
import com.coura.model.Course;
import com.coura.model.CourseRating;
import com.coura.model.CourseReview;
import com.coura.model.Users;

@ContextConfiguration(locations = "classpath:Coura-servlet-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RatingDaoTest {
	
	@Autowired
	RatingDao ratingDao;
	
	@Autowired
	CourseDao courseDao;
	
	@Autowired
	UsersDao userDao;
	
	@Test
	@Rollback(true)
	public void insertRatingTest() {
		List<Course> courseList = new ArrayList<Course>();
		List<Users> userList = new ArrayList<Users>();
		courseList = courseDao.listAllCourses();
		userList = userDao.listAllUsers();
		CourseRating cr = new CourseRating();
		cr.setCourseId(courseList.get(0).getId());
		cr.setUserEmailId(userList.get(0).getEmailId());
		cr.setDifficultyRating(4);
		cr.setUsefulnessRating(3);
		ratingDao.insertCourseRating(cr);
		
		List<CourseRating> courseRating = ratingDao.getCourseRating(cr.getCourseId());
		assertEquals(courseRating.get(0).getUsefulnessRating(), cr.getUsefulnessRating());
		assertEquals(courseRating.get(0).getDifficultyRating(), cr.getDifficultyRating());
	}
	
	@Test
	@Rollback(true)
	public void courseRatedByUserTest() {
		
		List<Course> courseList = new ArrayList<Course>();
		List<Users> userList = new ArrayList<Users>();
		courseList = courseDao.listAllCourses();
		userList = userDao.listAllUsers();
		CourseRating cr = new CourseRating();
		cr.setCourseId(courseList.get(0).getId());
		cr.setUserEmailId(userList.get(0).getEmailId());
		cr.setDifficultyRating(4);
		cr.setUsefulnessRating(3);
		ratingDao.insertCourseRating(cr);
		
		CourseRating cr1 = new CourseRating();
		cr1.setCourseId(courseList.get(1).getId());
		cr1.setUserEmailId(userList.get(0).getEmailId());
		cr1.setDifficultyRating(4);
		cr1.setUsefulnessRating(3);
		ratingDao.insertCourseRating(cr1);
		
		List<Integer> ids = ratingDao.courseRatedByUser(userList.get(0).getEmailId());
		assertEquals(Integer.toString(ids.get(0)), Integer.toString(cr.getCourseId()));
		assertEquals(Integer.toString(ids.get(1)), Integer.toString(cr1.getCourseId()));
	}

}
