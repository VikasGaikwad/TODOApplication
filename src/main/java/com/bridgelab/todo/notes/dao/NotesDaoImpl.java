/**
 * 
 */
package com.bridgelab.todo.notes.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanConstructorResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelab.todo.notes.model.Notes;

/**
 * @author bridgeit
 *
 */
/*
 * @Repository()- returns the component name, if any needed, Indicates that an
 * annotated class is a "Repository"- 4 classes-
 * 
 * @Target({ElementType.TYPE}) - Indicates the contexts in which an annotation
 * type is applicable.
 * 
 * @Retention(RetentionPolicy.RUNTIME)-Indicates how long annotations with the
 * annotated type are to be retained.
 * 
 * @Documented -Indicates that annotations with a type are to be documented by
 * javadoc and similar tools by default.
 * 
 * @Component-Indicates that an annotated class is a "component".
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

	/*-------------------------------------------------------------------------*/
	@Override
	public int createNote(Notes notes) {

		System.out.println(notes.getTitle() + "......" + notes.getTrash());		
		session = sessionFactory.getCurrentSession();
		long notedata = (long) session.save(notes);
		return (int) notedata;

	}
	/*-------------------------------------------------------------------------*/

	@Override
	public void updateNotes(Notes notes, long UserId) {

		session = sessionFactory.getCurrentSession();

		System.out.println(notes.getDescription() + "..." + notes.getTitle() + "...." + notes.getTrash() + " "
				+ notes.getArchive()+"..."+notes.getImage());
		session.update(notes);
		System.out.println("Record updated...");
	}
	/*-------------------------------------------------------------------------*/

	@Override
	public boolean deleteNotes(long noteId, int user_id) {
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
	
	
	/*-------------------------------------------------------------------------*/

	@Override
	public Notes getNoteById(long noteId) {
		return (Notes) sessionFactory.getCurrentSession().get(Notes.class, noteId);
	}
	
	
	
	/*-------------------------------------------------------------------------*/
	
	
	
	@Override
	public List<Notes> getAllNotesByUserId(long userId) {
		//String sqlQuery = "from Notes where noteId=:noteId";
		session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Notes.class);
		/*criteria.setProjection(Projections.projectionList().add(Projections.property("noteId"), "noteId")
				.add(Projections.property("title"), "title").add(Projections.property("description"), "description")
				.add(Projections.property("trash"), "trash").add(Projections.property("archive"), "archive")
				.add(Projections.property("pin"), "pin").add(Projections.property("reminder"), "reminder")
				.add(Projections.property("color"), "color"))
		.setResultTransformer(Transformers.aliasToBean(Notes.class));*/
		criteria.add(Restrictions.eq("user.userId", userId));

		@SuppressWarnings("unchecked")
		List<Notes> list = criteria.list();
		if (!list.isEmpty()) {
			System.out.println("following notes found..");
			/*
			 * list.get(0); list.get(1); list.get(2);
			 */
			for (Notes notes : list) {
				System.out.println(notes.getNoteId());
				System.out.println(notes.getTitle());
				System.out.println(notes.getDescription());
				System.out.println(notes.getTrash());
				System.out.println(notes.getCreatedDate());
				System.out.println(notes.getReminder());
				System.out.println((notes.getColor()));
				System.out.println("====================");
			}
		}
		return list;
	}
	/*-------------------------------------------------------------------------*/

	@Override
	public void saveImage(Notes noteImage) {
		sessionFactory.getCurrentSession().save(noteImage);		
	}
	@Override
	public void downloadImage(int noteId) {	
	//String hql="from Notes where noteId=:noteId";	
	session=sessionFactory.getCurrentSession();
	Criteria criteria=session.createCriteria(Notes.class);
	criteria.setProjection(Projections.projectionList().add(Projections.property("image"),"image"));
	criteria.add(Restrictions.eq("noteId", noteId));
	}

	@Override
	public void deleteImage(int noteId) {
		String delete_hql="delete image from Notes where noteId=:noteId";
		Query query=session.createQuery(delete_hql);
		query.setParameter("noteId", noteId);
		query.executeUpdate();
		
	}

	/*-------------------------------------------------------------------------*/

}
