package com.bridgelab.todo.collaborator.service;

import com.bridgelab.todo.collaborator.model.CollaboratorDTO;

public interface ICollaboratorService {
	public void addCollaborator(String sharedUseremail, int noteId, int userId);
	
	

	 void removeCollaborator(String sharedUserEmail, int noteId);

}
