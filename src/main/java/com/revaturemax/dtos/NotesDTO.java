package com.revaturemax.dtos;

import com.revaturemax.models.Notes;
import org.springframework.stereotype.Component;

import javax.persistence.*;

import java.util.List;
import java.util.Objects;

@Component
public class NotesDTO {


    private List<Notes> notes;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long fav_notes_id;

    private float competency;

    public NotesDTO() {
    }

    public NotesDTO(List<Notes> notes, Long fav_notes_id, float competency) {
        this.notes = notes;
        this.fav_notes_id = fav_notes_id;
        this.competency = competency;
    }

    public List<Notes> getNotes() {
        return notes;
    }

    public void setNotes(List<Notes> notes) {
        this.notes = notes;
    }

    public Long getFav_notes_id() {
        return fav_notes_id;
    }

    public void setFav_notes_id(Long fav_notes_id) {
        this.fav_notes_id = fav_notes_id;
    }

    public float getCompetency() {
        return competency;
    }

    public void setCompetency(float competency) {
        this.competency = competency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotesDTO notesDTO = (NotesDTO) o;
        return fav_notes_id == notesDTO.fav_notes_id && Float.compare(notesDTO.competency, competency) == 0 && Objects.equals(notes, notesDTO.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notes, fav_notes_id, competency);
    }

    @Override
    public String toString() {
        return "NotesDTO{" +
                "notes=" + notes +
                ", fav_notes_id=" + fav_notes_id +
                ", competency=" + competency +
                '}';
    }
}
