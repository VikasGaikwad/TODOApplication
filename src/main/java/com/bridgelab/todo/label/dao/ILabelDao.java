/**
 * 
 */
package com.bridgelab.todo.label.dao;

import java.util.List;

import com.bridgelab.todo.label.model.Label;
import com.bridgelab.todo.notes.model.Notes;
import com.bridgelab.todo.user.model.User;

/**
 * @author bridgeit
 *
 */
public interface ILabelDao {

	void addLabel(Label label);

	List<Label> isLabelPresent(String label);

	List<Label> readLabel(User user);

	int deleteLabels(int labelId, int id);

	void addLabelOnNote(Notes note);

	Label getLabelById(int labelId);

	void deleteLabelFromNotes(int labelId, int noteId);

	void updateLabel(Label label);



}
