package com.app.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.InsertNoteDto;
import com.app.dto.UpdateNoteDto;
import com.app.pojos.Note;
import com.app.pojos.User;
import com.app.service.NoteService;
import com.app.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/notes")
public class NotesController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private NoteService noteService;
	
	@Autowired
	private ModelMapper mapper;
	
	@PostMapping("/{userId}")
	public Note saveNoteDetails(@PathVariable Long userId, @RequestBody InsertNoteDto newNoteDto){
		Note newNote = mapper.map(newNoteDto, Note.class);
		return noteService.addNewNote(userId, newNote);	
	}
	
	@GetMapping("/{noteId}")
	public Note getNoteDetails(@PathVariable Long noteId){
		System.out.println("in get emp details "+noteId);
		return noteService.fetchNoteDetails(noteId);
	}
	
	@GetMapping("/userid/{userId}")
	public List<Note> getNoteDetailsByUserId(@PathVariable Long userId){
		User user = userService.fetchUserDetails(userId);
		return noteService.fetchNoteDetailsByUserId(user);
	}
	
	@DeleteMapping("/{noteId}")
	public ApiResponse deleteNote(@PathVariable Long noteId)
	{
		System.out.println("in del emp "+noteId);
		return new ApiResponse(noteService.deleteNoteDetails(noteId));
	}
	
	@PutMapping("/{userId}")
	public Note updateNoteDetails(@PathVariable Long userId, @RequestBody UpdateNoteDto detachedNoteDto){
		Note detachedNote = mapper.map(detachedNoteDto, Note.class);
		detachedNote.setUser(userService.fetchUserDetails(userId));
		return noteService.updateNoteDetails(detachedNote);
	}
	
}
