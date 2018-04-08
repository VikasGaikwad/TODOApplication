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

	/*
	 * @tTransactional - It is necessary that if you are interacting with the
	 * database either to update or insert or delete you must need to perform this
	 * with in a transaction. If any exception occurs in performing the transaction,
	 * it rollbacks the other tasks executed in same transaction.
	 */
	@Transactional
	@Override
	public void createNote(Notes notes, User user) {
		Date createdDate = new Date();
		notes.setCreatedDate(createdDate);
		notes.setUser(user);
		notesDao.createNote(notes);

	}

	@Transactional
	@Override
	public void updateNotes(Notes notes, long noteId) {

		notesDao.updateNotes(notes, noteId);
	}

	@Transactional
	@Override
	public void deleteNotes(long noteId) {
		notesDao.deleteNotes(noteId);

	}

	@Override
	public Notes getNoteById(long noteId) {

		return notesDao.getNoteById(noteId);
	}

}
