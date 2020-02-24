package com.floatingreels.sharpnote.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class NoteViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Note>> notes;

    //READ
    public MutableLiveData<ArrayList<Note>> getAllNotes() {
        if (notes == null){
            notes = new MutableLiveData<>();
            loadNotes();
        }
        return notes;
    }

    private void loadNotes(){
        ArrayList<Note> allNotes = new ArrayList<>();
        allNotes.add(new Note("to do list", "find job\nget rich\ndie trying"));
        notes.setValue(allNotes);
    }
}
