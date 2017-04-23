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
import com.coura.model.CourseInstructorWrapper;
import com.coura.model.Instructor;
import com.coura.service.InstructorService;

@Controller
@ControllerAdvice
@RequestMapping("/instructorservice")
public class InstructorController {
	
	private InstructorService instructorService;

	@Autowired(required = true)
	@Qualifier(value = "instructorService")
	public void setCourseService(InstructorService instructorService) {
		this.instructorService = instructorService;
	}
	
	@RequestMapping(
			value = "/addinstructor", 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.TEXT_PLAIN_VALUE
			)
	public @ResponseBody String saveCourse(@RequestBody CourseInstructorWrapper wrapper) {
		Boolean inserted = false;
		inserted = this.instructorService.addInstructor(wrapper);
		if (inserted) {
			return "Instructor Added Successfully";
		} else {
			return "Unable to add Instructor";
		}
	}
	
	@RequestMapping(
			value = "/updateinstructor",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.TEXT_PLAIN_VALUE
			)
	public @ResponseBody String updateInstructor(@RequestBody Instructor instructor) {
		boolean retVal = this.instructorService.updateInstructor(instructor);
		String message = null;
		if (retVal) { 
			message = "Instructor with email ID " + instructor.getEmailId() + " is successfully updated.";
		} else {
			message = "Instructor with email ID " + instructor.getEmailId() + " already exists.";
		}
		return message;
	}
	
	@RequestMapping(
			value = "/deleteinstructor/{instructorId}",
			method = RequestMethod.DELETE
			)
	public @ResponseBody void deleteCourse(@PathVariable("instructorId") Integer instructorId) {
		this.instructorService.deleteInstructor(instructorId);
	}
	
	@RequestMapping(
			value = "/getinstructors", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Instructor>> listInstructors() {
		List<Instructor> instructor = this.instructorService.listAllInstructors();
		return new ResponseEntity<List<Instructor>>(instructor, HttpStatus.OK);																 
	}
	
	@RequestMapping(
			value = "/getinstructor/{id}", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Instructor>> getInstructorByID(@PathVariable Integer id) {
		List<Instructor> instructor = this.instructorService.getInstructorById(id);
		return new ResponseEntity<List<Instructor>>(instructor, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/getinstructorId/{emailId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public @ResponseBody int getIdForInstructor(@PathVariable("emailId") String emailId) {
		return this.instructorService.getIdForInstructor(emailId);
	}
	
	@RequestMapping(
			value = "/getcourseforinstructor/{id}", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Course>> getCourseForInstructor(@PathVariable Integer id) {
		List<Course> course = this.instructorService.getCourseForInstructor(id);
		return new ResponseEntity<List<Course>>(course, HttpStatus.OK);
	}
}