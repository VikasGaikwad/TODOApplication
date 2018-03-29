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
@RequestMapping(value="users")
@RestController
public class UserController {
	@Autowired
	IUserService userService;

	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		String username = user.getUsername();
		String email = user.getEmail();
		String password = user.getPassword();

		if (Validator.validate(username) == true) {

			if (Validator.validateEmail(email) == true) {

				if (Validator.validatePassword(password) == true) {

					try {
						userService.registerUser(user);

						return new ResponseEntity<String>(HttpStatus.OK);
						
					} catch (Exception e) {
						return new ResponseEntity<String>(HttpStatus.CONFLICT);

					}

				}

			}
		}

		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> userLog(@RequestBody User user,HttpServletRequest request) {
		try {
			User user2 =userService.loginUser(user);
			HttpSession userLoginSession=request.getSession();
			userLoginSession.setAttribute("userObjectSession", user2);
			System.out.println("*******UserController*******");
			System.out.println("userId - "+userLoginSession.getId());
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}

	}
	@RequestMapping(value = "getuser/{userId}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("userId") long userId) {

		System.out.println(userId);
		User user = userService.getUserById(userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}
	
}
