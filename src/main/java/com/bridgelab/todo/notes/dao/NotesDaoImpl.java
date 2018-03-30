/**
 * 
 */
package com.bridgelab.todo.notes.dao;

import java.util.Date;

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

	
	@Autowired
	private SessionFactory sessionFactory;
	protected Session session;
	/*----------------------------------------------------------------------*/
	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
/*	@Override
	public void createNote(Notes notes) {
		currentSession().save(notes);
		
		}*/
	
	
	/*----------------------------------------------------------------------*/

	@Override
	public int createNote(Notes notes) {
		
		session = sessionFactory.openSession();
		long notedata = (long) session.save(notes);
		System.out.println("created date-----"+notes.getCreatedDate());
		return (int) notedata;

	}
	
	

	@Override
	public void updateNotes(Notes notes, long noteId) {
		/*Session session = sessionFactory.openSession();*/
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
