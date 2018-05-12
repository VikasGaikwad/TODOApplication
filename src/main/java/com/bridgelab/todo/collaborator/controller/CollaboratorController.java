/**
 * 
 */
package com.bridgelab.todo.collaborator.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelab.todo.collaborator.service.ICollaboratorService;

/**
 * @author bridgeit
 *
 */
@RestController
public class CollaboratorController {

	@Autowired
	ICollaboratorService collaboratorService;
	
	
	@RequestMapping(value = "user/addcollaborator", method = RequestMethod.POST)
	public ResponseEntity<Void> createCollaborator(@RequestParam String sharedId, @RequestParam int noteId,HttpServletRequest request) {

	System.out.println("Inside add collaborator");
	int userId = (int) request.getAttribute("userId");
	
	try {
	collaboratorService.addCollaborator(sharedId, noteId, userId);
	return new ResponseEntity<Void>(HttpStatus.OK);
	} catch (Exception e) {
		System.out.println("catch block exicuted in CollaboratorController....");
	e.printStackTrace();
	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	}
	}
	

	@RequestMapping(value = "user/removecollaborator", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeCollaborator(@RequestParam String sharedUserEmail, @RequestParam int noteId){
		
		try {
			collaboratorService.removeCollaborator(sharedUserEmail, noteId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}
	
	

	/*@RequestMapping(value = "user/savecollaborator", method = RequestMethod.POST)
	public ResponseEntity<?> saveCollaborator(@RequestBody CollaboratorDTO collaboratorDTO, HttpServletRequest request,
			HttpServletResponse response) {
		int userId = (int) request.getAttribute("userId");
		collaboratorService.saveCollaborator(collaboratorDTO, userId);
		response.setStatus(200);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "user/deletecollaborator", method = RequestMethod.POST)
	public ResponseEntity<?> deleteCollaborator(@RequestBody CollaboratorDTO collaboratorDTO,
			HttpServletRequest request) {
		int userId = (int) request.getAttribute("userId");
		collaboratorService.deleteCollaborator(collaboratorDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}*/

}
