/**
 * 
 */
package com.bridgelab.todo.label.service;

import java.util.List;

import com.bridgelab.todo.label.model.Label;

/**
 * @author bridgeit
 *
 */
public interface ILabelService {

	void addLabel(Label label,int userId);
	boolean isLabelPresent(String label);
	List<Label> readLabel(int id);
	boolean deleteLabel(Label label, int id);
	

}
