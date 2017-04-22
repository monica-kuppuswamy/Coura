package com.coura.servicetest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.coura.dao.RatingDao;
import com.coura.model.CourseRating;
import com.coura.service.RatingServiceImpl;

public class RatingServiceTest {
	
	private RatingServiceImpl ratingService;
	private RatingDao ratingDao;
	private CourseRating cr, cr1;
	
	@Before
	public void setupMock() {
		ratingService = new RatingServiceImpl();
		ratingDao = mock(RatingDao.class);
		cr = mock(CourseRating.class);
		cr1 = mock(CourseRating.class);
		ratingService.setRatingDao(ratingDao);
	}
	
	@Test
	public void testCourseRatedByUser() {
		String emailId = "spawar3@uncc.edu;";
		List<Integer> cids = new ArrayList<Integer>();
		cids.add(1);
		cids.add(4);
		
		System.out.println("Stubbing courseRatedByUser(emailId) to return " + cids);
		when(ratingDao.courseRatedByUser(emailId)).thenReturn(cids);
		
		System.out.println("Calling ratingService.courseRatedByUser(emailId)");
		ratingService.courseRatedByUser(emailId);
		
		System.out.println("Verifying ratingDao.courseRatedByUser(emailId) is called");
		verify(ratingDao).courseRatedByUser(emailId);
		
		assertNotNull(ratingService.courseRatedByUser(emailId));
		assertEquals(cids, (ratingService.courseRatedByUser(emailId)));
	}
	
	@Test
	public void testInsertCourseRating() {
		System.out.println("Calling ratingService.insertCourseRating(cr)");
		ratingService.insertCourseRating(cr);
		
		System.out.println("Verifying ratingDao.insertCourseRating(cr) is called");
		verify(ratingDao).insertCourseRating(cr);
	}
	
	@Test
	public void testGetCourseRating() {
		Integer courseId = 1;
		
		List<CourseRating> list = new ArrayList<CourseRating>();
		list.add(cr);
		list.add(cr1);
		
		System.out.println("Stubbing getCourseRating(courseId) to return " + list);
		when(ratingDao.getCourseRating(courseId)).thenReturn(list);
		
		System.out.println("Calling ratingService.getCourseRating(courseId)");
		ratingService.getCourseRating(courseId);
		
		System.out.println("Verifying ratingDao.getCourseRating(courseId) is called");
		verify(ratingDao).getCourseRating(courseId);
		
		assertNotNull(ratingService.getCourseRating(courseId));
		assertEquals(list, (ratingService.getCourseRating(courseId)));
	}
}