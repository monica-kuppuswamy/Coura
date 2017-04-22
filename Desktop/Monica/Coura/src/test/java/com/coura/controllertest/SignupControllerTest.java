package com.coura.controllertest;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.coura.app.SignupController;
import com.coura.model.Users;
import com.coura.service.UsersService;

public class SignupControllerTest extends AbstractControllerTest {
	
	@Mock
	private UsersService userService;
	
	@InjectMocks
	private SignupController signupController;
	
	private List<Users> getUserStubData() {
		List<Users> users = new ArrayList<Users>();
		Users u1 = new Users("mpadmana@uncc.edu", "Monica", "kuppuswamy", "1234567");
		users.add(u1);
		return users;
	}
	
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setUp(signupController);
    }
    
    @Test
    public void getUsersTest() throws Exception {
        List<Users> users = getUserStubData();
        when(userService.listAllUsers()).thenReturn((List<Users>) users);
        String uri = "/signupservices/getusers";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
    }
}