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

import com.coura.app.ReviewController;
import com.coura.model.CourseReview;
import com.coura.service.ReviewService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
 
@ContextConfiguration(locations = "classpath:Coura-servlet-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ReviewControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	@InjectMocks
	private ReviewController reviewController;
	
    @Mock
    private ReviewService reviewService;
 
    @Before
    public void setUp() {
        
    	MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();
    }
    
    @Test
    public void testGetCourseReviewedByUser() throws Exception {
    	
    	List<Integer> cIds = new ArrayList<Integer>();
        cIds.add(1);
        cIds.add(2);
        
        when(reviewService.courseReviewedByUser("user@uncc.edu")).thenReturn(cIds);
 
        mockMvc.perform(get("/reviewservice/getcoursereviewed/{id}", "user@uncc.edu"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
 		
        verify(reviewService, times(1)).courseReviewedByUser("user@uncc.edu");
        verifyNoMoreInteractions(reviewService);
    }
    
    @Test
    public void testGetCourseReview() throws Exception {
    	
    	CourseReview cr = new CourseReview();
    	cr.setCourseId(1);
    	cr.setUserEmailId("user@uncc.edu");
    	cr.setReview("Good course");
    	
    	List<CourseReview> list = new ArrayList<CourseReview>();
    	list.add(cr);
    	
    	when(reviewService.getCourseReviews(1)).thenReturn(list);
    	 
        mockMvc.perform(get("/reviewservice/getreview/{courseId}", 1))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].courseId", is(1)))
        .andExpect(jsonPath("$[0].userEmailId", is("user@uncc.edu")))
        .andExpect(jsonPath("$[0].review", is("Good course")));
    	
        verify(reviewService, times(1)).getCourseReviews(1);
        verifyNoMoreInteractions(reviewService);
    }
    
    @Test
    public void testInsertRating() throws Exception {
    	
    	CourseReview cr = new CourseReview();
    	cr.setCourseId(1);
    	cr.setUserEmailId("user@uncc.edu");
    	cr.setReview("Good course");
    	
    	doNothing().when(reviewService);
    	
    	mockMvc.perform(post("/reviewservice/insertrating").content(mapToJson(cr)))
    	.andExpect(status().isCreated());
    	
        verify(reviewService, times(1)).insertCourseReview(cr);
        verifyNoMoreInteractions(reviewService);
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