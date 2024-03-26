package com.example.hwSeminarSix.controller;

import com.example.hwSeminarSix.model.Note;
import com.example.hwSeminarSix.service.NotesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/notes")
public class NotesController {
    private NotesService notesService;

    @PostMapping("/add")
    public ResponseEntity<Note> addNote(@RequestBody Note note) {
        return ResponseEntity.ok(notesService.addNote(note));
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return ResponseEntity.ok(notesService.getAllNotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        Optional<Note> foundNote = notesService.findNoteById(id);
        return ResponseEntity.of(foundNote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note note) {
        try {
            return ResponseEntity.ok(notesService.updateNote(id, note));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable Long id) {
        try {
            notesService.deleteNote(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
