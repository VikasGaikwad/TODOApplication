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
public void removeCollaborator(String sharedId, int noteId) {
	User user = userDao.getUserByEmail(sharedId);
	Notes note = notesDao.getNoteById(noteId);
	collaboratorDao.removeCollaborator( user, note);
	
}




}
