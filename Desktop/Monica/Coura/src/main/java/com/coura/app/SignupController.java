package com.coura.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coura.model.Users;
import com.coura.service.UsersService;

@Controller
@ControllerAdvice
@RequestMapping("/signupservices")
public class SignupController {
	
	private UsersService userService;
	
	@Autowired(required = true)
	@Qualifier(value = "usersService")
	public void setUserService(UsersService userService) {
		this.userService = userService;
	}
	
	// Post request add users to the database
	@RequestMapping(
			value = "/saveuser", 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String saveUser(@RequestBody Users user){
		boolean retVal = this.userService.saveUsers(user);
		String message = null;
		if (retVal) { 
			message = "User with Email ID " + user.getEmailId() + " is successfully registered";
		} else {
			message = "User with Email ID " + user.getEmailId() + " is already registered";
		}
		return message;
	}
	
	@RequestMapping(
			value = "/getusers", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Users>> listUsers() {
		List<Users> users = this.userService.listAllUsers();
		return new ResponseEntity<List<Users>>(users, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/deleteuser/{emailId}",
			method = RequestMethod.DELETE
			)
	public @ResponseBody void deleteUser(@PathVariable("emailId") String emailId) {
		this.userService.deleteUser(emailId);
	}
	
}