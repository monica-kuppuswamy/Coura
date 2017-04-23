package com.coura.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coura.dao.CourseDao;
import com.coura.dao.InstructorDao;
import com.coura.model.Course;
import com.coura.model.Instructor;

@Service
public class CourseServiceImpl implements CourseService {

	private CourseDao courseDao;
	private InstructorDao instructorDao;

	public void setInstructorDao(InstructorDao instructorDao) {
		this.instructorDao = instructorDao;
	}

	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}
	
	@Override
	@Transactional
	public Course findCourseByNumber(Integer courseId) {
		return this.courseDao.findCourseByNumber(courseId);
	}
	
	@Override
	@Transactional
	public Course findCourseByNumber(String courseNumber) {
		return this.courseDao.findCourseByNumber(courseNumber);
	}
	
	@Override
	@Transactional
	public boolean isExistingCourse(Integer courseId) {
		return this.courseDao.isExistingCourse(courseId);
	}
	
	@Override
	@Transactional
	public boolean isExistingCourse(String courseNumber) {
		return this.courseDao.isExistingCourse(courseNumber);
	}
	
	@Override
	@Transactional
	public boolean saveCourses(Course course) {
		if (this.isExistingCourse(course.getId())) {
			return false;
		} else {
			this.courseDao.saveCourses(course);
			return true;
		}
	}
	
	@Override
	@Transactional
	public boolean updateCourse(Course course) {
		if (this.isExistingCourse(course.getCourseNumber())) {
			return false;
		} else {
			this.courseDao.updateCourse(course);
			return true;
		}
	}
	
	@Override
	@Transactional
	public List<Course> listAllCourses() {
		return this.courseDao.listAllCourses();
	}
	
	@Override
	@Transactional
	public void deleteCourse(Integer courseId) {
		this.courseDao.deleteCourse(courseId);
	}
	
	@Override
	@Transactional
	public List<Course> getCourseById(Integer courseId) {
		return this.courseDao.getCourseById(courseId);
	}
	
	@Override
	@Transactional
	public  Integer getIdForCourse(String courseNumber) {
		return this.courseDao.getIdForCourse(courseNumber);
	}
	
	@Override
	@Transactional
	public List<Instructor> getInstructorsForCourse(Integer courseId) {
		return this.instructorDao.getInstructorsForCourse(courseId);
	}
	
	@Override
	@Transactional
	public List<Course> searchCourses(String courseNumber, String courseName, String areaOfInterest, String instructorName) {
		return this.courseDao.searchCourses(courseNumber, courseName, areaOfInterest, instructorName);
	}
	@Override
	@Transactional
	public List<Course> listMostRecentlySearchedCourses() {
		return this.courseDao.listMostRecentlySearchedCourses();
	}

}