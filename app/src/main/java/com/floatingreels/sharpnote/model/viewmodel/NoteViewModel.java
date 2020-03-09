package com.floatingreels.sharpnote.model.viewmodel;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;

import com.floatingreels.sharpnote.model.Note;
import com.floatingreels.sharpnote.model.NoteDatabase;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private final LiveData<List<Note>> NOTES;
    private NoteDatabase database;
    private final Application mApplication;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        mApplication = application;
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
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mApplication);
        String sort = settings.getString("lp_pref_sort", "Sort by title");
            //TODO: switch
            switch (sort)
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