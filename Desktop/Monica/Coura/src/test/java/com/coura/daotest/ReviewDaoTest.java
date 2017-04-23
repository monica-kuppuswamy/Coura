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
import com.coura.dao.ReviewDao;
import com.coura.dao.UsersDao;
import com.coura.model.Course;
import com.coura.model.CourseReview;
import com.coura.model.Users;

@ContextConfiguration(locations = "classpath:Coura-servlet-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ReviewDaoTest {
	
	@Autowired
	ReviewDao reviewDao;
	
	@Autowired
	CourseDao courseDao;
	
	@Autowired
	UsersDao userDao;
	
	@Test
	@Rollback(true)
	public void inserCourseReviewTest() {
		List<Course> courseList = new ArrayList<Course>();
		List<Users> userList = new ArrayList<Users>();
		courseList = courseDao.listAllCourses();
		userList = userDao.listAllUsers();
		CourseReview cr = new CourseReview();
		cr.setCourseId(courseList.get(0).getId());
		cr.setUserEmailId(userList.get(0).getEmailId());
		cr.setReview("Execellent course");
		
		reviewDao.insertCourseReview(cr);
		
		List<CourseReview> reviews = reviewDao.getCourseReviews(courseList.get(0).getId());
		assertEquals(reviews.get(0).getUserEmailId(), cr.getUserEmailId());
		assertEquals(reviews.get(0).getReview(), cr.getReview());
	}
	
	@Test
	@Rollback(true)
	public void courseReviewedByUserTest() {
		
		List<Course> courseList = new ArrayList<Course>();
		List<Users> userList = new ArrayList<Users>();
		courseList = courseDao.listAllCourses();
		userList = userDao.listAllUsers();
		CourseReview cr = new CourseReview();
		cr.setCourseId(courseList.get(0).getId());
		cr.setUserEmailId(userList.get(0).getEmailId());
		cr.setReview("Execellent course");
		reviewDao.insertCourseReview(cr);
		
		CourseReview cr1 = new CourseReview();
		cr1.setCourseId(courseList.get(1).getId());
		cr1.setUserEmailId(userList.get(0).getEmailId());
		cr1.setReview("Good course");
		reviewDao.insertCourseReview(cr1);
		
		List<Integer> ids = reviewDao.courseReviewedByUser(userList.get(0).getEmailId());
		assertEquals(Integer.toString(ids.get(0)), Integer.toString(cr.getCourseId()));
		assertEquals(Integer.toString(ids.get(1)), Integer.toString(cr1.getCourseId()));
	}
}
