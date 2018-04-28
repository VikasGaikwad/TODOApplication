/**
 * 
 */
package com.bridgelab.todo.notes.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.bridgelab.todo.label.model.Label;
import com.bridgelab.todo.user.model.User;
import com.mysql.jdbc.Blob;

/**
 * @author bridgeit
 *
 */

@Entity
@Table(name = "notes")
public class Notes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1802508173596798656L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long noteId;

	@Column
	private String title;

	@Column
	private String description;

	@Column
	private Date createdDate;
	@Column /*
			 * (name = "trash", columnDefinition = "boolean default true", nullable = false)
			 */
	private Boolean trash = false;
	@Column
	private Boolean archive = false;
	@Column
	private Boolean pin = false;
	@Column
	private Date reminder;
	@Column
	private String color;
	@Transient
	private String fullPath;
	/*
	 * @Transient private Label label;
	 */

	/*
	 * BLOB - Specifies that a persistent property or field should be persisted as a
	 * large object to a database- supported large object type.
	 */
	@Lob
	@Column
	private byte[] image;

	@ManyToOne /* (fetch=FetchType.LAZY) */
	@JoinColumn(name = "userId")
	private User user;

	@ManyToMany
	@JoinTable(name = "label_note", joinColumns = { @JoinColumn(name = "noteId") }, inverseJoinColumns = {
			@JoinColumn(name = "labelId") })
	private Set<Label> labels = new HashSet<Label>();

	public Notes() {

	}

	public Notes(long noteId, String title, String description, boolean trash, boolean archive, boolean pin,
			Date reminder, String color) {

		this.noteId = noteId;
		this.title = title;
		this.description = description;
		this.createdDate = createdDate;
		this.trash = trash;
		this.archive = archive;
		this.pin = pin;
		this.reminder = reminder;

	}

	public Notes(byte[] image) {

		this.image = image;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public Set<Label> getLabels() {
		return labels;
	}

	public void setLabels(Set<Label> labels) {
		this.labels = labels;
	}

	/*
	 * public Label getLabel() { return label; }
	 * 
	 * public void setLabel(Label label) { this.label = label; }
	 */

}
