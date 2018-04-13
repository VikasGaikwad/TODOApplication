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

	void createNote(Notes notes, int id);

	
	void updateNotes(Notes notes, long noteId);
	void deleteNotes(long noteId);

	Notes getNoteById(long noteId);


	void readNotes(String token);

	
	
	
	

	
}
