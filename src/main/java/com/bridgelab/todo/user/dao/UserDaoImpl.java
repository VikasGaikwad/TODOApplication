/**
 * 
 */
package com.bridgelab.todo.user.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelab.todo.user.model.User;

/**
 * @author bridgeit
 *
 */
public class UserDaoImpl implements IUserDao {
	Session session;

	@Autowired
	private SessionFactory sessionFactory;

	public int registerUser(User user) {

		try {
			session = sessionFactory.openSession();
			long id = (long) session.save(user);

			return (int) id;
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}

	}
	@Transactional
	@Override
	public User loginUser(User user1) {

		session = sessionFactory.openSession();


		try {
			Criteria criteria = session.createCriteria(User.class);
			Criterion email = Restrictions.eq("email", user1.getEmail());
			Criterion password = Restrictions.eq("password", user1.getPassword());
			Criterion criterian = Restrictions.and(email, password);
			criteria.add(criterian);
			user1 = (User) criteria.uniqueResult();
			System.out.println("login success with ");
			System.out.println("user email - " + user1.getEmail() + "  \n" + "user password -" + user1.getPassword());

			return user1;
		} catch (Exception e) {
			System.out.println("login failed with ");
			System.out.println("user email - " + user1.getEmail() + "  \n " + "user password -" + user1.getPassword());
			System.out.println("try with correct credentials..");
			throw e;
		} finally {
			session.close();
		}

	}
	@Transactional

	@Override
	public User getUserById(long userId) {

		return (User) sessionFactory.getCurrentSession().get(User.class, userId);
	}

	@Transactional
	@Override
	public User getUserByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		User user = (User) criteria.uniqueResult();
		return user;
	}

	@Override
	public User sendingMail(User user) {

		return (User) sessionFactory.getCurrentSession().get(User.class, user.getEmail());
	}
	@Transactional
	@Override
	public String getUserEmailId(String randomUUID) {
		System.out.println("id "+randomUUID);

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("randomUUID", randomUUID));
		User user = (User) criteria.uniqueResult();
		return randomUUID;

		

		
	}
	@Transactional

	@Override
	public boolean resetPassword(String randomUUID, String password) {
		User user = null;
		//session.update(user);
		sessionFactory.openSession();
		Query query=session.createQuery("update User set password=:password where randomUUID=:randomUUID");
		query.setParameter("password", user.getPassword());
		query.setParameter("randomUUID", user.getRandomUUID());
		query.executeUpdate();
		System.out.println("Record updated...");
		return true;
	}
	@Transactional
	public User getUserByRandomId(String randomUUID) {

		System.out.println("id " + randomUUID);

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("randomUUID", randomUUID));
		User user = (User) criteria.uniqueResult();
		System.out.println("email id "+user.getEmail());
		return user;
	}
	@Transactional
	@Override
	public User updateRecord(User user) {

		Session session = sessionFactory.getCurrentSession();
		session.update(user);
		System.out.println("Record updated...");
		return user;

	}
}
