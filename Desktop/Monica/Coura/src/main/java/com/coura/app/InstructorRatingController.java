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

import com.coura.model.InstructorRating;
import com.coura.service.InstructorRatingService;;

@Controller
@ControllerAdvice
@RequestMapping("/instructorratingservice")
public class InstructorRatingController {
	private InstructorRatingService instructorRatingService;

	@Autowired(required = true)
	@Qualifier(value = "instructorRatingService")
	public void setInstructorRatingService(InstructorRatingService instructorRatingService) {
		this.instructorRatingService = instructorRatingService;
	}

	@RequestMapping(
			value = "/getinstructorrated/{id}", 
			method = RequestMethod.GET)
	public @ResponseBody List<Integer> getInstructorRated(@PathVariable String id) {
		List<Integer> ir = this.instructorRatingService.instructorRatedByUser(id);
		return ir;
	}
	
	@RequestMapping(
			value = "/insertrating", 
			method = RequestMethod.POST)
	public @ResponseBody void insertRating(@RequestBody InstructorRating ir) {
		this.instructorRatingService.insertInstructorRating(ir);
	}
	
	@RequestMapping(
			value = "/getrating/{instructorId}", 
			method = RequestMethod.GET,
			produces =  MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<InstructorRating>> getRating(@PathVariable Integer instructorId) {
		List<InstructorRating> ir = this.instructorRatingService.getInstructorRating(instructorId);
		return new ResponseEntity<List<InstructorRating>>(ir, HttpStatus.OK);
	}

}
