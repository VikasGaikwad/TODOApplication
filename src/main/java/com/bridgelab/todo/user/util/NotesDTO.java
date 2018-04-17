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
	private String title;
	private String description;
	
	public NotesDTO(Notes object) {
		this.title=object.getTitle();
		this.description=object.getDescription();
	
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
	

}
