package com.coura.service;

import java.util.List;

import com.coura.model.Course;
import com.coura.model.Instructor;

public interface CourseService {
	
	public Course findCourseByNumber(Integer courseId);
	public boolean isExistingCourse(Integer courseId);
	public Course findCourseByNumber(String courseNumber);
	public boolean isExistingCourse(String courseNumber);
	public boolean saveCourses(Course course);
	public boolean updateCourse(Course course);
	public List<Course> listAllCourses();
	public void deleteCourse(Integer courseId);
	public List<Course> getCourseById(Integer courseId);
	public Integer getIdForCourse(String courseNumber);
	public List<Instructor> getInstructorsForCourse(Integer courseId);
	public List<Course> searchCourses(String courseNumber, String courseName, String areaOfInterest, String instructorName);
	public List<Course> listMostRecentlySearchedCourses();
	public List<Instructor> listAllInstructors();
	public List<Instructor> searchInstructors(String firstName, String lastName, String areaOfInterest);
	public List<Course> listRecommendedCourses(Integer courseId); 
}
