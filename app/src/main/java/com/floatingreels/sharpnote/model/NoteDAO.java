package com.floatingreels.sharpnote.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDAO {

    @Insert
    void createNote(Note n);

    @Update
    void updateNote(Note n);

    @Query(value = "SELECT * FROM Note")
    LiveData<List<Note>> getAllNotes();

}
