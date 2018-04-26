/**
 * 
 */
package com.bridgelab.todo.label.dao;

import java.util.List;

import com.bridgelab.todo.label.model.Label;
import com.bridgelab.todo.user.model.User;

/**
 * @author bridgeit
 *
 */
public interface ILabelDao {

	void addLabel(Label label);

	List<Label> isLabelPresent(String label);

	List<Label> readLabel(User user);

	int deleteLabels(Label label, int id);

	void addLableOnNote(int noteId, int labelId);

	Label getLabelById(int labelId);



}
