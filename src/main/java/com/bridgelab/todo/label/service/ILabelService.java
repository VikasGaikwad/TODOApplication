/**
 * 
 */
package com.bridgelab.todo.label.service;

import java.util.List;

import com.bridgelab.todo.label.model.Label;
import com.bridgelab.todo.user.model.User;

/**
 * @author bridgeit
 *
 */
public interface ILabelService {

	void addLabel(Label label,int userId);
	boolean isLabelPresent(String label);
	List<Label> readLabel(int id);
	boolean deleteLabel(Label label, int id);
	public void addLabelOnNote(int labelId, int noteId);	
	void deleteLabelFromNote(int labelId, int noteId);
	
	

}
