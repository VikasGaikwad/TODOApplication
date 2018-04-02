/**
 * 
 */
package com.bridgelab.todo.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelab.todo.user.model.User;
import com.bridgelab.todo.user.service.IUserService;
import com.bridgelab.todo.user.util.Validator;

/**
 * <p>
 * This is a Rest Controller for User With
 * {@link RestController @RestController}, we have added all general purpose
 * methods here those method will accept a rest request in JSON form and will
 * return a JSON response.
 * </p>
 * <p>
 * The methods are self explanatory we have used <b>{@code @RestController}</b>
 * annotation to point incoming requests to this class, and
 * <b>{@link ResponseBody @ResponseBody}</b> annotation to point incoming
 * requests to appropriate Methods. <b>{@link RequestBody @RequestBody}</b>
 * annotation is used to accept data with request in JSON form and Spring.
 * <b>{@link RequestMapping @RequestMapping}</b> annotation is used to bind
 * request parameter with the appropriate method,default method=GET.
 * <b>{@link Autowired @Autowired}</b> annotation is used to autowire the bean
 * by matching data type.ResponseEntity is used to return JSON as response to
 * incoming request.
 * </p>
 * <b>{@link RequestBody @RequestBody}</b> annotation is used to maps the
 * HttpRequest body to a transfer or domain object, enabling automatic
 * deserialization of the inbound HttpRequest body onto a Java object.
 * 
 * @version 1
 * @since 2018-03-20
 * @author Bridgelabz
 * @author Vikas Gaikwad
 **/

@RestController
//@RequestMapping(value = "users")
public class UserController {
	@Autowired
	IUserService userService;

	
	/**
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user, HttpServletRequest request) {

		if (Validator.validate(user.getUsername()) == true && Validator.validateEmail(user.getEmail()) == true
				&& Validator.validatePassword(user.getPassword()) == true) {

			try {
				String emailVerificationUrl = request.getRequestURL().toString().substring(0,
						request.getRequestURL().lastIndexOf("/"));

				userService.registerUser(user, emailVerificationUrl);

				return new ResponseEntity<String>("registered successfully", HttpStatus.OK);

			} catch (Exception e) {
				return new ResponseEntity<String>("email,password,name--convention error", HttpStatus.CONFLICT);

			}

		}

		return new ResponseEntity<String>("something went wrong", HttpStatus.BAD_REQUEST);
	}
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> userLog(@RequestBody User user, HttpServletRequest request) {
		try {
			userService.loginUser(user);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}

	}
	
	

	@RequestMapping(value = "/activateaccount/{randomUUID}", method = RequestMethod.GET)
	public ResponseEntity<Void> activateAccount(@PathVariable("randomUUID") String randomUUID,
			HttpServletRequest request, HttpServletResponse response) {
		userService.activateAccount(randomUUID, request);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	

	@RequestMapping(value = "/getuser/{userId}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserById(@PathVariable("userId") long userId) {
		User user = userService.getUserById(userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}	
	
	@RequestMapping(value = "/getuserbyemail", method = RequestMethod.POST)
	public User getUserByEmail(@RequestBody User user) {
		User user1 = userService.getUserByEmail(user.getEmail());
		return user1;
	}
	
	@RequestMapping(value="/forgotpassword", method=RequestMethod.POST)
	public ResponseEntity<String> forgotPassword(@RequestBody User user,HttpServletRequest request){
		String forgotPasswordURL=request.getRequestURL().toString().substring(0,request.getRequestURL().lastIndexOf("/"));
		userService.forgotPassword(user, forgotPasswordURL);
		return new ResponseEntity<String>("link sent successfully",HttpStatus.OK);
	}

	@RequestMapping(value = "/resetPassword/{randomUUID}", method = RequestMethod.POST)
	public ResponseEntity<String> resetPassword(@RequestBody User user, @PathVariable("randomUUID") String randomUUID) {
		String email = userService.getUserEmailId(randomUUID);
		user.setEmail(email);
		userService.resetPassword(user);
		return new ResponseEntity<String>("password reset successfully...", HttpStatus.OK);

	}

}
