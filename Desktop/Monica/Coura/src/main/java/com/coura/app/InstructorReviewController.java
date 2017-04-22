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

import com.coura.model.InstructorReview;
import com.coura.service.InstructorReviewService;

@Controller
@ControllerAdvice
@RequestMapping("/instructorreviewservice")
public class InstructorReviewController {
	private InstructorReviewService instructorReviewService;

	@Autowired(required = true)
	@Qualifier(value = "instructorReviewService")
	public void setInstructorReviewService(InstructorReviewService instructorReviewService) {
		this.instructorReviewService = instructorReviewService;
	}
	
	@RequestMapping(
			value = "/getinstructorreviewed/{id}", 
			method = RequestMethod.GET)
	public @ResponseBody List<Integer> getInstructorReviewed(@PathVariable String id) {
		List<Integer> ir = this.instructorReviewService.instructorReviewedByUser(id);
		return ir;
	}
	
	@RequestMapping(
			value = "/insertreview", 
			method = RequestMethod.POST)
	public @ResponseBody void insertReview(@RequestBody InstructorReview ir) {
		this.instructorReviewService.insertInstructorReview(ir);
	}
	
	@RequestMapping(
			value = "/getreview/{instructorId}", 
			method = RequestMethod.GET,
			produces =  MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<InstructorReview>> getRating(@PathVariable Integer instructorId) {
		List<InstructorReview> ir = this.instructorReviewService.getInstructorReviews(instructorId);
		return new ResponseEntity<List<InstructorReview>>(ir, HttpStatus.OK);
	}
}
