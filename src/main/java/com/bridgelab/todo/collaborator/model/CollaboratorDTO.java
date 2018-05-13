/**
 * 
 */
package com.bridgelab.todo.collaborator.model;

import com.bridgelab.todo.notes.model.Notes;
import com.bridgelab.todo.user.model.User;

/**
 * @author bridgeit
 *
 */
public class CollaboratorDTO {
private int cid;
private Notes noteId;
private User ownerId;
private User sharedId;


public CollaboratorDTO() {
	// TODO Auto-generated constructor stub
}

public int getCid() {
	return cid;
}

public void setCid(int cid) {
	this.cid = cid;
}

public Notes getNoteId() {
	return noteId;
}

public void setNoteId(Notes noteId) {
	this.noteId = noteId;
}

public User getOwnerId() {
	return ownerId;
}

public void setOwnerId(User ownerId) {
	this.ownerId = ownerId;
}

public User getSharedId() {
	return sharedId;
}

public void setSharedId(User sharedId) {
	this.sharedId = sharedId;
}

public CollaboratorDTO(Collaborator object) {
	
	this.cid = object.getcId();
	this.noteId = object.getNoteId();
	this.ownerId = object.getOwnerId();
	this.sharedId = object.getSharedId();
}


}
