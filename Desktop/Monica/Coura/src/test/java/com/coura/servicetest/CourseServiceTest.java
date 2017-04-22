package com.coura.servicetest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.coura.dao.CourseDao;
import com.coura.dao.InstructorDao;
import com.coura.model.Course;
import com.coura.model.Instructor;
import com.coura.service.CourseServiceImpl;

public class CourseServiceTest {
	
	private CourseServiceImpl courseService;
	private CourseDao courseDao;
	private InstructorDao instructorDao;
	private Course course1, course2;
	
	@Before
	public void setupMock() {
		courseService = new CourseServiceImpl();
		courseDao = mock(CourseDao.class);
		instructorDao = mock(InstructorDao.class);
		course1 = mock(Course.class);
		course2 = mock(Course.class);
		courseService.setCourseDao(courseDao);
		courseService.setInstructorDao(instructorDao);
	}
	
	@Test
	public void testFindCourseByNumber() {
		Integer courseId = 1;
		Course course = new Course(1,"ITCS 6112","Software Systems","Graduate Standing","Design Principles, Implementation, Testing");
		
		System.out.println("Stubbing findCourseByNumber(courseId) to return " + course);
		when(courseDao.findCourseByNumber(courseId)).thenReturn(course);
		
		System.out.println("Calling CourseService.findCourseByNumber(courseId)");
		courseService.findCourseByNumber(courseId);
		
		System.out.println("Verifying CourseDao.findCourseByNumber(courseId) is called");
		verify(courseDao).findCourseByNumber(courseId);
		
		assertNotNull(courseService.findCourseByNumber(courseId));
		assertEquals(course, (courseService.findCourseByNumber(courseId)));
	}
	
	@Test
	public void testIsExistingCourse() {
		Integer courseId1 = 1;
		Integer courseId2 = 2;
		
		System.out.println("Stubbing isExistingCourse(courseId1) to return " + true);
		when(courseDao.isExistingCourse(courseId1)).thenReturn(true);
		
		System.out.println("Stubbing isExistingCourse(courseId2) to return " + false);
		when(courseDao.isExistingCourse(courseId2)).thenReturn(false);
		
		System.out.println("Calling CourseService.isExistingCourse(courseId1)");
		courseService.isExistingCourse(courseId1);;
		
		System.out.println("Verifying CourseService.isExistingCourse(courseId2) is called");
		verify(courseDao).isExistingCourse(courseId1);
		
		System.out.println("Calling UsersService.isExistingEmailId(emailId2)");
		courseService.isExistingCourse(courseId2);;
		
		System.out.println("Verifying UsersDao.isExistingEmailId(emailId2) is called");
		verify(courseDao).isExistingCourse(courseId2);
		
		assertNotNull(courseService.isExistingCourse(courseId1));
		assertNotNull(courseService.isExistingCourse(courseId2));
		assertEquals(true, (courseService.isExistingCourse(courseId1)));
		assertEquals(false, (courseService.isExistingCourse(courseId2)));
	}
	
	@Test
	public void testSaveCourses() {

		System.out.println("Stubbing getId() to return " + 1);
		when(course1.getId()).thenReturn(1);
		
		System.out.println("Stubbing getId() to return " + 2);
		when(course2.getId()).thenReturn(2);
		
		System.out.println("Stubbing isExistingCourse(course1.getId()) to return " + true);
		when(courseDao.isExistingCourse(1)).thenReturn(true);
		
		System.out.println("Stubbing isExistingCourse(course2.getId()) to return " + false);
		when(courseDao.isExistingCourse(2)).thenReturn(false);
		
		System.out.println("Calling CourseService.saveCourses(course1)");
		courseService.saveCourses(course1);
		
		System.out.println("Verifying Course.getId() is called");
		verify(course1).getId();
		
		System.out.println("Verifying CourseDao.isExistingCourse(course1.getId()) is called");
		verify(courseDao).isExistingCourse(course1.getId());
		
		assertNotNull(courseService.saveCourses(course1));
		assertEquals(false, (courseService.saveCourses(course1)));
		
		System.out.println("Calling CourseService.saveCourses(course2)");
		courseService.saveCourses(course2);
		
		System.out.println("Verifying Course.getId() is called");
		verify(course2).getId();
		
		System.out.println("Verifying CourseDao.isExistingCourse(course2.getId()) is called");
		verify(courseDao).isExistingCourse(course2.getId());
		
		assertNotNull(courseService.saveCourses(course2));
		assertEquals(true, (courseService.saveCourses(course2)));
	}
	
	@Test
	public void testUpdateCourse() {
		
		System.out.println("Calling CourseService.saveCourses(course2)");
		courseService.updateCourse(course1);
		
		System.out.println("Verifying CourseDao.updateCourse(course1) is called");
		verify(courseDao).updateCourse(course1);
	}
	
	@Test
	public void testListAllCourses() {
		Course course = new Course(1,"ITCS 6112","Software Systems","Graduate Standing","Design Principles, Implementation, Testing");
		
		List<Course> list = new ArrayList<Course>();
		list.add(course);
		
		System.out.println("Stubbing listAllCourses() to return " + list);
		when(courseDao.listAllCourses()).thenReturn(list);
		
		System.out.println("Calling CourseService.listAllCourses()");
		courseService.listAllCourses();
		
		System.out.println("Verifying CourseDao.listAllCourses() is called");
		verify(courseDao).listAllCourses();
		
		assertNotNull(courseService.listAllCourses());
		assertEquals(list, (courseService.listAllCourses()));
		assertEquals(1, (courseService.listAllCourses().size()));
	}
	
	@Test
	public void testDeleteCourse() {
		Integer courseId = 1;
		
		System.out.println("Calling CourseService.deleteCourse()");
		courseService.deleteCourse(courseId);
		
		System.out.println("Verifying courseDao.deleteCourse() is called");
		verify(courseDao).deleteCourse(courseId);
	}
	
	@Test
	public void testGetCourseById() {
		Integer courseId = 1;
		Course course = new Course(1,"ITCS 6112","Software Systems","Graduate Standing","Design Principles, Implementation, Testing");
		List<Course> list = new ArrayList<Course>();
		list.add(course);
		
		System.out.println("Stubbing getCourseById(courseId) to return " + list);
		when(courseDao.getCourseById(courseId)).thenReturn(list);
		
		System.out.println("Calling CourseService.getCourseById(courseId)");
		courseService.getCourseById(courseId);
		
		System.out.println("Verifying CourseDao.getCourseById(courseId) is called");
		verify(courseDao).getCourseById(courseId);
		
		assertNotNull(courseService.getCourseById(courseId));
		assertEquals(list, (courseService.getCourseById(courseId)));
	}
	
	@Test
	public void testGetIdForCourse() {
		String courseNumber = "ITCS 6112";
		Integer courseId = 1;
		
		System.out.println("Stubbing getIdForCourse(courseNumber) to return " + 1);
		when(courseDao.getIdForCourse(courseNumber)).thenReturn(courseId);
		
		System.out.println("Calling CourseService.getIdForCourse(courseNumber)");
		courseService.getIdForCourse(courseNumber);
		
		System.out.println("Verifying CourseDao.getIdForCourse(courseNumber) is called");
		verify(courseDao).getIdForCourse(courseNumber);
		
		assertNotNull(courseService.getIdForCourse(courseNumber));
		assertEquals(courseId, (courseService.getIdForCourse(courseNumber)));
	}
	
	@Test
	public void testGetInstructorsForCourse() {
		Integer courseId = 1;
		Instructor instructor1 = new Instructor();
		Instructor instructor2 = new Instructor();
		
		List<Instructor> list = new ArrayList<Instructor>();
		list.add(instructor1);
		list.add(instructor2);
		
		System.out.println("Stubbing getInstructorsForCourse(courseId) to return " + list);
		when(instructorDao.getInstructorsForCourse(courseId)).thenReturn(list);
		
		System.out.println("Calling CourseService.getIdForCourse(courseId)");
		courseService.getInstructorsForCourse(courseId);
		
		System.out.println("Verifying InstructorDao.getInstructorsForCourse(courseId) is called");
		verify(instructorDao).getInstructorsForCourse(courseId);
		
		assertNotNull(courseService.getInstructorsForCourse(courseId));
		assertEquals(list, (courseService.getInstructorsForCourse(courseId)));
	}
	
	@Test
	public void testSearchCourses() {
		String courseNumber = "ITCS 6112";
		String courseName = "Software Systems";
		String areaOfInterest = "Design";
		String instructorName = "Harini Ramaprasad";
		
		Course course1 = new Course();
		Course course2 = new Course();
		
		List<Course> list1 = new ArrayList<Course>();
		list1.add(course1);
		list1.add(course2);
		
		System.out.println("Stubbing searchCourses(courseNumber,courseName,areaOfInterest,instructorName) to return " + list1);
		when(courseDao.searchCourses(courseNumber,courseName,areaOfInterest,instructorName)).thenReturn(list1);
		
		System.out.println("Calling CourseService.searchCourses(courseNumber,courseName,areaOfInterest,instructorName)");
		courseService.searchCourses(courseNumber,courseName,areaOfInterest,instructorName);
		
		System.out.println("Verifying CourseDao.searchCourses(courseNumber,courseName,areaOfInterest,instructorName) is called");
		verify(courseDao).searchCourses(courseNumber,courseName,areaOfInterest,instructorName);
		
		assertNotNull(courseService.searchCourses(courseNumber,courseName,areaOfInterest,instructorName));
		assertEquals(list1, (courseService.searchCourses(courseNumber,courseName,areaOfInterest,instructorName)));
	}
}