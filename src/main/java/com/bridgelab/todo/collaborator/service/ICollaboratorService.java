package com.bridgelab.todo.collaborator.service;

import com.bridgelab.todo.user.util.CollaboratorDTO;

public interface ICollaboratorService {
	public void addCollaborator(String sharedUseremail, int noteId, int userId);
	
	void saveCollaborator(CollaboratorDTO collaboratorDto, int userId);

	boolean deleteCollaborator(CollaboratorDTO collaboratorDTO);

	 void removeCollaborator(String sharedUserEmail, int noteId);

}
