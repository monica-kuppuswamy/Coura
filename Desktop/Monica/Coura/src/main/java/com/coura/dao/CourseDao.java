package com.coura.dao;

import java.util.List;

import com.coura.model.Course;
import com.coura.model.Instructor;

public interface CourseDao {
	public Course findCourseByNumber(Integer courseId);
	public boolean isExistingCourse(Integer courseId);
	public Course findCourseByNumber(String courseNumber);
	public boolean isExistingCourse(String courseNumber);
	public void saveCourses(Course course);
	public void updateCourse(Course course);
	public List<Course> listAllCourses();
	public void deleteCourse(Integer courseId);
	public List<Course> getCourseById(Integer courseId);
	public Integer getIdForCourse(String courseNumber);
	public List<Course> getCoursesForInstructor(Integer instructorId);
	public List<Course> searchCourses(String courseNumber, String courseName, String areaOfInterest, String instructorName);
	public List<Course> listMostRecentlySearchedCourses();
	public List<Instructor> listAllInstructors();
	public List<Instructor> searchInstructors(String firstName, String lastName, String areaOfInterest);
}
