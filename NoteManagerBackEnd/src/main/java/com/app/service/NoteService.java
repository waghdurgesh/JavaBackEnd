package com.app.service;

import java.util.List;

import com.app.pojos.Note;
import com.app.pojos.User;

public interface NoteService {
	Note addNewNote(Long userId, Note transientNote);
	Note fetchNoteDetails(Long noteId);
	Note updateNoteDetails(Note detachedNote);
	String deleteNoteDetails(Long noteId);
	List<Note> fetchNoteDetailsByUserId(User user);
}
