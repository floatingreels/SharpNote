package com.floatingreels.sharpnote.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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

//CREATE
//    private void loadNotes(){
//        allNotes = new ArrayList<>();
//        allNotes.add(new Note("To-do list", "find job\nget rich\ndie trying"));
//        allNotes.add(new Note("Zakdoek Raketkanon","afmetingen 39 x 39 cm, misschien kader kopen in Lucas Creativ?"));
//        allNotes.add(new Note("Deurbel", "nieuwe deurbel gaan kopen in Gamma of Brico"));
//        notes.setValue(allNotes);
//    }

    // READ
    public LiveData<List<Note>> getNOTES() {
        return NOTES;
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
}
