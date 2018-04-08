/**
 * 
 */
package com.bridgelab.todo.notes.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelab.todo.notes.model.Notes;

/**
 * @author bridgeit
 *
 */
@Repository()
public class NotesDaoImpl implements INotesDao {

	/*
	 * Session Factory : This is factory of session objects. To create the session
	 * factory object, we need to have configuration object to create session
	 * factory.
	 */
	@Autowired
	private SessionFactory sessionFactory;
	protected Session session;

	/*
	 * Session : It is basically a factory for Transaction, Query and Criteria. If
	 * we want to use the any of them, we will need to get it from session.
	 */
	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public int createNote(Notes notes) {

		/* getCurrentSession()-Obtains the current session. */
		session = sessionFactory.getCurrentSession();
		/*
		 * Persist the given transient instance, first assigning a generated identifier.
		 */
		long notedata = (long) session.save(notes);
		return (int) notedata;

	}

	@Override
	public void updateNotes(Notes notes, long noteId) {
		/* openSession() - btains the current session. */
		sessionFactory.openSession();
		Query query = (Query) session
				.createQuery("update Notes  set title=:title,description=:description where noteId=:noteId");
		query.setParameter("title", notes.getTitle());
		query.setParameter("description", notes.getDescription());
		query.setParameter("noteId", noteId);
		query.executeUpdate();

	}

	
	@Override
	public boolean deleteNotes(long noteId) {
		session = sessionFactory.openSession();
		try {
			String sqlQuery = "delete from Notes where noteId=:noteId";
			/*
			 * Query : query is a class that provides methods to write HQL(Hibernate Query
			 * Language) to perform the transaction.
			 */
			Query query = (Query) session.createQuery(sqlQuery);
			query.setParameter("noteId", noteId);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public Notes getNoteById(long noteId) {
		return (Notes) sessionFactory.openSession().get(Notes.class, noteId);
	}

}
