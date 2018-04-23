/**
 * 
 */
package com.bridgelab.todo.label.dao;

import java.util.List;

import com.bridgelab.todo.label.model.Label;

/**
 * @author bridgeit
 *
 */
public interface ILabelDao {

	void addLabel(Label label);

	List<Label> isLabelPresent(String label);

	List<Label> readLabel(int id);

	int deleteLabels(Label label, int id);

}
