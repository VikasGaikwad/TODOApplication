/**
 * 
 */
package com.bridgelab.todo.notes.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelab.todo.notes.dao.INotesDao;
import com.bridgelab.todo.notes.model.Notes;
import com.bridgelab.todo.user.dao.IUserDao;
import com.bridgelab.todo.user.model.User;

/**
 * @author bridgeit
 *
 */
public class NotesServiceImpl implements INotesService {

	@Autowired
	INotesDao notesDao;

	@Autowired
	IUserDao userDao;

	@Transactional
	@Override
	public void createNote(Notes notes, User user) {
System.out.println("user id------"+user.getUserId());
		Date createdDate = new Date();
		notes.setCreatedDate(createdDate);
		notes.setUser(user);
		notesDao.createNote(notes);

	}

	public void updateNotes(Notes notes, long noteId) {
		//Date updatedDate = new Date();
		//notes.setUpdatedDate(updatedDate);

		notesDao.updateNotes(notes, noteId);
	}

	@Override
	public void deleteNotes(long noteId) {
		notesDao.deleteNotes(noteId);

	}

	@Override
	public Notes getNoteById(long noteId) {

		return notesDao.getNoteById(noteId);
	}

}
