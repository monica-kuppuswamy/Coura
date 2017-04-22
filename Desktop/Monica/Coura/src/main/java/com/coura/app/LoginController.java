package com.coura.app;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coura.service.LoginService;

@Controller
@ControllerAdvice
@RequestMapping("/loginservice")
public class LoginController {
	
	private LoginService loginService;

	@Autowired(required = true)
	@Qualifier(value = "loginService")
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@RequestMapping(
			value="/authLogin/{emailId}/{password}", 
			method = RequestMethod.GET,
			produces = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String authenticateLogin(@PathVariable("emailId") String emailId, @PathVariable("password") String password,
			HttpSession httpsession) {
		String response;
		boolean userFound = this.loginService.verifyCredentials(emailId, password);
		if (userFound) {
			httpsession.setAttribute("userId", emailId);
			httpsession.setMaxInactiveInterval(10 * 60);
			response = "Valid User";
		} else {
			response = "Not Found";
		}
		return response;
	}
	
	@RequestMapping(
			value = "/logout",
			method = RequestMethod.GET,
			produces = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String logout(HttpSession httpSession) {
		httpSession.invalidate();
		return "Logout Successful";
	}
}
