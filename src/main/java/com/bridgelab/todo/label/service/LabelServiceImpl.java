/**
 * 
 */
package com.bridgelab.todo.label.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelab.todo.label.dao.ILabelDao;
import com.bridgelab.todo.label.model.Label;

/**
 * @author bridgeit
 *
 */
public class LabelServiceImpl implements ILabelService{

	@Autowired 
	ILabelDao labelDao;
	@Transactional
	
	public void addLabel(Label label,int userId) {
		
		labelDao.addLabel(label);
	}
	@Transactional
	@Override
	public boolean isLabelPresent(String label) {
		List<Label> labelPresent=labelDao.isLabelPresent( label);
		if(labelPresent.size()!=0) {
			return true;
		}
		return false;
	}
	@Transactional
	@Override
	public List<Label> readLabel(int id) {
		System.out.println("******************");
		List<Label> label=null;
		if(id!=0) {
			label=labelDao.readLabel(id);
			return label;
		}
		
		return label;
	}
	
	
	

@Override
public boolean deleteLabel(Label label, int id) {
	int row=labelDao.deleteLabels(label,id);
	if(row!=0) {
		return true;
	}
	return false;
}
}
