/**
 * 
 */
package com.bridgelab.todo.user.util;

import com.bridgelab.todo.notes.model.Notes;

/**
 * @author bridgeit
 *
 */
public class NotesDTO {
	private long noteId;
	private String title;
	private String description;
	private Boolean trash;
	
	public NotesDTO(Notes object) {
		this.noteId=object.getNoteId();
		this.title=object.getTitle();
		this.description=object.getDescription();
		this.trash=object.getTrash();
	
	}
	

	public long getNoteId() {
		return noteId;
	}


	public void setNoteId(long noteId) {
		this.noteId = noteId;
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public Boolean getTrash() {
		return trash;
	}


	public void setTrash(Boolean trash) {
		this.trash = trash;
	}
	

}
