package com.coura.controllertest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.coura.app.SignupController;
import com.coura.model.Users;
import com.coura.service.UsersService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SigninControllerTest {
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	private SignupController signupController;
	
	private Users user;
	
	@Before
	public void setup() throws Exception {
		this.user = new Users("mpadmana@uncc.edu", "Monica", "kuppuswamy", "1234567");
		List<Users> usersList = new ArrayList<Users>();
		usersList.add(user);
		Mockito.when(this.userService.listAllUsers()).thenReturn(usersList);
	}
	
	@Test
	public void listAllUsersTest() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.signupController).build();
		String uri = "/signupservices/getusers";
		mockMvc.perform(MockMvcRequestBuilders.get(uri))
				.andExpect(status().isOk());
	}
	
    @Configuration
    static class SignupControllerTestConfiguration {

        @Bean
        public UsersService userService() {
            return Mockito.mock(UsersService.class);
        }

        @Bean
        public SignupController signupController() {
            return new SignupController();
        }
    }
}