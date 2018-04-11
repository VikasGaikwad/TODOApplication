/**
 * 
 */
package com.bridgelab.todo.user.service;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelab.todo.user.dao.IUserDao;
import com.bridgelab.todo.user.model.User;
import com.bridgelab.todo.user.util.JWT_Tokens;
import com.bridgelab.todo.user.util.Mail;
import com.bridgelab.todo.user.util.Validator;

/**
 * <b>{@link PasswordEncoder }</b> this Interface is Service interface for
 * encoding passwords. The preferred implementation is BCryptPasswordEncoder.
 * 
 * @author bridgeit
 *
 */
public class UserServiceImpl implements IUserService {
	@Autowired
	IUserDao userDao;

	@Autowired
	Validator validateBean;
	@Autowired
	Mail mailService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	/*
	 * <b>{@link Transactional @Transactional}</b> Transactional annotation provides
	 * the application the ability to declaratively control transaction boundaries.
	 */
	@Transactional
	public void registerUser(User user, String emailVerificationUrl) {

		String hashCode = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashCode);
		String randomUUID = UUID.randomUUID().toString();
		user.setRandomUUID(randomUUID);
		int id = userDao.registerUser(user);
		int token_id=(int) user.getUserId();
		System.out.println("registered user id----"+user.getUserId());
		String token=JWT_Tokens.createToken(token_id);
		System.out.println("registration token : "+token);
		if (id > 0) {			
			String to = user.getEmail();
			String message = emailVerificationUrl + "/activateaccount/" + token;
			mailService.sendMail(to,message);
		}


	}

	@Override
	public String loginUser(User user) {
		String token = null;
		User user3 = userDao.loginUser(user);
		System.out.println("userId :- " + user3.getUserId());
		if (user3 != null && user3.isStatus() == true) {
			int id = (int) user3.getUserId();
			token = JWT_Tokens.createToken(id);
			System.out.println("generated token : - " + token);
		}
		return token;
	}

	@Transactional
	public User getUserById(long userId) {

		return userDao.getUserById(userId);
	}

	@Override
	public User sendingMail(User user) {

		return userDao.sendingMail(user);
	}

	@Transactional
	public User getUserByEmail(String email) {

		return userDao.getUserByEmail(email);
	}
	@Transactional
	@Override
	public void forgotPassword(User user, String forgotPasswordUrl) {

		user = userDao.getUserByEmail(user.getEmail());

		if (user != null) {

			String randomUUID = user.getRandomUUID();
			int id = (int) user.getUserId();
			String token = JWT_Tokens.createToken(id);
			System.out.println("generated token for forgot password : - " + token);
			String to = user.getEmail();
			String message = forgotPasswordUrl + "/userapi/resetPassword/" + token;
			mailService.sendMail(to,message);
		}
	}

	@Transactional
	public User getObjByUUID(String randomUUID) {
		System.out.println("inside userserviceimpl" + randomUUID);
		return userDao.getObjByUUID(randomUUID);

	}
@Transactional
	@Override
	public String resetPassword(String token, HttpServletRequest request,String newPassword) {
		System.out.println("inside activate account token"+token);
		int id=JWT_Tokens.verifyToken(token);
		System.out.println("id+++++++++++"+id);
		User user = userDao.getUserById(id);
		user.setPassword(newPassword);
		System.out.println("inside activateAccount====\n"+user.getEmail()+"\npassword===="+user.getPassword());
		User user2 = userDao.updateRecord(user);
		return null;
				
		
	}
	
	/*
	 * Propagation- The transaction isolation level. Support a current transaction,
	 * create a new one if none exists. REQUIRED - Support a current transaction,
	 * execute non-transactionally if none exists. As a consequence, the same
	 * resources (Hibernate Session) will be shared for the entire specified scope.
	 */

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public String activateAccount(String token, HttpServletRequest request) {
		System.out.println("inside activate account token"+token);
		int id=JWT_Tokens.verifyToken(token);
		System.out.println("id+++++++++++"+id);
		User user = userDao.getUserById(id);
		System.out.println("inside activateAccount====\n"+user.getEmail()+"\npassword===="+user.getPassword());
		user.setStatus(true);
		@SuppressWarnings("unused")
		User user2 = userDao.updateRecord(user);
		return null;

	}

}
