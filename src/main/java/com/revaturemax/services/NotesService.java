package com.revaturemax.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revaturemax.models.Employee;
import com.revaturemax.models.Notes;
import com.revaturemax.models.Token;
import com.revaturemax.repositories.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotesService {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private NotesRepository notesRepository;

    public ResponseEntity<String> setNotes(long employeeId, Notes notes) {
        if (notes.getTopicId() == null) {
            return new ResponseEntity<>("Need topic id to be set!",HttpStatus.BAD_REQUEST);
        }
        notes.setEmployee(new Employee(employeeId));
        if (notes.getContent().equals("")) {
            notesRepository.delete(notes);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            notes = notesRepository.save(notes);
            return new ResponseEntity<>(notes.getId().toString(), HttpStatus.OK);
        }
    }
}
