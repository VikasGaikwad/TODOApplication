/**
 * 
 */
package com.bridgelab.todo.label.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelab.todo.label.model.Label;
import com.bridgelab.todo.label.service.ILabelService;

/**
 * @author bridgeit
 *
 */

@RestController
public class LabelController {

	@Autowired
	ILabelService labelService;

	// --------------------------------------------------------------------- //

	@RequestMapping(value = "user/addlabel", method = RequestMethod.POST)
	public ResponseEntity<?> addlabel(@RequestBody Label label, HttpServletRequest request) {
		int userId=(int) request.getAttribute("userId");
		labelService.addLabel(label,userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// ---------------------------------------------------------------------- //

	/*when mapping  userId, code need to be chenge */ 
	@RequestMapping(value = "user/readLabel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Label>> readLabel(HttpServletRequest request) {
		System.out.println("inside label controller");
		int id = (int) request.getAttribute("userId");
		if (id != 0) {
			List<Label> labelList = labelService.readLabel(id);
			return new ResponseEntity<List<Label>>(labelList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Label>>(HttpStatus.CONFLICT);
		}
	}
	// ----------------------------------------------------------------------- //

	@RequestMapping(value="user/deletelabel", method=RequestMethod.DELETE)
	public ResponseEntity<?> deletelabel(@RequestBody Label label,HttpServletRequest request){
		int id=(int) request.getAttribute("userId");
		try {
			labelService.deleteLabel(label,id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}


		
	}
	//-------------------------------------------------------------------------//
	
	
	@RequestMapping(value="user/addLabelOnNotes/{noteId}/{labelId}/{checked}", method= RequestMethod.PUT)
	public ResponseEntity<?> addLabelOnNote(@PathVariable("noteId") int noteId, @PathVariable("labelId") int labelId,
			@PathVariable("checked") String checked){
		
		boolean status = Boolean.valueOf(checked);
		
		if(status) {
			
			labelService.addtheLabel(noteId, labelId);
			
			
		}else if(!status) {
			
			labelService.deleteLabelFromNote();
		}
	
		return null;
			
		
	}
}
