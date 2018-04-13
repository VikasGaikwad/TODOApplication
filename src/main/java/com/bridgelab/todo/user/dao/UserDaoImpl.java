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
			
		} finally {
			session.close();
		}
		return 0;

	}

	@Override
	public User loginUser(User user) {

		session = sessionFactory.openSession();

//
		try {
			Criteria criteria = session.createCriteria(User.class);
			Criterion email_id = Restrictions.eq("email", user.getEmail());

			Criterion password1 = Restrictions.eq("password", user.getPassword());

			Criterion criterian = Restrictions.and(email_id, password1);
			criteria.add(criterian);
			/*uniqueResult()-
			 * Convenience method to return a single instance that matches the query, or
			 * null if the query returns no results.
			 */
			User userObj = (User) criteria.uniqueResult();
		//	System.out.println("login successfull");

			return userObj;

		} catch (Exception e) {
			System.out.println("login failed");

		} finally {
			session.close();
		}
		return null;

	}

	@Override
	public User getUserById(long userId) {

		return (User) sessionFactory.getCurrentSession().get(User.class, userId);
	}

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

	@Override
	public User getObjByUUID(String randomUUID) {

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("randomUUID", randomUUID));

		User user3 = (User) criteria.uniqueResult();
		System.out.println("userdaoimpl..........." + user3.getRandomUUID());
		return user3;

	}

	@Override
	public boolean resetPassword(String randomUUID, String password) {
		User user = null;
		// session.update(user);
		session = sessionFactory.openSession();
		Query query = session.createQuery("update User set password=:password where randomUUID=:randomUUID");
		query.setParameter("password", password);
		query.setParameter("randomUUID", randomUUID);
		query.executeUpdate();
		System.out.println("Record updated...");
		return true;
	}

	public User getUserByRandomId(String randomUUID) {

		System.out.println("id " + randomUUID);

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("randomUUID", randomUUID));
		User user = (User) criteria.uniqueResult();
		//System.out.println("email id " + user.getEmail());
		return user;
	}

	@Override
	public User updateRecord(User user) {

		Session session = sessionFactory.getCurrentSession();
		session.update(user);
		System.out.println("Record updated...");
		return user;

	}

	@Override
	public String getUserPassword() {

		return null;
	}
}
