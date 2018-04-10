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
		// System.out.println(randomUUID);
		user.setRandomUUID(randomUUID);
		int id = userDao.registerUser(user);
		// System.out.println("record number " + id);
		if (id > 0) {
			String to = user.getEmail();
			String from = "vikas343430@gmail.com";
			String message = emailVerificationUrl + "/activateaccount/" + randomUUID;
			String subject = "succssfully reistered, click on link to activate account";

			mailService.sendMail(to, from, message, subject);
		}

		/*
		 * if(count>0) { String to=user.getEmail();
		 * System.out.println(" mail id in UserServiceImpl----------"+to); String
		 * from=user.getEmail(); Mail.sendMail(to, from); }
		 */
	}

	@Override
	public String loginUser(User user) {
		// User user3 = null;
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
			String to = user.getEmail();
			String from = "vikas343430@gmail.com";
			String subject = "Link to reset password";
			String message = forgotPasswordUrl + "/resetPassword/" + randomUUID;
			mailService.sendMail(to, from, message, subject);
		}
	}

	@Transactional
	public User getObjByUUID(String randomUUID) {
		System.out.println("inside userserviceimpl" + randomUUID);
		return userDao.getObjByUUID(randomUUID);

	}

	@Override
	public boolean resetPassword(User userobj, User user) {

		System.out.println(userobj.getEmail());
		System.out.println(userobj.getPassword());
		System.out.println(userobj.getRandomUUID());

		boolean status = userDao.resetPassword(userobj.getRandomUUID(), user.getPassword());
		return status;
	}
	/*
	 * Propagation- The transaction isolation level. Support a current transaction,
	 * create a new one if none exists. REQUIRED - Support a current transaction,
	 * execute non-transactionally if none exists. As a consequence, the same
	 * resources (Hibernate Session) will be shared for the entire specified scope.
	 */

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void activateAccount(String randomUUID, HttpServletRequest request) {
		User user = userDao.getUserByRandomId(randomUUID);
		user.setStatus(true);
		User user2 = userDao.updateRecord(user);

	}

}
