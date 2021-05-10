package com.revaturemax.services;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.revaturemax.dtos.NotesDTO;
import com.revaturemax.models.Employee;
import com.revaturemax.models.Notes;
import com.revaturemax.models.TopicCompetency;
import com.revaturemax.models.TopicCompetencyId;
import com.revaturemax.repositories.NotesRepository;
import com.revaturemax.repositories.TopicCompetencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotesService {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private NotesRepository notesRepository;
    @Autowired
    private TopicCompetencyRepository topicCompetencyRepo;

    @Transactional
    public NotesDTO getNotesByTopic(Long topicId, Long employeeId) {
        NotesDTO notes = new NotesDTO();
        List<Notes> notesList = notesRepository.getNotesByTopicId(topicId);
        notesList.forEach(n -> {
            n.setVotes(topicCompetencyRepo.countByFavNotes(n));
        });
        notes.setNotes(notesList);
        TopicCompetency tc = topicCompetencyRepo.getOne(new TopicCompetencyId(employeeId, topicId));
        notes.setFav_notes_id(tc.getFavNotes().getId());
        notes.setCompetency(tc.getCompetency());
        return notes;
    }

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

//    public ResponseEntity<String> getFavNotes(long favNotesId, FavNotesDTO favNotesDTO) {
//        Notes notes = notesRepository.findById(favNotesId).orElse(null);
//        if (notes== null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        return new ResponseEntity<T>(notes, HttpStatus.OK);
//
//
//    }

}
