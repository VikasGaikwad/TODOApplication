/**
 * 
 */
package com.bridgelab.todo.notes.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelab.todo.notes.model.Notes;

/**
 * @author bridgeit
 *
 */
public class NotesDaoImpl implements INotesDao {

	Session session;
	@Autowired
	private SessionFactory sessionFactory;

	
	
	@Override
	public int createNote(Notes notes) {
		System.out.println("******NotesDaoImpl******");
		System.out.println("userId - "+notes.getUser().getUserId());
		System.out.println("userEmail - "+notes.getUser().getEmail());
		session = sessionFactory.openSession();
		long notedata = (long) session.save(notes);
		return (int) notedata;

	}
	
	

	@Override
	public void updateNotes(Notes notes, long noteId) {
		Session session = sessionFactory.openSession();
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
