/**
 * 
 */
package com.bridgelab.todo.notes.dao;

import java.util.Date;

import com.bridgelab.todo.notes.model.Notes;

/**
 * @author bridgeit
 *
 */
public interface INotesDao {

	int createNote(Notes notes);
	
	void updateNotes(Notes notes, long noteId);

	

	boolean deleteNotes(long noteId);




	Notes getNoteById(long noteId);

	

	

}
