/**
 * 
 */
package com.bridgelab.todo.user.dao;

import org.hibernate.Criteria;
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

		session = sessionFactory.openSession();
		long id = (long) session.save(user);
		return (int) id;

	}

	@Override
	public User loginUser(User user1) {

		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		Criterion email = Restrictions.eq("email", user1.getEmail());
		Criterion password = Restrictions.eq("password", user1.getPassword());
		Criterion criterian = Restrictions.and(email, password);
		criteria.add(criterian);
		user1 = (User) criteria.uniqueResult();
		System.out.println(user1.getEmail() + "   " + user1.getPassword());

		return user1;

	}

	@Override
	public User getUserById(long userId) {

		return (User) sessionFactory.getCurrentSession().get(User.class, userId);
	}

	@Override
	public User sendingMail(User user) {

		return (User) sessionFactory.getCurrentSession().get(User.class, user.getEmail());
	}

	@Override
	public User getUserByEmail(String email) {
		
		return (User) sessionFactory.getCurrentSession().get(User.class, email);
	}

	@Override
	public String getUserEmailId(String randomUUID) {
		 String sql="select email from User where randomUUID=?";
		return sql;
	}

	@Override
	public boolean resetPassword(String username, String password) {
		int rowCount=0;
		String sqlUpdate="update User set password=? where username=?";
		
		return true;
	}

	public User getUserByRandomId(String randomUUID) {

		System.out.println("id "+randomUUID);

		Session session =sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("randomUUID", randomUUID));
		User user = (User) criteria.uniqueResult();		
		return user;
		}

	
	@Override
	public User updateRecord(User user) {
	
	Session session = sessionFactory.getCurrentSession();
	session.update(user);
	System.out.println("Record updated...");
	return user; 

}
}
