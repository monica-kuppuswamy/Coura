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
import com.coura.model.CourseRating;
import com.coura.service.RatingService;

@Controller
@ControllerAdvice
@RequestMapping("/ratingservice")
public class RatingController {
	private RatingService ratingService;

	@Autowired(required = true)
	@Qualifier(value = "ratingService")
	public void setRatingService(RatingService ratingService) {
		this.ratingService = ratingService;
	}

	@RequestMapping(
			value = "/getcourserated/{id}", 
			method = RequestMethod.GET)
	public @ResponseBody List<Integer> getCourseByID(@PathVariable String id) {
		List<Integer> cr = this.ratingService.courseRatedByUser(id);
		return cr;
	}
	
	@RequestMapping(
			value = "/insertrating", 
			method = RequestMethod.POST)
	public @ResponseBody void insertRating(@RequestBody CourseRating cr) {
		this.ratingService.insertCourseRating(cr);
	}
	
	@RequestMapping(
			value = "/getrating/{courseId}", 
			method = RequestMethod.GET,
			produces =  MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<CourseRating>> getRating(@PathVariable Integer courseId) {
		List<CourseRating> cr = this.ratingService.getCourseRating(courseId);
		return new ResponseEntity<List<CourseRating>>(cr, HttpStatus.OK);
	}

}
