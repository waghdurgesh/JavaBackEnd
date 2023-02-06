package com.app.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.pojos.Note;
import com.app.pojos.User;
import com.app.repository.NoteRepository;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteRepository noteRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper mapper;
	
	@PostConstruct
	public void init(){
		System.out.println("in init "+mapper);
	}
	
	@Override
	public Note addNewNote(Long userId, Note transientNote) {
		User user = userService.fetchUserDetails(userId);
		user.addNote(transientNote);
		return noteRepo.save(transientNote);
	}

	@Override
	public Note fetchNoteDetails(Long noteId) {
		return noteRepo.findById(noteId).orElseThrow(() -> new ResourceNotFoundException("Invalid Note Id"));
	}

	@Override
	public Note updateNoteDetails(Note detachedNote) {
		if (noteRepo.existsById(detachedNote.getId())) {
			return noteRepo.save(detachedNote);
		}
		throw new ResourceNotFoundException("Invalid Note Id : Updation Failed");
	}

	@Override
	public String deleteNoteDetails(Long noteId) {
		if (noteRepo.existsById(noteId)) {
			noteRepo.deleteById(noteId);
			return "Note deleted";
		}
		return "Deletion Failed : Invalid Id";
	}

	@Override
	public List<Note> fetchNoteDetailsByUserId(User user) {
		return noteRepo.findByUser(user);
	}
}