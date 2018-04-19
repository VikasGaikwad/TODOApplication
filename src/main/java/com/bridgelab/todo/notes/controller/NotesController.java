/**
* 
*/
package com.bridgelab.todo.notes.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelab.todo.notes.model.Notes;
import com.bridgelab.todo.notes.service.INotesService;
import com.bridgelab.todo.user.util.JWT_Tokens;
import com.bridgelab.todo.user.util.NotesDTO;

/**
 * @author bridgeit
 *
 */
@RestController
//@RequestMapping(value="/notes")
public class NotesController {
	@Autowired
	INotesService notesService;
	

	@RequestMapping(value = "/createnote", method = RequestMethod.POST)
	public ResponseEntity<?> createNote(@RequestBody Notes notes, HttpServletRequest request,HttpServletResponse response) {
	
		//int userId=(int) request.getAttribute("userId");
		
		int userId = JWT_Tokens.verifyToken(request.getHeader("Authorization"));

		
		notesService.createNote(notes,userId);
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "user/updatenote", method = RequestMethod.PUT)
	public ResponseEntity<String> updateNotes(@RequestBody Notes notes, HttpServletRequest request) {
		
		int userId = (int) request.getAttribute("userId");
		System.out.println("id =>" +userId);
		notesService.updateNotes(notes, userId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "user/deletenote/{noteId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteNotes(@PathVariable("noteId") long noteId, HttpServletRequest request) {
		int user_id=(int) request.getAttribute("userId");
		notesService.deleteNotes(noteId,user_id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "user/{noteId}/readonenote", method = RequestMethod.GET)
	public ResponseEntity<Notes> getnote(@PathVariable("noteId") long noteId) {

		
		Notes notes = notesService.getNoteById(noteId);
		return new ResponseEntity<Notes>(notes, HttpStatus.OK);

	}
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="user/readallnotes", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> readNotes(HttpServletRequest request,HttpServletResponse response){
		int userId=(int) request.getAttribute("userId");	 
		List<NotesDTO> notes = notesService.getAllNotesByUserId(userId);			
		return new ResponseEntity<List>(notes,HttpStatus.OK);
	}


}
