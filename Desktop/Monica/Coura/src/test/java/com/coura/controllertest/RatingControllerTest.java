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

import com.coura.app.RatingController;
import com.coura.model.CourseRating;
import com.coura.service.RatingService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
 
@ContextConfiguration(locations = "classpath:Coura-servlet-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class RatingControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	@InjectMocks
	private RatingController ratingController;
	
    @Mock
    private RatingService ratingService;
 
    @Before
    public void setUp() {
        
    	MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(ratingController).build();
    }
    
    @Test
    public void testGetCourseRatedByUser() throws Exception {
    	
    	List<Integer> cIds = new ArrayList<Integer>();
        cIds.add(1);
        cIds.add(2);
        
        when(ratingService.courseRatedByUser("user@uncc.edu")).thenReturn(cIds);
 
        mockMvc.perform(get("/ratingservice/getcourserated/{id}", "user@uncc.edu"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
 		
        verify(ratingService, times(1)).courseRatedByUser("user@uncc.edu");
        verifyNoMoreInteractions(ratingService);
    }
    
    @Test
    public void testGetCourseRating() throws Exception {
    	
    	CourseRating cr = new CourseRating();
    	cr.setCourseId(1);
    	cr.setUserEmailId("user@uncc.edu");
    	cr.setDifficultyRating(2);
    	cr.setUsefulnessRating(4);
    	
    	List<CourseRating> list = new ArrayList<CourseRating>();
    	list.add(cr);
    	
    	when(ratingService.getCourseRating(1)).thenReturn(list);
    	 
        mockMvc.perform(get("/ratingservice/getrating/{courseId}", 1))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].courseId", is(1)))
        .andExpect(jsonPath("$[0].userEmailId", is("user@uncc.edu")))
        .andExpect(jsonPath("$[0].difficultyRating", is(2)))
        .andExpect(jsonPath("$[0].usefulnessRating", is(4)));
    	
        verify(ratingService, times(1)).getCourseRating(1);
        verifyNoMoreInteractions(ratingService);
    }
    
    @Test
    public void testInsertRating() throws Exception {
    	
    	CourseRating cr = new CourseRating();
    	cr.setCourseId(1);
    	cr.setUserEmailId("user@uncc.edu");
    	cr.setDifficultyRating(2);
    	cr.setUsefulnessRating(4);
    	
    	doNothing().when(ratingService);
    	
    	mockMvc.perform(post("/ratingservice/insertrating").content(mapToJson(cr)))
    	.andExpect(status().isCreated());
    	
        verify(ratingService, times(1)).insertCourseRating(cr);
        verifyNoMoreInteractions(ratingService);
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
