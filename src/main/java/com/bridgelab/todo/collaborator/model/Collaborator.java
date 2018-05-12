/**
 * 
 */
package com.bridgelab.todo.collaborator.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bridgelab.todo.notes.model.Notes;
import com.bridgelab.todo.user.model.User;

/**
 * @author bridgeit
 *
 */
@Entity
@Table(name="collaborator")
public class Collaborator implements Serializable{

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int cId;
	
	
	@ManyToOne
	@JoinColumn(name="ownerId")
	private User ownerId;
	
	@ManyToOne
	@JoinColumn(name="sharedId")
	private User sharedId;
	
	@ManyToOne
	@JoinColumn(name="noteId")
	private Notes noteId;
	
	public Collaborator() {
	
	}
	
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
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
	public Notes getNoteId() {
		return noteId;
	}
	public void setNoteId(Notes noteId) {
		this.noteId = noteId;
	}
	
	
	}
