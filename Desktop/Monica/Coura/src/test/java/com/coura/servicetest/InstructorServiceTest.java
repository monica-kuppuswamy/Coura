package com.coura.servicetest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.coura.dao.InstructorDao;
import com.coura.model.CourseInstructorWrapper;
import com.coura.model.Instructor;
import com.coura.service.InstructorServiceImpl;

public class InstructorServiceTest {
	
	private InstructorServiceImpl instructorService;
	private InstructorDao instructorDao;
	private CourseInstructorWrapper wrapper;
	private Instructor instructor;
	
	@Before
	public void setupMock() {
		instructorService = new InstructorServiceImpl();
		instructorDao = mock(InstructorDao.class);
		wrapper = mock(CourseInstructorWrapper.class);
		instructor = mock(Instructor.class);
		instructorService.setInstructorDao(instructorDao);
	}
	
	@Test
	public void testAddInstructor() {
		System.out.println("Stubbing addInstructor(wrapper) to return " + true);
		when(instructorDao.addInstructor(wrapper)).thenReturn(true);
		
		System.out.println("Calling instructorService.addInstructor(wrapper)");
		instructorService.addInstructor(wrapper);
		
		System.out.println("Verifying CourseDao.addInstructor(wrapper) is called");
		verify(instructorDao).addInstructor(wrapper);
		
		assertNotNull(instructorService.addInstructor(wrapper));
		assertEquals(true, (instructorService.addInstructor(wrapper)));
	}
	
	@Test
	public void testDeleteInstructor() {
		Integer instructorId = 1;
		
		System.out.println("Calling instructorService.deleteInstructor(instructorId)");
		instructorService.deleteInstructor(instructorId);
		
		System.out.println("Verifying courseDao.deleteInstructor(instructorId) is called");
		verify(instructorDao).deleteInstructor(instructorId);
	}
	
	@Test
	public void testListAllInstructors() {

		List<Instructor> list = new ArrayList<Instructor>();
		list.add(instructor);
		
		System.out.println("Stubbing listAllInstructors() to return " + list);
		when(instructorDao.listAllInstructors()).thenReturn(list);
		
		System.out.println("Calling instructorService.listAllInstructors()");
		instructorService.listAllInstructors();
		
		System.out.println("Verifying instructorDao.listAllInstructors() is called");
		verify(instructorDao).listAllInstructors();
		
		assertNotNull(instructorService.listAllInstructors());
		assertEquals(list, (instructorService.listAllInstructors()));
		assertEquals(1, (instructorService.listAllInstructors().size()));
	}
}