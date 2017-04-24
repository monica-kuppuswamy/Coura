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

import com.coura.dao.InstructorDao;
import com.coura.dao.InstructorReviewDao;
import com.coura.dao.UsersDao;
import com.coura.model.Instructor;
import com.coura.model.InstructorReview;
import com.coura.model.Users;

@ContextConfiguration(locations = "classpath:Coura-servlet-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class InstructorReviewDaoTest {
	
	@Autowired
	InstructorReviewDao instructorReviewDao;
	
	@Autowired
	InstructorDao instructorDao;
	
	@Autowired
	UsersDao userDao;
	
	@Test
	@Rollback(true)
	public void inserInstructorReviewTest() {
		List<Instructor> instructorList = new ArrayList<Instructor>();
		List<Users> userList = new ArrayList<Users>();
		instructorList = instructorDao.listAllInstructors();
		userList = userDao.listAllUsers();
		InstructorReview ir = new InstructorReview();
		ir.setInstructorId(instructorList.get(0).getId());
		ir.setUserEmailId(userList.get(0).getEmailId());
		ir.setReview("Execellent instructor");
		
		instructorReviewDao.insertInstructorReview(ir);
		
		List<InstructorReview> reviews = instructorReviewDao.getInstructorReviews(instructorList.get(0).getId());
		assertEquals(reviews.get(0).getUserEmailId(), ir.getUserEmailId());
		assertEquals(reviews.get(0).getReview(), ir.getReview());
	}
	
	@Test
	@Rollback(true)
	public void courseReviewedByUserTest() {
		
		List<Instructor> instructorList = new ArrayList<Instructor>();
		List<Users> userList = new ArrayList<Users>();
		instructorList = instructorDao.listAllInstructors();
		userList = userDao.listAllUsers();
		InstructorReview ir = new InstructorReview();
		ir.setInstructorId(instructorList.get(0).getId());
		ir.setUserEmailId(userList.get(0).getEmailId());
		ir.setReview("Execellent instructor");
		instructorReviewDao.insertInstructorReview(ir);
		
		InstructorReview ir1 = new InstructorReview();
		ir1.setInstructorId(instructorList.get(1).getId());
		ir1.setUserEmailId(userList.get(0).getEmailId());
		ir1.setReview("Good instructor");
		instructorReviewDao.insertInstructorReview(ir1);
		
		List<Integer> ids = instructorReviewDao.instructorReviewedByUser(userList.get(0).getEmailId());
		assertEquals(Integer.toString(ids.get(0)), Integer.toString(ir.getInstructorId()));
		assertEquals(Integer.toString(ids.get(1)), Integer.toString(ir1.getInstructorId()));
	}
}
