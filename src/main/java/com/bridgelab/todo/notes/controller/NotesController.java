/**
* 
*/
package com.bridgelab.todo.notes.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.bridgelab.todo.user.util.JWT_Tokens;

/**
 * @author bridgeit
 *
 */
@RestController
//@RequestMapping(value="/notes")
public class NotesController {
	@Autowired
	INotesService notesService;
	

	@RequestMapping(value = "userapi/createnote", method = RequestMethod.POST)
	public ResponseEntity<?> newNote(@RequestBody Notes notes, HttpServletRequest request,HttpServletResponse response) {
		/*User user = (User) request.getSession().getAttribute("userId");
		System.out.println("----------"+user.getUserId());*/
		
		String token=request.getHeader("auth");
		System.out.println("token------"+token);
	int id=	JWT_Tokens.verifyToken(token);
	System.out.println("verified token---"+id);
	
		notesService.createNote(notes,id);
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "userapi/{noteId}/updatenote", method = RequestMethod.PUT)
	public ResponseEntity<String> updateNotes(@PathVariable("noteId") long noteId, @RequestBody Notes notes) {
		notesService.updateNotes(notes, noteId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "userapi/{noteId}/deletenote", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteNotes(@PathVariable("noteId") long noteId) {
		notesService.deleteNotes(noteId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "userapi/{noteId}/readonenote", method = RequestMethod.GET)
	public ResponseEntity<Notes> getnote(@PathVariable("noteId") long noteId) {

		
		Notes notes = notesService.getNoteById(noteId);
		return new ResponseEntity<Notes>(notes, HttpStatus.OK);

	}
	@RequestMapping(value="userapi/readallnotes", method=RequestMethod.GET)
	public ResponseEntity<?> readNotes(HttpServletRequest request,HttpServlet response){

		String token=request.getHeader("auth");
		System.out.println("token------"+token);
	     int userId=JWT_Tokens.verifyToken(token);
	     List<Notes> notes=notesService.getAllNotesByUserId(userId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}


}
