/**
 * 
 */
package com.bridgelab.todo.user.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
// @RequestMapping(value = "users")
public class UserController {
	@Autowired
	IUserService userService;

	UserService userResponse=new UserService();


	@RequestMapping(value = "register", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user, HttpServletRequest request) {

		if (Validator.validate(user.getUsername()) == true && Validator.validateEmail(user.getEmail()) == true
				&& Validator.validatePassword(user.getPassword()) == true) {

			try {
				String emailVerificationUrl = request.getRequestURL().toString().substring(0,
						request.getRequestURL().lastIndexOf("/"));

				userService.registerUser(user, emailVerificationUrl);

				return new ResponseEntity<String>("registered successfully", HttpStatus.OK);

			} catch (Exception e) {
				return new ResponseEntity<String>("email/password/name--convention error", HttpStatus.CONFLICT);

			}

		}

		return new ResponseEntity<String>("something went wrong", HttpStatus.BAD_REQUEST);
	}
	@RequestMapping(value = "login", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> userLog(@RequestBody User user, HttpServletRequest request,
			HttpServletResponse response) {
		try {

			String token = userService.loginUser(user);
			System.out.println("token : "+token);

			if (token != null) {
				response.setHeader("Authorization", token);

				System.out.println("token in user controller... :"+ token);
				userResponse.setStatusCode(200);
				response.setHeader("Authorization", token);
				return new ResponseEntity<UserService>(userResponse , HttpStatus.OK);


			} else {
				userResponse.setStatusCode(409);
				userResponse.setMessage("Login fail");
				return new ResponseEntity<UserService>(userResponse, HttpStatus.CONFLICT);

			}

		} catch (Exception e) {
			System.out.println("exception thrown");
			e.printStackTrace();
			return new ResponseEntity<UserService>(userResponse, HttpStatus.CONFLICT);

		}

	}
	@RequestMapping(value = "activateaccount/{token:.+}", method = RequestMethod.GET)
	public ResponseEntity<?> activateAccount(@PathVariable("token") String token,
			HttpServletRequest request, HttpServletResponse response) {

		System.out.println("inside controller of activate------"+token);
		userService.activateAccount(token, request);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	/*@RequestMapping(value = "getuser/{userId}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserById(@PathVariable("userId") long userId) {

		User user = userService.getUserById(userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}*/

	@RequestMapping(value = "user/getuser", method = RequestMethod.GET)
	public ResponseEntity<User> getUserById(HttpServletRequest request) {
		int userId=(int) request.getAttribute("userId");
		User user = userService.getUserById(userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	@RequestMapping(value = "user/getuserbyemail/{email}", method = RequestMethod.POST)
	public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email,HttpServletRequest request) { 
		int userId=(int) request.getAttribute("userId");
		User user = userService.getUserByEmail(email);

		return new ResponseEntity<User>(HttpStatus.OK);

	}


	@RequestMapping(value = "forgotpassword", method = RequestMethod.POST)
	public ResponseEntity<String> forgotPassword(@RequestBody User user, HttpServletRequest request) {
		String forgotPasswordURL = request.getRequestURL().toString().substring(0,
				request.getRequestURL().lastIndexOf("/"));
		userService.forgotPassword(user, forgotPasswordURL);
		return new ResponseEntity<String>("link sent successfully", HttpStatus.OK);
	}

	@RequestMapping(value = "user/resetPassword/{token:.+}", method = RequestMethod.POST)
	public ResponseEntity<?> resetPassword(@RequestBody User user, @PathVariable("token") String token, HttpServletRequest request) {

		//User userobj = userService.getObjByUUID(token);
		// userobj.setEmail(userobj.getEmail());
		String newPassword=user.getPassword();
		userService.resetPassword(token, request,newPassword);
		return new ResponseEntity<String>("password reset successfully...", HttpStatus.OK);

	}

	@RequestMapping(value = "user/uploaduser",  method = RequestMethod.POST)
	public ResponseEntity<String> handleFileUpload(HttpServletRequest request, @RequestParam("file") MultipartFile fileUpload, @RequestParam int userId)
			throws Exception {
		System.out.println("file name -- "+fileUpload.getOriginalFilename());
		userService.saveImage(fileUpload, userId);


		return new ResponseEntity<String>(HttpStatus.OK);
	}


}
