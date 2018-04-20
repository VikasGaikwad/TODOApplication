/**
 * 
 */
package com.bridgelab.todo.user.util;

import java.util.Date;

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
	private Boolean archive;
	private Boolean pin;
	private Date reminder;
	
	public NotesDTO(Notes object) {
		this.noteId=object.getNoteId();
		this.title=object.getTitle();
		this.description=object.getDescription();
		this.trash=object.getTrash();
		this.archive=object.getArchive();
		this.pin=object.getPin();
		this.reminder=object.getReminder();
	
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


	public Boolean getArchive() {
		return archive;
	}


	public void setArchive(Boolean archive) {
		this.archive = archive;
	}


	public Boolean getPin() {
		return pin;
	}


	public void setPin(Boolean pin) {
		this.pin = pin;
	}


	public Date getReminder() {
		return reminder;
	}


	public void setReminder(Date reminder) {
		this.reminder = reminder;
	}
	

}
