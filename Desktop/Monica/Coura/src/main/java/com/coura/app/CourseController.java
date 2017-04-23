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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coura.model.Course;
import com.coura.model.CourseInstructorWrapper;
import com.coura.model.Instructor;
import com.coura.model.Users;
import com.coura.service.CourseService;

@Controller
@ControllerAdvice
@RequestMapping("/courseservice")
public class CourseController {
	
	private CourseService courseService;

	@Autowired(required = true)
	@Qualifier(value = "courseService")
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	@RequestMapping(
			value = "/savecourse", 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.TEXT_PLAIN_VALUE
			)
	public @ResponseBody String saveCourse(@RequestBody Course course) {
		Boolean inserted = false;
		inserted = this.courseService.saveCourses(course);
		if (inserted) {
			return "Course added successfully.";
		} else {
			return "Unable to add course.";
		}
	}
	
	@RequestMapping(
			value = "/updatecourse",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.TEXT_PLAIN_VALUE
			)
	public @ResponseBody void updateCourse(@RequestBody Course course) {
		this.courseService.updateCourse(course);
	}
	
	@RequestMapping(
			value = "/getcourses", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Course>> listCourses() {
		List<Course> course = this.courseService.listAllCourses();
		return new ResponseEntity<List<Course>>(course, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/getcourse/{id}", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Course>> getCourseByID(@PathVariable Integer id) {
		List<Course> course = this.courseService.getCourseById(id);
		return new ResponseEntity<List<Course>>(course, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/deletecourse/{courseId}",
			method = RequestMethod.DELETE
			)
	public @ResponseBody void deleteCourse(@PathVariable("courseId") Integer courseId) {
		this.courseService.deleteCourse(courseId);
	}
	
	@RequestMapping(
			value = "/getcourseId/{courseNumber}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public @ResponseBody int getIdForCourse(@PathVariable("courseNumber") String courseNumber) {
		return this.courseService.getIdForCourse(courseNumber);
	}
	
	@RequestMapping(
			value = "/getinstructorforcourse/{id}", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Instructor>> getInstructorForCourse(@PathVariable Integer id) {
		List<Instructor> course = this.courseService.getInstructorsForCourse(id);
		return new ResponseEntity<List<Instructor>>(course, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/searchcourses", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Course>> searchCourses(@RequestParam(value = "courseNumber", required = false) String courseNumber, @RequestParam(value = "courseName", required = false) String courseName, @RequestParam(value = "areaOfInterest", required = false) String areaOfInterest, @RequestParam(value = "instructorName", required = false) String instructorName) {
		List<Course> course = this.courseService.searchCourses(courseNumber, courseName, areaOfInterest, instructorName);
		return new ResponseEntity<List<Course>>(course, HttpStatus.OK);
	}
	
@RequestMapping(
	value="getMostRecentlySearchedCourses",
	method=RequestMethod.GET,
	produces=MediaType.APPLICATION_JSON_VALUE)
public @ResponseBody ResponseEntity<List<Course>> listMostRecentlySearchedCourses(){
List<Course> course=this.courseService.listMostRecentlySearchedCourses();
return new ResponseEntity<List<Course>>(course,HttpStatus.OK);
}
	
}