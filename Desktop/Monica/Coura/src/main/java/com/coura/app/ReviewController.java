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

import com.coura.model.CourseReview;
import com.coura.service.ReviewService;

@Controller
@ControllerAdvice
@RequestMapping("/reviewservice")
public class ReviewController {
	private ReviewService reviewService;

	@Autowired(required = true)
	@Qualifier(value = "reviewService")
	public void setReviewService(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	@RequestMapping(
			value = "/getcoursereviewed/{id}", 
			method = RequestMethod.GET)
	public @ResponseBody List<Integer> getCourseReviwed(@PathVariable String id) {
		List<Integer> cr = this.reviewService.courseReviewedByUser(id);
		return cr;
	}
	
	@RequestMapping(
			value = "/insertreview", 
			method = RequestMethod.POST)
	public @ResponseBody void insertReview(@RequestBody CourseReview cr) {
		this.reviewService.insertCourseReview(cr);
	}
	
	@RequestMapping(
			value = "/getreview/{courseId}", 
			method = RequestMethod.GET,
			produces =  MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<CourseReview>> getRating(@PathVariable Integer courseId) {
		List<CourseReview> cr = this.reviewService.getCourseReviews(courseId);
		return new ResponseEntity<List<CourseReview>>(cr, HttpStatus.OK);
	}
}
