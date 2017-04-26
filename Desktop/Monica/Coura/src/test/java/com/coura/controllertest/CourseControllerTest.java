package com.coura.controllertest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.coura.app.CourseController;
import com.coura.model.Course;
import com.coura.model.Instructor;
import com.coura.service.CourseService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
 
@ContextConfiguration(locations = "classpath:Coura-servlet-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CourseControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	@InjectMocks
	private CourseController courseController;
	
    @Mock
    private CourseService courseService;
 
    @Before
    public void setUp() {
        
    	MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
    }
    
    @Test
    public void testGetCourses() throws Exception {
        Course course1 = new Course(1,"ITCS6112","Software Development","Graduate Standing","Software development metnods");
        Course course2 = new Course(2,"ITCS4114","Cognitive Science","Graduate Standing","Consciousness");
        List<Course> courses = new ArrayList<Course>();
        courses.add(course1);
        courses.add(course2);
        
        when(courseService.listAllCourses()).thenReturn(courses);
 
        mockMvc.perform(get("/courseservice/getcourses"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[0].courseNumber", is("ITCS6112")))
        .andExpect(jsonPath("$[0].courseName", is("Software Development")))
        .andExpect(jsonPath("$[0].prerequisite", is("Graduate Standing")))
        .andExpect(jsonPath("$[0].description", is("Software development metnods")))
        .andExpect(jsonPath("$[1].id", is(2)))
        .andExpect(jsonPath("$[1].courseNumber", is("ITCS4114")))
        .andExpect(jsonPath("$[1].courseName", is("Cognitive Science")))
        .andExpect(jsonPath("$[1].prerequisite", is("Graduate Standing")))
        .andExpect(jsonPath("$[1].description", is("Consciousness")));
 		
        verify(courseService, times(1)).listAllCourses();
        verifyNoMoreInteractions(courseService);
    }
    
    @Test
    public void testGetCourseById() throws Exception {
        
    	Course course = new Course(1,"ITCS6112","Software Development","Graduate Standing","Software development metnods");
        List<Course> courses = new ArrayList<Course>();
        courses.add(course);
        
        when(courseService.getCourseById(1)).thenReturn(courses);
        
        mockMvc.perform(get("/courseservice/getcourse/{id}", 1))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[0].courseNumber", is("ITCS6112")))
        .andExpect(jsonPath("$[0].courseName", is("Software Development")))
        .andExpect(jsonPath("$[0].prerequisite", is("Graduate Standing")))
        .andExpect(jsonPath("$[0].description", is("Software development metnods")));
        
        verify(courseService, times(1)).getCourseById(1);
        verifyNoMoreInteractions(courseService);
    }
    
    @Test
    public void testSaveCourse() throws Exception {
    	Course course = new Course(1,"ITCS6112","Software Development","Graduate Standing","Software development metnods");
    	
    	when(courseService.saveCourses(course)).thenReturn(true);
    	
    	mockMvc.perform(post("/courseservice/savecourse").contentType(MediaType.APPLICATION_JSON).content(mapToJson(course)))
        .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE));
        
        verify(courseService, times(1)).saveCourses(course);
        verifyNoMoreInteractions(courseService);
    }
    
    @Test
    public void testUpdateCourse() throws Exception {
    	Course course = new Course(1,"ITCS6112","Software Development","Graduate Standing","Software development metnods");
    	
    	when(courseService.updateCourse(course)).thenReturn(true);
    	
    	mockMvc.perform(
                post("/courseservice/updatecourse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(course)))
        .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE));
        
        verify(courseService, times(1)).updateCourse(course);
        verifyNoMoreInteractions(courseService);
    }
    
    @Test
    public void testDeleteCourse() throws Exception {
        
    	doNothing().when(courseService);
    	
        mockMvc.perform(get("/courseservice/deletecourse/{courseId}", 1))
        .andExpect(status().isOk());
        
        verify(courseService, times(1)).deleteCourse(1);
        verifyNoMoreInteractions(courseService);
    }
    
    @Test
    public void testGetCourseId() throws Exception {
        
    	when(courseService.getIdForCourse("ITCS6112")).thenReturn(1);
        
    	mockMvc.perform(get("/courseservice/getcourseId/{courseNumber}", "ITCS6112"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
        
        verify(courseService, times(1)).getCourseById(1);
        verifyNoMoreInteractions(courseService);
    }
    
    @Test
    public void testGetInstructorForCourse() throws Exception {
        
    	Instructor instructor1 = new Instructor();
    	instructor1.setId(1);
    	instructor1.setFirstName("John");
    	instructor1.setLastName("Paul");
    	instructor1.setEmailId("john@uncc.edu");
    	instructor1.setResearchInterest("None");
    	Instructor instructor2 = new Instructor();
    	instructor2.setId(1);
    	instructor2.setFirstName("Maria");
    	instructor2.setLastName("Clemson");
    	instructor2.setEmailId("maria@uncc.edu");
    	instructor2.setResearchInterest("Neural Networks");
    	
    	List<Instructor> list = new ArrayList<Instructor>();
    	list.add(instructor1);
    	list.add(instructor2);
    	
    	when(courseService.getInstructorsForCourse(1)).thenReturn(list);
        
    	mockMvc.perform(get("/courseservice/getinstructorforcourse/{id}", 1))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
    	.andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[0].firstName", is("John")))
        .andExpect(jsonPath("$[0].lastName", is("Paul")))
        .andExpect(jsonPath("$[0].emailId", is("john@uncc.edu")))
        .andExpect(jsonPath("$[0].researchInterest", is("None")))
    	.andExpect(jsonPath("$[1].id", is(1)))
        .andExpect(jsonPath("$[1].firstName", is("Maria")))
        .andExpect(jsonPath("$[1].lastName", is("Clemson")))
        .andExpect(jsonPath("$[1].emailId", is("maria@uncc.edu")))
        .andExpect(jsonPath("$[1].researchInterest", is("Neural Networks")));
    	
        verify(courseService, times(1)).getInstructorsForCourse(1);
        verifyNoMoreInteractions(courseService);
    }
    
    @Test
    public void testSearchCourses() throws Exception {
    	
        Course course1 = new Course(1,"ITCS6112","Software Development","Graduate Standing","Software development methods");
        
        List<Course> courses = new ArrayList<Course>();
        courses.add(course1);
        
        when(courseService.searchCourses("ITCS6112", " ", " ", " ")).thenReturn(courses);
 
        mockMvc.perform(get("/courseservice/searchcourses?courseNumber=" + "ITCS6112" + "&courseName=" + " " + "&areaOfInterest=" + " " + "&instructorName=" + " "))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[0].courseNumber", is("ITCS6112")))
        .andExpect(jsonPath("$[0].courseName", is("Software Development")))
        .andExpect(jsonPath("$[0].prerequisite", is("Graduate Standing")))
        .andExpect(jsonPath("$[0].description", is("Software development methods")));
 		
        verify(courseService, times(1)).listAllCourses();
        verifyNoMoreInteractions(courseService);
        
        when(courseService.searchCourses(" ", "Software Development", " ", " ")).thenReturn(courses);
        
        mockMvc.perform(get("/courseservice/searchcourses?courseNumber=" + " " + "&courseName=" + "Software Development" + "&areaOfInterest=" + " " + "&instructorName=" + " "))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[0].courseNumber", is("ITCS6112")))
        .andExpect(jsonPath("$[0].courseName", is("Software Development")))
        .andExpect(jsonPath("$[0].prerequisite", is("Graduate Standing")))
        .andExpect(jsonPath("$[0].description", is("Software development methods")));
        
        verify(courseService, times(1)).listAllCourses();
        verifyNoMoreInteractions(courseService);
        
        when(courseService.searchCourses(" ", " ", "Software", " ")).thenReturn(courses);
        
        mockMvc.perform(get("/courseservice/searchcourses?courseNumber=" + " " + "&courseName=" + " " + "&areaOfInterest=" + "Software" + "&instructorName=" + " "))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[0].courseNumber", is("ITCS6112")))
        .andExpect(jsonPath("$[0].courseName", is("Software Development")))
        .andExpect(jsonPath("$[0].prerequisite", is("Graduate Standing")))
        .andExpect(jsonPath("$[0].description", is("Software development methods")));
        
        verify(courseService, times(1)).listAllCourses();
        verifyNoMoreInteractions(courseService);
        
        when(courseService.searchCourses(" ", " ", " ", "John Paul")).thenReturn(courses);
        
        mockMvc.perform(get("/courseservice/searchcourses?courseNumber=" + " " + "&courseName=" + " " + "&areaOfInterest=" + " " + "&instructorName=" + "John Paul"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[0].courseNumber", is("ITCS6112")))
        .andExpect(jsonPath("$[0].courseName", is("Software Development")))
        .andExpect(jsonPath("$[0].prerequisite", is("Graduate Standing")))
        .andExpect(jsonPath("$[0].description", is("Software development methods")));
        
        verify(courseService, times(1)).listAllCourses();
        verifyNoMoreInteractions(courseService);
    }
    
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, clazz);
    }
}