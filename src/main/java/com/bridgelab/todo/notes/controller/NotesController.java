/**
* 
*/
package com.bridgelab.todo.notes.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelab.todo.notes.model.Notes;
import com.bridgelab.todo.notes.service.INotesService;
import com.bridgelab.todo.user.model.User;

/**
 * @author bridgeit
 *
 */
@RequestMapping(value = "notes")
@RestController
public class NotesController {
	@Autowired
	INotesService notesService;

	@RequestMapping(value = "createnote", method = RequestMethod.POST)
	public ResponseEntity<?> newNote(@RequestBody Notes notes, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("userObjectSession");
		System.out.println("******NotesController******");
		System.out.println("userId - "+user.getUserId()+"\nuserEmail = "+user.getEmail());
		notesService.createNote(notes,user);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "{noteId}/updatenote", method = RequestMethod.PUT)
	public ResponseEntity<String> updateNotes(@PathVariable("noteId") long noteId, @RequestBody Notes notes) {
		notesService.updateNotes(notes, noteId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "{noteId}/deletenote", method = RequestMethod.DELETE)
	public ResponseEntity<String> newDeleteNotes(@PathVariable("noteId") long noteId) {
		notesService.deleteNotes(noteId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "{noteId}/readnote", method = RequestMethod.GET)
	public ResponseEntity<Notes> getnote(@PathVariable("noteId") long noteId) {

		
		Notes notes = notesService.getNoteById(noteId);
		return new ResponseEntity<Notes>(notes, HttpStatus.OK);

	}


}
