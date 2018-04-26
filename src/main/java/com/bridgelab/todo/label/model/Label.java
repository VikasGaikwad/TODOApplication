/**
 * 
 */
package com.bridgelab.todo.label.model;

/**
 * @author bridgeit
 *
 */
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bridgelab.todo.notes.model.Notes;
import com.bridgelab.todo.user.model.User;



/**
 * @author bridgeit
 *
 */

@Entity
@Table(name="Label")
public class Label implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)

	@Column
	private int labelId;


	@Column
	private String labelName;
	
	
	@ManyToMany(mappedBy = "labels")
	private Set<Notes> notes=new HashSet<Notes>();
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	public Label() {
		
	}


	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	public int getLabelId() {
		return labelId;
	}
	public void setLabelId(int labelId) {
		this.labelId = labelId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<Notes> getNotes() {
		return notes;
	}
	public void setNotes(Set<Notes> notes) {
		this.notes = notes;
	}
	public Label(int labelId, String labelName, Set<Notes> notes, User user) {
		this.labelId = labelId;
		this.labelName = labelName;
		this.notes = notes;
		this.user = user;
	}


}

