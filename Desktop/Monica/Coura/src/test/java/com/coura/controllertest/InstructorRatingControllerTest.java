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

import com.coura.app.InstructorRatingController;
import com.coura.model.InstructorRating;
import com.coura.service.InstructorRatingService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
 
@ContextConfiguration(locations = "classpath:Coura-servlet-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class InstructorRatingControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	@InjectMocks
	private InstructorRatingController ratingController;
	
    @Mock
    private InstructorRatingService ratingService;
 
    @Before
    public void setUp() {
        
    	MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(ratingController).build();
    }
    
    @Test
    public void testGetInstructorRatedByUser() throws Exception {
    	
    	List<Integer> iIds = new ArrayList<Integer>();
        iIds.add(1);
        iIds.add(2);
        
        when(ratingService.instructorRatedByUser("user@uncc.edu")).thenReturn(iIds);
 
        mockMvc.perform(get("/instructorratingservice/getinstructorrated/{id}", "user@uncc.edu"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
 		
        verify(ratingService, times(1)).instructorRatedByUser("user@uncc.edu");
        verifyNoMoreInteractions(ratingService);
    }
    
    @Test
    public void testGetInstructorRating() throws Exception {
    	
    	InstructorRating ir = new InstructorRating();
    	ir.setInstructorId(1);
    	ir.setCourseId(1);
    	ir.setUserEmailId("user@uncc.edu");
    	ir.setQualityOfTeachingRating(2);
    	ir.setGradingStyleRating(3);
    	ir.setLeniencyRating(3);
    	
    	List<InstructorRating> list = new ArrayList<InstructorRating>();
    	list.add(ir);
    	
    	when(ratingService.getInstructorRating(1)).thenReturn(list);
    	 
        mockMvc.perform(get("/instructorratingservice/getrating/{instructorId}", 1))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].instructorId", is(1)))
        .andExpect(jsonPath("$[0].courseId", is(1)))
        .andExpect(jsonPath("$[0].userEmailId", is("user@uncc.edu")))
        .andExpect(jsonPath("$[0].qualityOfTeachingRating", is(2)))
        .andExpect(jsonPath("$[0].gradingStyleRating", is(3)))
        .andExpect(jsonPath("$[0].leniencyRating", is(3)));
    	
        verify(ratingService, times(1)).getInstructorRating(1);
        verifyNoMoreInteractions(ratingService);
    }
    
    @Test
    public void testInsertRating() throws Exception {
    	
    	InstructorRating ir = new InstructorRating();
    	ir.setInstructorId(1);
    	ir.setCourseId(1);
    	ir.setUserEmailId("user@uncc.edu");
    	ir.setQualityOfTeachingRating(2);
    	ir.setGradingStyleRating(3);
    	ir.setLeniencyRating(3);
    	
    	doNothing().when(ratingService);
    	
    	mockMvc.perform(post("/instructorratingservice/insertrating").content(mapToJson(ir)))
    	.andExpect(status().isCreated());
    	
        verify(ratingService, times(1)).insertInstructorRating(ir);
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
