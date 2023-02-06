package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Note;
import com.app.pojos.User;

public interface NoteRepository extends JpaRepository<Note, Long> {
	List<Note> findByUser(User user);
}