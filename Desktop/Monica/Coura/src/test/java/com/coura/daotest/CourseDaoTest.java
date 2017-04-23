package com.coura.daotest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import com.coura.model.Course;
import com.coura.model.Users;

@ContextConfiguration(locations = "classpath:Coura-servlet-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CourseDaoTest {

	@Autowired
	private CourseDao courseDao;
	
	@Test
	@Rollback(true)
	public void saveCoursesTest() {
		Course c1 = new Course();
		c1.setId(101);
		c1.setCourseName("Applied Databases");
		c1.setCourseNumber("ITIS6523");
		c1.setDescription("Introduction to Applied Databases");
		c1.setPrerequisite("Instructor Approval");
		
		courseDao.saveCourses(c1);
		Course findCourse = courseDao.findCourseByNumber(c1.getId());
		assertEquals(findCourse.getId(), c1.getId());
	}
	
	@Test
	@Rollback(true)
	public void isExistingCourseTest() {
		Course c1 = new Course();
		c1.setId(101);
		c1.setCourseName("Applied Databases");
		c1.setCourseNumber("ITIS6523");
		c1.setDescription("Introduction to Applied Databases");
		c1.setPrerequisite("Instructor Approval");
		
		courseDao.saveCourses(c1);
		assertTrue(courseDao.isExistingCourse(c1.getId()));
	}
	
	@Test
	@Rollback(true)
	public void updateCourseTest() {
		Course c1 = new Course();
		c1.setId(101);
		c1.setCourseName("Applied Databases");
		c1.setCourseNumber("ITIS6523");
		c1.setDescription("Introduction to Applied Databases");
		c1.setPrerequisite("Instructor Approval");
		courseDao.saveCourses(c1);
		Course findCourse = courseDao.findCourseByNumber(c1.getId());
		assertEquals(findCourse.getId(), c1.getId());
		assertEquals(findCourse.getCourseName(), c1.getCourseName());
		assertEquals(findCourse.getDescription(), c1.getDescription());
		assertEquals(findCourse.getPrerequisite(), c1.getPrerequisite());
		
		c1.setPrerequisite("CCI Graduate");
		courseDao.updateCourse(c1);
		Course findCourse2 = courseDao.findCourseByNumber(c1.getId());
		assertEquals(findCourse2.getId(), c1.getId());
		assertEquals(findCourse2.getCourseName(), c1.getCourseName());
		assertEquals(findCourse2.getDescription(), c1.getDescription());
		assertEquals(findCourse2.getPrerequisite(), c1.getPrerequisite());
	}
	
	@Test
	@Rollback(true)
	public void listAllCoursesTest() {
		List<Course> courseList = new ArrayList<Course>();
		assertNotNull(courseList);
	}
	
	@Test
	@Rollback(true)
	public void deleteCourseTest() {
		List<Course> courseList = courseDao.listAllCourses();
		courseDao.deleteCourse(courseList.get(0).getId());
		assertFalse(courseDao.isExistingCourse(courseList.get(0).getId()));
	}
	
	@Test
	@Rollback(true)
	public void getCourseByIdTest() {
		Course c1 = new Course();
		c1.setId(101);
		c1.setCourseName("Applied Databases");
		c1.setCourseNumber("ITIS6523");
		c1.setDescription("Introduction to Applied Databases");
		c1.setPrerequisite("Instructor Approval");
		courseDao.saveCourses(c1);
		
		List<Course> course = courseDao.getCourseById(c1.getId());
		assertEquals(course.get(0).getCourseName(), c1.getCourseName());
		assertEquals(course.get(0).getCourseNumber(), c1.getCourseNumber());
		assertEquals(course.get(0).getDescription(), c1.getDescription());
		assertEquals(course.get(0).getPrerequisite(), c1.getPrerequisite());
	}
	
	@Test
	@Rollback(true)
	public void getIdForCourseTest() {
		Course c1 = new Course();
		c1.setId(101);
		c1.setCourseName("Applied Databases");
		c1.setCourseNumber("ITIS6523");
		c1.setDescription("Introduction to Applied Databases");
		c1.setPrerequisite("Instructor Approval");
		courseDao.saveCourses(c1);
		assertEquals(c1.getId(), courseDao.getIdForCourse(c1.getCourseNumber()));
	}
}