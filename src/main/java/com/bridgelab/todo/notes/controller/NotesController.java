/**
 * 
 */
package com.bridgelab.todo.notes.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;//

import com.bridgelab.todo.Service.JsoupDemo;
import com.bridgelab.todo.Service.UrlInfo;
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

	/*------------------------------------------------------------------------------------*/
	@RequestMapping(value = "user/createnote", method = RequestMethod.POST)
		public ResponseEntity<?> createNote(@RequestBody Notes notes, HttpServletRequest request,
			HttpServletResponse response) {
		int userId = JWT_Tokens.verifyToken(request.getHeader("Authorization"));
	
		if(userId!=0) {		
	notesService.createNote(notes, userId);
		return new ResponseEntity<String>(HttpStatus.OK);
		}
		else {
			System.out.println("token empty...");
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
	}

	/*------------------------------------------------------------------------------------*/
	@RequestMapping(value = "user/updatenote", method = RequestMethod.PUT)
	public ResponseEntity<String> updateNotes(@RequestBody Notes notes, HttpServletRequest request) {
		int userId = (int) request.getAttribute("userId");
		System.out.println("id =>" + userId);
		notesService.updateNotes(notes, userId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	/*------------------------------------------------------------------------------------*/
	@RequestMapping(value = "user/deletenote/{noteId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteNotes(@PathVariable("noteId") long noteId, HttpServletRequest request) {
		int user_id = (int) request.getAttribute("userId");
		notesService.deleteNotes(noteId, user_id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	/*------------------------------------------------------------------------------------*/
	@RequestMapping(value = "user/{noteId}/readonenote", method = RequestMethod.GET)
	public ResponseEntity<Notes> getnote(@PathVariable("noteId") long noteId) {

		Notes notes = notesService.getNoteById(noteId);
		return new ResponseEntity<Notes>(notes, HttpStatus.OK);
	}
	/*------------------------------------------------------------------------------------*/
    
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "user/readallnotes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> readAllNotes(HttpServletRequest request, HttpServletResponse response) {
		int userId = (int) request.getAttribute("userId");
		List<NotesDTO> notes = notesService.getAllNotesByUserId(userId);
		return new ResponseEntity<List>(notes, HttpStatus.OK);
	}
	
	
	
	/*------------------------------------------------------------------------------------*/

	@RequestMapping(value = "user/upload",  method = RequestMethod.POST, headers= {"content-type=multipart/*"})
	public ResponseEntity<String> handleFileUpload(HttpServletRequest request, @RequestParam("file") MultipartFile fileUpload, @RequestParam int noteId)
			throws Exception {
			System.out.println("file name -- "+fileUpload.getOriginalFilename());
			notesService.saveImage(fileUpload, noteId);

		
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	/*------------------------------------------------------------------------------------*/
	@RequestMapping(value="user/deleteImage", method = RequestMethod.DELETE)
	public ResponseEntity<?> handleFileDelete(HttpServletResponse response,@RequestParam int noteId){
		notesService.deleteImage(noteId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/*------------------------------------------------------------------------------------*/

	
	@RequestMapping(value="/getUrl", method = RequestMethod.POST)
	public List<UrlInfo> getUrlInfo(@RequestBody List<String> urls,HttpServletRequest request) throws URISyntaxException, IOException{
		
		JsoupDemo jsoupDemo = new JsoupDemo();
		UrlInfo urlInfo = null;
		List<UrlInfo> urlData = new ArrayList<>();
		
		for(String url : urls) {
			System.out.println(urls);
			
			urlInfo = jsoupDemo.getUrlData(url);
			urlData.add(urlInfo);
		}
		return urlData;	
	}

}
