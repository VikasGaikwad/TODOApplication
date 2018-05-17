/**
 * 
 */
package com.bridgelab.todo.user.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bridgelab.todo.collaborator.model.Collaborator;
import com.bridgelab.todo.collaborator.model.CollaboratorDTO;
import com.bridgelab.todo.label.model.Label;
import com.bridgelab.todo.label.model.LabelRes;
import com.bridgelab.todo.notes.model.Notes;
import com.mysql.jdbc.Blob;

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
	private String color;
	private byte[] image;
	private String collaboratorName;
	private List<LabelRes> labels = new ArrayList<>();
	private List<CollaboratorDTO> collaborators = new ArrayList<>();

	// private byte[] image;

	public NotesDTO(Notes object) {
		this.noteId = object.getNoteId();
		this.title = object.getTitle();
		this.description = object.getDescription();
		this.trash = object.getTrash();
		this.archive = object.getArchive();
		this.pin = object.getPin();
		this.reminder = object.getReminder();
		this.color = object.getColor();
		this.image=object.getImage();
		this.collaboratorName = object.getCollaboratorName();
		for (Label label : object.getLabels()) {
			labels.add(new LabelRes(label));
		}
		for (Collaborator collaborator : object.getCollaborators()) {
			collaborators.add(new CollaboratorDTO(collaborator));
		}
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	

	public List<LabelRes> getLabels() {
		return labels;
	}

	public void setLabels(List<LabelRes> labels) {
		this.labels = labels;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public List<CollaboratorDTO> getCollaborators() {
		return collaborators;
	}

	public void setCollaborators(List<CollaboratorDTO> collaborators) {
		this.collaborators = collaborators;
	}

	public String getCollaboratorName() {
		return collaboratorName;
	}

	public void setCollaboratorName(String collaboratorName) {
		this.collaboratorName = collaboratorName;
	}

	
}
