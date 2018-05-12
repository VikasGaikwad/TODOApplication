/**
 * 
 */
package com.bridgelab.todo.collaborator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelab.todo.collaborator.dao.ICollaboratorDao;
import com.bridgelab.todo.collaborator.model.Collaborator;
import com.bridgelab.todo.notes.dao.INotesDao;
import com.bridgelab.todo.notes.model.Notes;
import com.bridgelab.todo.user.dao.IUserDao;
import com.bridgelab.todo.user.model.User;
import com.bridgelab.todo.user.util.CollaboratorDTO;

/**
 * @author bridgeit
 *
 */
public class CollaboratorServiceImpl implements ICollaboratorService {
@Autowired
ICollaboratorDao collaboratorDao;

@Autowired
IUserDao userDao;

@Autowired
INotesDao notesDao;

@Transactional
@Override
public void addCollaborator(String sharedUseremail, int noteId, int userId) {

Collaborator collaborator = new Collaborator();
//User user = userDao.getUserByEmaiId(sharedUseremail);
collaborator.setOwnerId(userDao.getUserById(userId));
collaborator.setSharedId(userDao.getUserByEmail(sharedUseremail));
//Notes notes = notesDao.getNotebyNoteId(noteId);
Notes notes = notesDao.getNoteById(noteId);
collaborator.setNoteId(notes);
collaboratorDao.addCollaborator(collaborator);



}

@Override
public void saveCollaborator(CollaboratorDTO collaboratorDto, int userId) {
	// TODO Auto-generated method stub
	
}

@Override
public boolean deleteCollaborator(CollaboratorDTO collaboratorDTO) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public void removeCollaborator(String sharedId, int noteId) {
	User user = userDao.getUserByEmail(sharedId);
	Notes notes = notesDao.getNoteById(noteId);
	collaboratorDao.removeCollaborator( user, notes);
	
}

/*@Transactional
@Override
public void saveCollaborator(CollaboratorDTO collaboratorDto, int userId) {
	Collaborator collaborator=new Collaborator();
	collaborator.setOwnerId(userDao.getUserById(userId));
	//User user = userDao.getUserById(userId);	
	collaborator.setSharedId(userDao.getUserByEmail(collaboratorDto.getEmail()));
	collaborator.setNoteId(notesDao.getNoteById(collaboratorDto.getNoteId()));
	collaboratorDao.saveCollaborator(collaborator);
	
}
@Transactional
@Override
public boolean deleteCollaborator(CollaboratorDTO collaboratorDTO) {
	Collaborator collaborator=new Collaborator();
	Notes note=collaborator.getNoteId();
	User shareduser=collaborator.getSharedId();
	collaboratorDao.deleteCollaborator(note,shareduser);
	return true;
	
	
}*/
	
}
