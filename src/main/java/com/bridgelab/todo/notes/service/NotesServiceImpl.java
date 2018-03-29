/**
 * 
 */
package com.bridgelab.todo.notes.service;

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
	public int createNote(Notes notes,User user) {
		System.out.println("********NotesServiceImpl*********");
		System.out.println("userId - " +user.getUserId());
		System.out.println("user email - "+user.getEmail());
		
		notes.setUser(user);
		return notesDao.createNote(notes);

	}

	public void updateNotes(Notes notes, long noteId) {

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
