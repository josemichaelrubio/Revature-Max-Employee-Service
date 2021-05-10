package com.revaturemax.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.revaturemax.models.Notes;
import org.springframework.stereotype.Component;

import javax.persistence.*;

import java.util.List;
import java.util.Objects;

@Component
public class FavNotesDTO {

   // @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Notes> notes;
   // @JsonInclude(JsonInclude.Include.NON_NULL)
    private long fav_notes_id;

   /* public FavNotesDTO(List<Notes> notes, long fav_notes_id) {
        this.notes = notes;
        this.fav_notes_id = fav_notes_id;
    } */

    public List<Notes> getNotes() {
        return notes;
    }

    public void setNotes(List<Notes> notes) {
        this.notes = notes;
    }

    public long getFav_notes_id() {
        return fav_notes_id;
    }

    public void setFav_notes_id(long fav_notes_id) {
        this.fav_notes_id = fav_notes_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavNotesDTO that = (FavNotesDTO) o;
        return fav_notes_id == that.fav_notes_id && Objects.equals(notes, that.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notes, fav_notes_id);
    }

    @Override
    public String toString() {
        return "FavNotesDTO{" +
                "notes=" + notes +
                ", fav_notes_id=" + fav_notes_id +
                '}';
    }
}
