/**
 * 
 */
package com.bridgelab.todo.notes.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bridgelab.todo.notes.model.Notes;
import com.bridgelab.todo.user.util.NotesDTO;

/**
 * @author bridgeit
 *
 */
public interface INotesService {

	void createNote(Notes notes, int id);

	
	void updateNotes(Notes notes, long noteId);
	void deleteNotes(long noteId,int note_id);

	Notes getNoteById(long noteId);


	void readNotes(String token);
	List<NotesDTO> getAllNotesByUserId(long userId);


	void saveImage(MultipartFile fileUpload, int noteId) throws IOException;


	void downloadImage(int noteId);


	void deleteImage(int noteId);


	
	

	
	
	
	

	
}
