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

import com.coura.app.InstructorReviewController;
import com.coura.model.InstructorReview;
import com.coura.service.InstructorReviewService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
 
@ContextConfiguration(locations = "classpath:Coura-servlet-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class InstructorReviewControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	@InjectMocks
	private InstructorReviewController reviewController;
	
    @Mock
    private InstructorReviewService reviewService;
 
    @Before
    public void setUp() {
        
    	MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();
    }
    
    @Test
    public void testGetInstructorReviewedByUser() throws Exception {
    	
    	List<Integer> iIds = new ArrayList<Integer>();
        iIds.add(1);
        iIds.add(2);
        
        when(reviewService.instructorReviewedByUser("user@uncc.edu")).thenReturn(iIds);
 
        mockMvc.perform(get("/instructorreviewservice/getinstructorreviewed/{id}", "user@uncc.edu"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
 		
        verify(reviewService, times(1)).instructorReviewedByUser("user@uncc.edu");
        verifyNoMoreInteractions(reviewService);
    }
    
    @Test
    public void testGetCourseReview() throws Exception {
    	
    	InstructorReview ir = new InstructorReview();
    	ir.setInstructorId(1);
    	ir.setUserEmailId("user@uncc.edu");
    	ir.setReview("Good instructor");
    	
    	List<InstructorReview> list = new ArrayList<InstructorReview>();
    	list.add(ir);
    	
    	when(reviewService.getInstructorReviews(1)).thenReturn(list);
    	 
        mockMvc.perform(get("/instructorreviewservice/getreview/{instructorId}", 1))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].instructorId", is(1)))
        .andExpect(jsonPath("$[0].userEmailId", is("user@uncc.edu")))
        .andExpect(jsonPath("$[0].review", is("Good instructor")));
    	
        verify(reviewService, times(1)).getInstructorReviews(1);
        verifyNoMoreInteractions(reviewService);
    }
    
    @Test
    public void testInsertRating() throws Exception {
    	
    	InstructorReview ir = new InstructorReview();
    	ir.setInstructorId(1);
    	ir.setUserEmailId("user@uncc.edu");
    	ir.setReview("Good instructor");
    	
    	doNothing().when(reviewService);
    	
    	mockMvc.perform(post("/instructorreviewservice/insertrating").content(mapToJson(ir)))
    	.andExpect(status().isCreated());
    	
        verify(reviewService, times(1)).insertInstructorReview(ir);
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