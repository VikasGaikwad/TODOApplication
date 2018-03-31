/**
 * 
 */
package com.bridgelab.todo.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelab.todo.user.model.User;
import com.bridgelab.todo.user.service.IUserService;
import com.bridgelab.todo.user.util.Validator;

/**
 * @author bridgeit
 *
 */
@Service
@RestController
@RequestMapping(value = "users")
public class UserController {
	@Autowired
	IUserService userService;

	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user, HttpServletRequest request) {

		if (Validator.validate(user.getUsername()) == true && Validator.validateEmail(user.getEmail()) == true
				&& Validator.validatePassword(user.getPassword()) == true) {

			try {
				String emailVerificationUrl = request.getRequestURL().toString().substring(0, request.getRequestURL().lastIndexOf("/"));

				userService.registerUser(user, emailVerificationUrl);

				return new ResponseEntity<String>("registered successfully", HttpStatus.OK);

			} catch (Exception e) {
				return new ResponseEntity<String>("email,password,name--convention error", HttpStatus.CONFLICT);

			}

		}

		return new ResponseEntity<String>("something went wrong", HttpStatus.BAD_REQUEST);
	}

	
	@RequestMapping(value="/activateaccount/{randomUUID}",method=RequestMethod.GET)
	public ResponseEntity<Void> activateAccount(@PathVariable("randomUUID") String randomUUID,HttpServletRequest request,HttpServletResponse response){
		userService.activateAccount(randomUUID,request);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	 
	 
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> userLog(@RequestBody User user, HttpServletRequest request) {
		try {
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}

	}

	@RequestMapping(value = "{userId}/getuser", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("userId") long userId) {

		System.out.println(userId);
		User user = userService.getUserById(userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	@RequestMapping(value = "/getbyemail/{email}", method = RequestMethod.GET)
	public ResponseEntity<User> getemail(@PathVariable("email") String email) {
		User user = userService.getUserByEmail(email);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public ResponseEntity<String> forgotPassword(@RequestBody User user,HttpServletRequest request) {
		String forgotPasswordUrl=request.getRequestURL().toString().substring(0,request.getRequestURL().lastIndexOf("/"));
		userService.forgotPassword(user,forgotPasswordUrl);
		return new ResponseEntity<String>("mail sent successfully", HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/resetPassword/{randomUUID}",method=RequestMethod.POST)
	public ResponseEntity<String>resetPassword(@RequestBody User user,@PathVariable("randomUUID") String randomUUID){
		String email=userService.getUserEmailId(randomUUID);
		user.setEmail(email);
		userService.resetPassword(user);
		return new ResponseEntity<String>("password reset successfully...",HttpStatus.OK);
		
	}

}
