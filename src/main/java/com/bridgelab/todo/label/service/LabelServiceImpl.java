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
	public boolean deleteLabel(int labelId, int id) {
		int row = labelDao.deleteLabels(labelId, id);
		if (row != 0) {
			return true;
		}
		return false;
	}

	@Transactional
	@Override
	public void deleteLabelFromNote(int labelId, int noteId) {
		labelDao.deleteLabelFromNotes(labelId, noteId);
	}

	@Transactional
	@Override
	public void addLabelOnNote(int labelId, int noteId) {



		Notes note=notesService.getNoteById(noteId);

		System.out.println("labelservice impl note id"+note.getNoteId());
		System.out.println("label service impl- title -"+note.getNoteId());
		Label label=labelDao.getLabelById(labelId);
		System.out.println("label service impl- name -"+label.getLabelId());
		note.getLabels().add(label);

		labelDao.addLabelOnNote(note);

	}
	@Transactional
	@Override
	public void updateLabel(Label label, int userId) {
		User userObj = new User();
		//User userObj=null;
		userObj.setUserId(userId);
		label.setUser(userObj);
		labelDao.updateLabel(label);
	}
}
