/**
 * 
 */
package com.bridgelab.todo.label.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelab.todo.label.dao.ILabelDao;
import com.bridgelab.todo.label.model.Label;
import com.bridgelab.todo.notes.dao.INotesDao;
import com.bridgelab.todo.notes.model.Notes;
import com.bridgelab.todo.notes.service.INotesService;
import com.bridgelab.todo.user.model.User;
import com.bridgelab.todo.user.service.IUserService;

/**
 * @author bridgeit
 *
 */
@Service
public class LabelServiceImpl implements ILabelService {

	@Autowired
	ILabelDao labelDao;

	@Autowired
	IUserService userService;
	
	@Autowired
	INotesService notesService;
	
	

	@Transactional
	@Override
	public void addLabel(Label label, int userId) {
		User user = new User();
		user.setUserId(userId);
		label.setUser(user);
		labelDao.addLabel(label);
	}

	@Transactional
	@Override
	public boolean isLabelPresent(String label) {
		List<Label> labelPresent = labelDao.isLabelPresent(label);
		if (labelPresent.size() != 0) {
			return true;
		}
		return false;
	}

	@Transactional
	@Override
	public List<Label> readLabel(int id) {
		User user = userService.getUserById(id);

		List<Label> label = null;
		if (id != 0) {
			label = labelDao.readLabel(user);
			return label;
		}

		return label;
	}

	@Transactional
	@Override
	public boolean deleteLabel(Label label, int id) {
		int row = labelDao.deleteLabels(label, id);
		if (row != 0) {
			return true;
		}
		return false;
	}

	@Transactional
	@Override
	public void deleteLabelFromNote() {

	}

	@Transactional
	@Override
	public void addLabelOnNote(int noteId, int labelId) {
		Notes notes=notesService.getNoteById(noteId);
		//System.out.println("label service impl- title -"+notes.getTitle());
		Label label=labelDao.getLabelById(labelId);
		//System.out.println("label service impl- name -"+label.getLabelName());
		labelDao.addLabelOnNote(noteId, labelId);

	}
}
