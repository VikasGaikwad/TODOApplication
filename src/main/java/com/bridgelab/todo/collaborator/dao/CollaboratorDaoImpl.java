/**
 * 
 */
package com.bridgelab.todo.collaborator.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelab.todo.collaborator.model.Collaborator;
import com.bridgelab.todo.notes.model.Notes;
import com.bridgelab.todo.user.model.User;

/**
 * @author bridgeit
 *
 */
public class CollaboratorDaoImpl implements ICollaboratorDao {
	@Autowired
	private SessionFactory sessionFactory;
	protected Session session;

	@Override
	public boolean saveCollaborator(Collaborator collaborator) {
		session = sessionFactory.getCurrentSession();
		session.save(collaborator);
		return true;

	}

	@Override
	public int deleteCollaborator(Notes note, User shareduser) {
		int id = 0;
		session = sessionFactory.getCurrentSession();
		String hql = "delete from Collaborator where noteId = :noteId and shareduser = :sharedId";
		Query query = session.createQuery(hql);
		
		query.setParameter("sharedId", shareduser);
		id = query.executeUpdate();
		return id;
	}
	@Override
	public void addCollaborator(Collaborator collaborator) {

	Session session = sessionFactory.getCurrentSession();
	session.save(collaborator);
	System.out.println("collaborat successfully");	
	}

	@Override
	public void removeCollaborator(User user, Notes notes) {
		
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "delete from Collaborator c where c.noteId = :note and c.sharedUserId = :sharedUser";
		Query query = session.createQuery(hql); 
		query.setParameter("note", notes);
		query.setParameter("sharedUser", user);
		query.executeUpdate();
		System.out.println("record deleted... ");
	}
	@Override
	public List<Collaborator> getCollbySharedId(User sharedUser) {
	Session session = sessionFactory.getCurrentSession();
	String hql = "from Collaborator where sharedId = :sharedId";
	Query query = session.createQuery(hql);
	query.setParameter("sharedId", sharedUser);
	List<Collaborator> coll = query.list();
	return coll;
	}

	

}
