/**
 * 
 */
package com.bridgelab.todo.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RestController
@RequestMapping(value = "users")
public class UserController {
	@Autowired
	IUserService userService;

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user,HttpServletRequest request) {

		if (Validator.validate(user.getUsername()) == true && Validator.validateEmail(user.getEmail()) == true
				&& Validator.validatePassword(user.getPassword()) == true) {

			try {
				String url = request.getRequestURL().toString().substring(0, request.getRequestURL().lastIndexOf("/"));

				userService.registerUser(user, url);

				return new ResponseEntity<String>("registered successfully", HttpStatus.OK);

			} catch (Exception e) {
				return new ResponseEntity<String>("email,password,name--convention error",HttpStatus.CONFLICT);

			}

		}
		
		return new ResponseEntity<String>("something went wrong",HttpStatus.BAD_REQUEST);
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
	@RequestMapping(value="/getbyemail/{email}",method=RequestMethod.GET)
	public ResponseEntity<User> getemail(@PathVariable("email") String email){
		User user=userService.getUserByEmail(email);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}

}
