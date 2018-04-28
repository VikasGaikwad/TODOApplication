/**
 * 
 */
package com.bridgelab.todo.user.util;

/**
 * @author bridgeit
 *
 */
public class ImageDTO 
{
	private int noteId;
	private String fullPath;
	
	public int getNoteId() {
		return noteId;
	}
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	public String getFullPath() {
		return fullPath;
	}
	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}
	public ImageDTO(int noteId, String fullPath) {

		this.noteId = noteId;
		this.fullPath = fullPath;
	}
	public ImageDTO() {
		// TODO Auto-generated constructor stub
	}

}
