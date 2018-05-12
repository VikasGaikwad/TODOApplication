package com.bridgelab.todo.collaborator.dao;

import com.bridgelab.todo.collaborator.model.Collaborator;
import com.bridgelab.todo.notes.model.Notes;
import com.bridgelab.todo.user.model.User;

public interface ICollaboratorDao {

	void addCollaborator(Collaborator collaborator);
	boolean saveCollaborator(Collaborator collaborator);

	int deleteCollaborator(Notes note, User shareduser);
	void removeCollaborator(User user, Notes notes);
	
	
	
	
	
}
