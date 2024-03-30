package com.example.hwSeminarSix.service;

import com.example.hwSeminarSix.model.Note;
import com.example.hwSeminarSix.repository.NotesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NotesService {
    private NotesRepository notesRepository;

    public Note addNote(Note note) {
        return notesRepository.save(note);
    }

    public List<Note> getAllNotes() {
        return notesRepository.findAll();
    }

    public Optional<Note> findNoteById(Long id) {
        return notesRepository.findById(id);
    }

    public Note updateNote(Long id, Note note) {
        Note noteForUpdate = findNoteById(id).orElse(null);
        if (noteForUpdate != null) {
            if (note.getTitle() != null) {
                noteForUpdate.setTitle(note.getTitle());
            }
            if (note.getDescription() != null) {
                noteForUpdate.setDescription(note.getDescription());
            }
            return notesRepository.save(noteForUpdate);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void deleteNote(Long id) {
        if (notesRepository.existsById(id)) {
            notesRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
