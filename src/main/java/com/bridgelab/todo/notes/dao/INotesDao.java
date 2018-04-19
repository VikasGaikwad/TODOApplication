/**
 * 
 */
package com.bridgelab.todo.notes.dao;

import java.util.List;

import com.bridgelab.todo.notes.model.Notes;

/**
 * @author bridgeit
 *
 */
public interface INotesDao {

	int createNote(Notes notes);
	
	void updateNotes(Notes notes, long noteId);

	

	boolean deleteNotes(long noteId,int user_id);




	Notes getNoteById(long noteId);
	 List<Notes> getAllNotesByUserId(long userId);

	

	

}
