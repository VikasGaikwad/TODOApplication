/**
 * 
 */
package com.bridgelab.todo.notes.service;

import com.bridgelab.todo.notes.model.Notes;
import com.bridgelab.todo.user.model.User;

/**
 * @author bridgeit
 *
 */
public interface INotesService {

	int createNote(Notes notes, User user);

	
	void updateNotes(Notes notes, long noteId);
	void deleteNotes(long noteId);

	Notes getNoteById(long noteId);

	
	
	
	

	
}
