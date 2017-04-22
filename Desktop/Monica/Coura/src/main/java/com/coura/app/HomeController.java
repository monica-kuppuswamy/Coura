package com.coura.app;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("message", "Coura");
		return "home";
	}
	
	/**
	 * Simply selects the signup view to render by returning its name.
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupPage(Model model) {
		model.addAttribute("message", "Sign Up");
		return "SignupPage";
	}
	
	/**
	 * Simply selects the Login view to render by returning its name.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model, HttpSession httpSession) {
		String sessionUser = (String) httpSession.getAttribute("userId");
		if(sessionUser != null && sessionUser.contains("admin")) {
			model.addAttribute("message", "Manage Users");
			return "AdminManageUsers";
		} else if (sessionUser != null && !(sessionUser.contains("admin"))) {
			model.addAttribute("message", "Student Home");
			return "StudentHomePage";
		} else {
			model.addAttribute("message", "Log In");
			return "LoginPage";	
		}
	}
	
	/**
	 * Simply renders the Admin Homepage when the administrator logs in the system
	 */
	@RequestMapping(value = "/adminmanageinstructors", method = RequestMethod.GET)
	public String adminHome(Model model, HttpSession httpSession) {
		
		if (httpSession.getAttribute("userId") != null) {
			String user = httpSession.getAttribute("userId").toString();
			if(user.equals("admin@uncc.edu")) {
				model.addAttribute("message", "Manage Instructors");
				return "AdminManageInstructors";
			} else {
				model.addAttribute("message", "Log In");
				return "LoginPage";
			}
		} else {
			model.addAttribute("message", "Log In");
			return "LoginPage";
		}
	}
	
	/**
	 * Simply renders the Admin Manage courses view when the administrator logs in the system
	 */
	@RequestMapping(value = "/adminmanagecourses", method = RequestMethod.GET)
	public String manageCourses(Model model, HttpSession httpSession) {
		
		if (httpSession.getAttribute("userId") != null) {
			String user = httpSession.getAttribute("userId").toString();
			if (user.equals("admin@uncc.edu")) {
				model.addAttribute("message", "Manage Courses");
				return "AdminManageCourses";
			} else {
				model.addAttribute("message", "Log In");
				return "LoginPage";
			}		
		} else {
			model.addAttribute("message", "Log In");
			return "LoginPage";
		}
	}
	
	@RequestMapping(value = "/adminmanageusers", method = RequestMethod.GET)
	public String manageUsers(Model model, HttpSession httpSession) {
		if (httpSession.getAttribute("userId") != null) {
			String user = httpSession.getAttribute("userId").toString();
			if (user.equals("admin@uncc.edu")) {
				model.addAttribute("message", "Manage Courses");
				return "AdminManageUsers";
			} else {
				model.addAttribute("message", "Log In");
				return "LoginPage";
			}		
		} else {
			model.addAttribute("message", "Log In");
			return "LoginPage";
		}
	}
	
	@RequestMapping(value = "/studenthome", method = RequestMethod.GET)
	public String studentHome(Model model, HttpSession httpSession) {
		if (httpSession.getAttribute("userId") != null) {
			String user = httpSession.getAttribute("userId").toString();
			if(!(user.equals("admin@uncc.edu"))) {
				model.addAttribute("message", "Student Home");
				return "StudentHomePage";
			} else {
				model.addAttribute("message", "Log In");
				return "LoginPage";
			}
		} else {
			model.addAttribute("message", "Log In");
			return "LoginPage";
		}
	}
	
	@RequestMapping(value = "/studentviewinstructors", method = RequestMethod.GET)
	public String studentViewInstructors(Model model, HttpSession httpSession) {
		if (httpSession.getAttribute("userId") != null) {
			String user = httpSession.getAttribute("userId").toString();												   
			if(!(user.equals("admin@uncc.edu"))) {							 
			model.addAttribute("message", "Student View Instructors");
			return "StudentViewInstructors";
			} else {
				model.addAttribute("message", "Log In");
				return "LoginPage";
			}
		} else {
			model.addAttribute("message", "Log In");
			return "LoginPage";
		}
	}
	
	@RequestMapping(value = "/coursedetails", method = RequestMethod.GET)
	public String courseDetailsPage(Model model, HttpSession httpSession) {
		if (httpSession.getAttribute("userId") != null) {
			String user = httpSession.getAttribute("userId").toString();
			if(!(user.equals("admin@uncc.edu"))) {
				model.addAttribute("message", "Course Details");
				return "CourseDetails";
			} else {
				model.addAttribute("message", "Log In");
				return "LoginPage";
			}
		} else {
			model.addAttribute("message", "Log In");
			return "LoginPage";
		}
	}
	
	@RequestMapping(value = "/mycourses", method = RequestMethod.GET)
	public String myCourses(Model model, HttpSession httpSession) {
		if (httpSession.getAttribute("userId") != null) {
			String user = httpSession.getAttribute("userId").toString();
			if(!(user.equals("admin@uncc.edu"))) {
				model.addAttribute("message", "Student Home");
				return "MyCourses";
			} else {
				model.addAttribute("message", "Log In");
				return "LoginPage";
			}
		} else {
			model.addAttribute("message", "Log In");
			return "LoginPage";
		}
	}
	
	@RequestMapping(value = "/instructordetails", method = RequestMethod.GET)
	public String instructorDetailsPage(Model model, HttpSession httpSession) {
															  
		if (httpSession.getAttribute("userId") != null) {
			String user = httpSession.getAttribute("userId").toString();												   
			if(!(user.equals("admin@uncc.edu"))) {							 
			model.addAttribute("message", "Instructor Details");								
			return "InstructorDetails";
			} else {
				model.addAttribute("message", "Log In");
				return "LoginPage";
			}
		} else {
			model.addAttribute("message", "Log In");
			return "LoginPage";
		}
	}
}
