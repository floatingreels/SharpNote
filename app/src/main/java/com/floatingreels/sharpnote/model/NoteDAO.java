package com.floatingreels.sharpnote.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDAO {

    @Query(value = "SELECT * FROM Note")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM Note ORDER BY title")
    LiveData<List<Note>> sortAllNotesByTitle();

    @Query("SELECT * FROM Note ORDER BY timeCreated")
    LiveData<List<Note>> sortAllNotesByTimeCreated();

    @Query("SELECT * FROM Note ORDER BY timeModified")
    LiveData<List<Note>> soortAllNotesByTimeModified();

    @Insert
    void createNote(Note n);

    @Update
    void updateNote(Note n);

    @Delete
    void deleteNote(Note n);

}
