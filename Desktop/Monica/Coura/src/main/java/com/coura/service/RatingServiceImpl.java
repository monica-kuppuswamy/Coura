package com.coura.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.coura.dao.RatingDao;
import com.coura.model.CourseRating;

public class RatingServiceImpl implements RatingService {
	
	private RatingDao ratingDao;

	public void setRatingDao(RatingDao ratingDao) {
		this.ratingDao = ratingDao;
	}

	@Override
	@Transactional
	public List<Integer> courseRatedByUser(String emailId) {
		
		// TODO Auto-generated method stub
		List<Integer> cIds = this.ratingDao.courseRatedByUser(emailId);
		return cIds;
	}

	@Override
	@Transactional
	public void insertCourseRating(CourseRating cr) {
		
		// TODO Auto-generated method stub
		this.ratingDao.insertCourseRating(cr);
	}

	@Override
	@Transactional
	public List<CourseRating> getCourseRating(Integer courseId) {
		
		// TODO Auto-generated method stub
		List<CourseRating> courseRating = this.ratingDao.getCourseRating(courseId);
		return courseRating;
	}
}
