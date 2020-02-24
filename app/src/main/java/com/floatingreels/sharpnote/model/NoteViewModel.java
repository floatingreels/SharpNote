package com.floatingreels.sharpnote.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class NoteViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Note>> notes;

    //CREATE
    private void loadNotes(){
        ArrayList<Note> allNotes = new ArrayList<>();
        allNotes.add(new Note("To-do list", "find job\nget rich\ndie trying"));
        allNotes.add(new Note("Zakdoek Raketkanon","afmetingen 39 x 39 cm, misschien kader kopen in Lucas Creativ?"));
        allNotes.add(new Note("Deurbel", "nieuwe deurbel gaan kopen in Gamma of Brico"));
        notes.setValue(allNotes);
    }

    //READ
    public MutableLiveData<ArrayList<Note>> getAllNotes() {
        if (notes == null){
            notes = new MutableLiveData<>();
            loadNotes();
        }
        return notes;
    }
}
