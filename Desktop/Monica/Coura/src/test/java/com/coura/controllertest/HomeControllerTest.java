package com.coura.controllertest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.coura.app.HomeController;
 
@ContextConfiguration(locations = "classpath:Coura-servlet-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class HomeControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	@InjectMocks
	private HomeController homeController;
	
    @Before
    public void setUp() {
        
    	MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }
    
    @Test
    public void testGetHomePage() throws Exception {
    	mockMvc.perform(get("/home"))
        .andExpect(status().isOk())
        .andExpect(view().name("home"))
        .andExpect(forwardedUrl("/WEB-INF/jsp/home.jsp"));
    }
    
    @Test
    public void testGetSignupPage() throws Exception {
    	mockMvc.perform(get("/signup"))
        .andExpect(status().isOk())
        .andExpect(view().name("SignupPage"))
        .andExpect(forwardedUrl("/WEB-INF/jsp/SignupPage.jsp"));
    }
}
