package com.floatingreels.sharpnote.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.floatingreels.sharpnote.model.Note;
import com.floatingreels.sharpnote.model.NoteDatabase;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private final LiveData<List<Note>> NOTES;
    private NoteDatabase database;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        database = NoteDatabase.getSharedInstance(application);
        NOTES = database.getNoteDAO().getAllNotes();
    }

    // CREATE
    public void createNote(Note note){
        NoteDatabase.databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                database.getNoteDAO().createNote(note);
            }
        });
    }

    // READ
    public LiveData<List<Note>> getNOTES() {
        return NOTES;
    }

    // UPDATE
    public void updateNote(Note note){
        NoteDatabase.databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                database.getNoteDAO().updateNote(note);
            }
        });
    }

    // DELETE
    public void deleteNote(Note note){
        NoteDatabase.databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                database.getNoteDAO().deleteNote(note);
            }
        });
    }
}