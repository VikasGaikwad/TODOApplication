/**
 * 
 */
package com.bridgelab.todo.notes.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelab.todo.notes.dao.INotesDao;
import com.bridgelab.todo.notes.model.Notes;
import com.bridgelab.todo.user.dao.IUserDao;
import com.bridgelab.todo.user.model.User;
import com.bridgelab.todo.user.service.IUserService;
import com.bridgelab.todo.user.util.ImageDTO;
import com.bridgelab.todo.user.util.JWT_Tokens;
import com.bridgelab.todo.user.util.NotesDTO;

/**
 * @author bridgeit
 *
 */
public class NotesServiceImpl implements INotesService {

	@Autowired
	INotesDao notesDao;

	@Autowired
	IUserDao userDao;

	@Autowired
	IUserService userService;
	/*
	 * @Transactional - It is necessary that if you are interacting with the
	 * database either to update or insert or delete you must need to perform this
	 * with in a transaction. If any exception occurs in performing the transaction,
	 * it rollbacks the other tasks executed in same transaction.
	 */
	@Transactional
	@Override
	public void createNote(Notes notes,int id) {

		Date createdDate = new Date();
		notes.setCreatedDate(createdDate);

		User user=new User();
		user.setUserId(id);
		notes.setUser(user);
		notesDao.createNote(notes);


	}
	/*--------------------------------------------------------*/

	@Transactional
	@Override
	public void updateNotes(Notes notes, long noteId) {

		User user = userService.getUserById(noteId);
		/*notes.setCreatedDate(notes.getCreatedDate());*/
		notes.setUser(user);
		notesDao.updateNotes(notes, noteId);
	}
	/*--------------------------------------------------------*/
	@Transactional
	@Override
	public void deleteNotes(long noteId,int note_id) {
		notesDao.deleteNotes(noteId,note_id);

	}
	/*--------------------------------------------------------*/
	@Transactional
	@Override
	public Notes getNoteById(long noteId) {

		return notesDao.getNoteById(noteId);
	}
	/*--------------------------------------------------------*/
	@Transactional
	@Override
	public void readNotes(String token) {
		int id=JWT_Tokens.verifyToken(token);
		System.out.println("notes id : "+id);
		@SuppressWarnings("unused")
		Notes notes=notesDao.getNoteById(id);
	}
	/*--------------------------------------------------------*/
	@Transactional
	@Override
	public List<NotesDTO> getAllNotesByUserId(long userId) {
		List<Notes> notes=notesDao.getAllNotesByUserId(userId);
		List<NotesDTO> responseDTO=new ArrayList<>();
		for (Notes object : notes) {
			NotesDTO obj=	new NotesDTO(object);
			responseDTO.add(obj);
		}
		return responseDTO;
	}
	
	/*--------------------------------------------------------*/
	@Transactional
	@Override
	public void uploadImage(ImageDTO imagedto, int userId) throws FileNotFoundException, IOException {
		Notes noteObject=new Notes();
		User user=new User();
		noteObject.setNoteId(imagedto.getNoteId());
		noteObject.setFullPath(imagedto.getFullPath());
		user.setUserId(userId);
		noteObject.setUser(user);
		notesDao.uploadImage(noteObject);
		

	}
}
