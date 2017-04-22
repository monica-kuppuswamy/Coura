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

import com.coura.model.Course;
import com.coura.model.StudentCourse;
import com.coura.service.RegistrationService;

@Controller
@ControllerAdvice
@RequestMapping("/enrollservice")
public class RegistrationController {

	private RegistrationService registrationService;

	@Autowired(required = true)
	@Qualifier(value = "registrationService")
	public void setRegistrationService(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}
	
	@RequestMapping(
			value = "/savedetails", 
			method = RequestMethod.POST)
	public @ResponseBody void insertRegistrationDetails(@RequestBody StudentCourse sc) {
		this.registrationService.insertRegistrationDetails(sc);
	}
	
	@RequestMapping(
			value = "/getenrolledcourses/{id}", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Course>> getEnrolledCourses(@PathVariable String id) {
		List<Course> course = this.registrationService.getEnrolledCourses(id);
		return new ResponseEntity<List<Course>>(course, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/unenrollcourse/{emailId}/{courseId}",
			method = RequestMethod.DELETE
			)
	public @ResponseBody void unEnrollCourse(@PathVariable("emailId") String emailId, @PathVariable("courseId") Integer courseId) {
		this.registrationService.unEnrollCourse(emailId, courseId);
	}
}